import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;

import log.LogMessageProto.LogMessage;

public class ServerProtBuf
{
    public static void main(String[] args)
    {
        try
        {
            int port = 4444;
            ServerSocket server_socket = new ServerSocket(port);
            System.out.println("log-server running on port " + server_socket.getLocalPort());

            while (true)
            {
                Socket socket = server_socket.accept();
                System.out.println("New connection accepted " + socket.getInetAddress() + ":" + socket.getPort());

                LogHandler handler = new LogHandler(socket);
                Thread thread = new Thread(handler);
                thread.start();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

class LogHandler implements Runnable
{
    static Object lock = new Object();

    Socket socket;

    public LogHandler(Socket s)
    {
        this.socket = s;
    }

    public void run()
    {
        try
        {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            BufferedWriter log = new BufferedWriter(new FileWriter("server.log", true));

            while (true)
            {
                int len = in.readInt();              // erst die Laenge lesen (das writeInt vom Client)
                byte[] data = new byte[len];
                in.readFully(data);                 // dann genau len Bytes lesen

                LogMessage msg = LogMessage.parseFrom(data);   // Bytes -> Nachricht

                String line = Instant.ofEpochMilli(msg.getTimestamp()) + " [" + msg.getSeverity() + "] "
                    + (msg.hasSource() ? msg.getSource() : "-") + ": " + msg.getMessage();

                System.out.println(line);

                synchronized (lock)                 // mehrere Threads duerfen nicht gleichzeitig schreiben
                {
                    log.write(line);                // ein Log-Eintrag = eine Zeile
                    log.newLine();
                    log.flush();                    // sofort auf Platte schreiben
                }
            }
        } catch (EOFException e) {
            System.out.println("client disconnected");   // Client hat zugemacht -> Schleife endet
        } catch (IOException e) {
            e.printStackTrace();
        } finally
        {
            try { socket.close(); } catch (IOException e) {}
        }
    }
}
