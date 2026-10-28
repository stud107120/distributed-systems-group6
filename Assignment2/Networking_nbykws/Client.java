import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client
{
    Socket server;
    String http1 = "GET / HTTP/1.1";
    String http2 = "Host: google.com";
    String http3 = "Host: fh-wedel.de";
    String http4 = "Message";

    public static void main(String[] args)
    {
        //1c)
        //System.setProperty("http.proxyHost", "proxy.example.de");
        //System.setProperty("http.proxyPort", "8080");

        Client myClient = new Client();
        myClient.createSocket();

        myClient.send();
    }

    public void createSocket()
    {
        try
        {
            //server = new Socket("google.com", 80);
            server = new Socket("localhost", 4444);

            System.out.println ("Connected to " + server.getInetAddress());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void send()
    {
        try {
           PrintWriter writer = new PrintWriter(new OutputStreamWriter(server.getOutputStream()));

           BufferedReader reader = new BufferedReader(new InputStreamReader(server.getInputStream()));

           writer.println(http1);
           writer.println(http2);
           writer.println("");

           writer.flush();

           String response;
           while((response = reader.readLine()) != null)
           {
               System.out.println("Server: '" + response + "'");
           }

           writer.close();
           reader.close();
           server.close();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
