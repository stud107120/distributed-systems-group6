import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class Server
{
    public static void main (String[] args)
    {
        try
        {
            int port = 4444;
            ServerSocket server_socket = new ServerSocket(port);
            System.out.println("http-server running on port" + server_socket.getLocalPort());

            while (true)
            {
                Socket socket = server_socket.accept();
                System.out.println("New connection accepted " + socket.getInetAddress() + ":" + socket.getPort());
                System.out.println("");

                HttpsRequestHandler request = new HttpsRequestHandler(socket);
                Thread thread = new Thread(request);
                thread.start();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
		}
    }
}

class HttpsRequestHandler implements Runnable
{
    private Socket socket;
    private BufferedReader br;
    private PrintWriter out;

    public HttpsRequestHandler(Socket s) throws Exception
    {
        this.socket = s;
        try
        {
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        String headerLine;
        String firstLine;
        try
		{
            firstLine = br.readLine();
            System.out.println("First Line: " + firstLine);
            if (firstLine == null) return;

            while (true)
            {
				headerLine = br.readLine();
                System.out.println("Headerline: " + headerLine);

                if (headerLine == null || headerLine.isEmpty())
                    break;
            }

            String[] token = firstLine.split(" ");
            String temp = "";
            if(token.length > 0)
                temp = token[0];

            if(temp.equals("GET"))
            {
                out.println("HTTP/1.1 200 OK");
                out.println("Content-Type: text/html");
                out.println();

                out.println("<html><body>Test success!!!</body></html>");

                out.flush();
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try {
    			socket.close();
    			System.out.println("");
    			System.out.println("connection closed");
    		} catch (IOException e) {
    		}
        }
    }
}
