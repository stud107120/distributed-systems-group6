import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import db.DatabaseRPC.AddRecordArg;
import db.DatabaseRPC.GetRecordArg;
import db.DatabaseRPC.Operation;
import db.DatabaseRPC.RPC_Request;
import db.DatabaseRPC.RPC_Response;

public class DbClient
{
    public static void main(String[] args)
    {
        try
        {
            Socket socket = new Socket("localhost", 4445);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            System.out.println("Connected to " + socket.getInetAddress());

            // Aufgabe 4: die 6 Records aus der Tabelle schreiben
            addRecord(out, in, 4101, "Appen");
            addRecord(out, in, 4102, "Ahrensburg");
            addRecord(out, in, 4103, "Wedel");
            addRecord(out, in, 4104, "Aumuehle");
            addRecord(out, in, 4105, "Seevetal");
            addRecord(out, in, 4106, "Quickborn");

            // Aufgabe 4: Index 4103 und 4107 lesen
            getRecord(out, in, 4103);
            getRecord(out, in, 4107);

            // Aufgabe 5: getSize aufrufen
            getSize(out, in);

            socket.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Request senden: erst Laenge, dann Bytes
    static void send(DataOutputStream out, RPC_Request req) throws Exception
    {
        byte[] data = req.toByteArray();
        out.writeInt(data.length);
        out.write(data);
        out.flush();
    }

    // Response empfangen: erst Laenge, dann Bytes
    static RPC_Response receive(DataInputStream in) throws Exception
    {
        int len = in.readInt();
        byte[] data = new byte[len];
        in.readFully(data);
        return RPC_Response.parseFrom(data);
    }

    static void addRecord(DataOutputStream out, DataInputStream in, int index, String record) throws Exception
    {
        RPC_Request req = RPC_Request.newBuilder()
            .setOperation(Operation.ADD_RECORD)
            .setAddRecord(AddRecordArg.newBuilder().setIndex(index).setRecord(record).build())
            .build();
        send(out, req);
        RPC_Response resp = receive(in);
        System.out.println("addRecord(" + index + ", \"" + record + "\") -> " + (resp.hasError() ? "ERROR: " + resp.getError() : "OK"));
    }

    static void getRecord(DataOutputStream out, DataInputStream in, int index) throws Exception
    {
        RPC_Request req = RPC_Request.newBuilder()
            .setOperation(Operation.GET_RECORD)
            .setGetRecord(GetRecordArg.newBuilder().setIndex(index).build())
            .build();
        send(out, req);
        RPC_Response resp = receive(in);
        if (resp.hasError())
            System.out.println("getRecord(" + index + ") -> ERROR: " + resp.getError());
        else
            System.out.println("getRecord(" + index + ") -> \"" + resp.getRecord() + "\"");
    }

    static void getSize(DataOutputStream out, DataInputStream in) throws Exception
    {
        RPC_Request req = RPC_Request.newBuilder()
            .setOperation(Operation.GET_SIZE)
            .build();
        send(out, req);
        RPC_Response resp = receive(in);
        System.out.println("getSize() -> " + resp.getSize());
    }
}
