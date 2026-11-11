import java.io.*;
import java.net.Socket;

import log.LogMessageProto.*;


public class ClientProtBuf
{
    Socket server;
    String http = "GET / HTTP/1.1";
    String google = "google.com";
    String fhwedel = "fh-wedel.de";
    String local = "localhost";
    String stud = "10.217.58.204";


    public static void main(String[] args)
    {
        //1c)
        //System.setProperty("http.proxyHost", "proxy.example.de");
        //System.setProperty("http.proxyPort", "8080");

        ClientProtBuf myClient = new ClientProtBuf();
        myClient.createSocket();

        myClient.send();
    }

    public void createSocket()
    {
        try
        {
            //server = new Socket(stud, 80);
            server = new Socket(local, 4444);

            System.out.println ("Connected to " + server.getInetAddress());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void send()
    {
        try
        {
            DataOutputStream out = new DataOutputStream(server.getOutputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(server.getInputStream()));

            LogMessage log = LogMessage.newBuilder()
                            .setTimestamp(System.currentTimeMillis())
                            .setSource("ClientProtBuf@localhost")
                            .setSeverity(Severity.INFO)
                            .setMessage("test message")
                            .build();
                            
            byte[] b = log.toByteArray();
            
            out.writeInt(b.length);
            out.write(b);
            out.flush();           

            out.close();
            reader.close();
            server.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
