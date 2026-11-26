package jsonrpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonServer
{
    static HashMap<Integer, String> database = new HashMap<Integer, String>();

    public static void main (String[] args)
    {
        try
        {
            int port = 4447;
            ServerSocket server_socket = new ServerSocket(port);
            System.out.println("json-rpc server running on port " + server_socket.getLocalPort());

            while (true)
            {
                Socket socket = server_socket.accept();
                System.out.println("New connection accepted " + socket.getInetAddress() + ":" + socket.getPort());
                System.out.println("");

                RequestHandler request = new RequestHandler(socket);
                Thread thread = new Thread(request);
                thread.start();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
		}
    }
}

class RequestHandler implements Runnable
{
    private Socket socket;
    private BufferedReader br;
    private PrintWriter out;

    public RequestHandler(Socket s) throws Exception
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
        try
		{
		    String message;
			JsonObject resp = null;
            while ((message = br.readLine()) != null)
            {
				JsonObject req = JsonParser.parseString(message).getAsJsonObject();
				String method = req.get("method").getAsString();
				JsonObject params = req.getAsJsonObject("params");
				int id = req.get("id").getAsInt();

				resp = new JsonObject();
                resp.addProperty("jsonrpc", "2.0");
                resp.addProperty("id", id);

                switch(method){
                    case "addRecord" ->
                    {
                        JsonServer.database.put(params.get("index").getAsInt(), params.get("record").getAsString());
                        resp.addProperty("result","OK");
                    }
                    case "getRecord" ->
                    {
                        int index = params.get("index").getAsInt();
                        String city = JsonServer.database.get(index);
                        if (city == null){
                            JsonObject err = new JsonObject();
                            err.addProperty("code", -32000);
                            err.addProperty("message","no Record with index: " + index);
                            resp.add("error", err);
                        } else {
                            resp.addProperty("index", index);
                            resp.addProperty("city", city);                           
                        }
                    }
                    case "getSize" ->
                    {
                        int size = JsonServer.database.size();
                        resp.addProperty("size", size);
                    }
                    default ->
                    {
                        JsonObject err = new JsonObject();
                        err.addProperty("code", -32601);
                        err.addProperty("message", "Method not found");
                        resp.add("error", err);
                    }
                }

                String response = resp.toString();
                System.out.println("Response to Client: " + response);
                out.println(response);
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
