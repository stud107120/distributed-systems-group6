import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import db.DatabaseRPC.Operation;
import db.DatabaseRPC.RPC_Request;
import db.DatabaseRPC.RPC_Response;

public class DbServer
{
    // static -> alle Handler-Threads teilen sich EINE Datenbank
    static HashMap<Integer, String> database = new HashMap<Integer, String>();

    public static void main(String[] args)
    {
        try
        {
            int port = 4445;   // anderer Port als der Log-Server (4444)
            ServerSocket server_socket = new ServerSocket(port);
            System.out.println("db-server running on port " + server_socket.getLocalPort());

            while (true)
            {
                Socket socket = server_socket.accept();
                System.out.println("New connection accepted " + socket.getInetAddress() + ":" + socket.getPort());

                DbHandler handler = new DbHandler(socket);
                Thread thread = new Thread(handler);
                thread.start();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

class DbHandler implements Runnable
{
    Socket socket;

    public DbHandler(Socket s)
    {
        this.socket = s;
    }

    public void run()
    {
        try
        {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            while (true)
            {
                // Request lesen: erst die Laenge, dann genau so viele Bytes
                int len = in.readInt();
                byte[] data = new byte[len];
                in.readFully(data);
                RPC_Request request = RPC_Request.parseFrom(data);
                System.out.println("Request: " + request.getOperation());

                // Operation ausfuehren -> Response bauen
                RPC_Response response = execute(request);

                // Response zurueckschicken: erst die Laenge, dann die Bytes
                byte[] respBytes = response.toByteArray();
                out.writeInt(respBytes.length);
                out.write(respBytes);
                out.flush();
            }
        } catch (EOFException e) {
            System.out.println("client disconnected");
        } catch (IOException e) {
            e.printStackTrace();
        } finally
        {
            try { socket.close(); } catch (IOException e) {}
        }
    }

    static RPC_Response execute(RPC_Request request)
    {
        RPC_Response.Builder resp = RPC_Response.newBuilder();

        switch (request.getOperation())
        {
            case ADD_RECORD:
                DbServer.database.put(request.getAddRecord().getIndex(), request.getAddRecord().getRecord());
                System.out.println("  added: " + request.getAddRecord().getIndex() + " -> " + request.getAddRecord().getRecord());
                break;

            case GET_RECORD:
            {
                int index = request.getGetRecord().getIndex();
                String record = DbServer.database.get(index);
                if (record != null)
                    resp.setRecord(record);
                else
                    resp.setError("kein Record mit Index " + index);
                break;
            }

            case GET_SIZE:
                resp.setSize(DbServer.database.size());
                break;

            default:
                resp.setError("unbekannte Operation: " + request.getOperation());
        }

        return resp.build();
    }
}
