import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import db.DatabaseServiceGrpc;
import db.DatabaseServiceProto.AddRecordRequest;
import db.DatabaseServiceProto.AddRecordResponse;
import db.DatabaseServiceProto.GetRecordRequest;
import db.DatabaseServiceProto.GetRecordResponse;
import db.DatabaseServiceProto.GetSizeRequest;
import db.DatabaseServiceProto.GetSizeResponse;

public class DbGrpcClient
{
    public static void main(String[] args)
    {
        // Verbindung zum Server (plaintext, lokal)
        ManagedChannel channel = ManagedChannelBuilder
            .forAddress("localhost", 4446)
            .usePlaintext()
            .build();

        // Blocking Stub: ruft die rpc-Methoden wie normale Methoden auf
        DatabaseServiceGrpc.DatabaseServiceBlockingStub stub =
            DatabaseServiceGrpc.newBlockingStub(channel);

        addRecord(stub, 4101, "Appen");
        addRecord(stub, 4102, "Ahrensburg");
        addRecord(stub, 4103, "Wedel");
        addRecord(stub, 4104, "Aumuehle");
        addRecord(stub, 4105, "Seevetal");
        addRecord(stub, 4106, "Quickborn");

        getRecord(stub, 4103);
        getRecord(stub, 4107);

        GetSizeResponse sizeResp = stub.getSize(GetSizeRequest.newBuilder().build());
        System.out.println("getSize() -> " + sizeResp.getSize());
        
        channel.shutdown();
    }

    static void addRecord(DatabaseServiceGrpc.DatabaseServiceBlockingStub stub, int index, String record)
        {
            AddRecordResponse resp = stub.addRecord(AddRecordRequest.newBuilder()
                .setIndex(index)
                .setRecord(record)
                .build());
            System.out.println("addRecord(" + index + ", \"" + record + "\") -> "
                + (resp.hasError() ? "ERROR: " + resp.getError() : "OK"));
        }
    
        static void getRecord(DatabaseServiceGrpc.DatabaseServiceBlockingStub stub, int index)
        {
            GetRecordResponse resp = stub.getRecord(GetRecordRequest.newBuilder()
                .setIndex(index)
                .build());
            if (resp.hasError())
                System.out.println("getRecord(" + index + ") -> ERROR: " + resp.getError());
            else
                System.out.println("getRecord(" + index + ") -> \"" + resp.getRecord() + "\"");
        }
}
