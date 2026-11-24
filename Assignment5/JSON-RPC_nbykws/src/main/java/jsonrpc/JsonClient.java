package jsonrpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonClient
{
    Socket server;
    static int id = 0;

    PrintWriter writer;
    BufferedReader reader;

    public static void main(String[] args)
    {
        JsonClient myClient = new JsonClient();
        myClient.createSocket();

        myClient.send();
    }

    public void createSocket()
    {
        try
        {
            server = new Socket("localhost", 4447);

            System.out.println ("Connected to " + server.getInetAddress());

            writer = new PrintWriter(new OutputStreamWriter(server.getOutputStream()));
 
            reader = new BufferedReader(new InputStreamReader(server.getInputStream()));
            
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addRecord(int index, String city)
    {
        JsonObject params = new JsonObject();
        params.addProperty("index", index);
        params.addProperty("record", city);

        builder(params, "addRecord");
    }

    public void getRecord(int index)
    {
        JsonObject params = new JsonObject();
        params.addProperty("index", index);
        builder(params, "getRecord");
    }

    public void getSize()
    {
        JsonObject params = new JsonObject();
        builder(params, "getSize");
    }

    private void builder(JsonElement params, String method)
    {
        JsonObject req = new JsonObject();
        req.addProperty("jsonrpc", "2.0");
        req.addProperty("method", method);
        if (params != null)
            req.add("params", params);
        req.addProperty("id", id++);
        String request = req.toString();
        System.out.println("Request to server: " + request);
        writer.println(request);
        writer.flush();

        try
        {
            String answer = reader.readLine();
            System.out.println("Answer from server: " + answer);

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void send()
    {
        try {
            
           addRecord(4101, "Appen");
           addRecord(4102, "Ahrensburg");
           addRecord(4103, "Wedel");
           addRecord(4104, "Aumuehle");
           addRecord(4105, "Seevetal");
           addRecord(4106, "Quickborn");

           getRecord(4103);
           getRecord(4107);

           getSize();          

           writer.close();
           reader.close();
           server.close();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
