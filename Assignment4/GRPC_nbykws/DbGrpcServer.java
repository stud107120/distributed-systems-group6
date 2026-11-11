import java.util.HashMap;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import db.DatabaseServiceGrpc;
import db.DatabaseServiceProto.AddRecordRequest;
import db.DatabaseServiceProto.AddRecordResponse;
import db.DatabaseServiceProto.GetRecordRequest;
import db.DatabaseServiceProto.GetRecordResponse;
import db.DatabaseServiceProto.GetSizeRequest;
import db.DatabaseServiceProto.GetSizeResponse;

public class DbGrpcServer
{
    public static void main(String[] args) throws Exception
    {
        int port = 4446;   // eigener Port (Log-Server: 4444, RPC-Server: 4445)

        Server server = ServerBuilder.forPort(port)
            .addService(new DatabaseServiceImpl())
            .build()
            .start();

        System.out.println("grpc db-server running on port " + server.getPort());
        server.awaitTermination();
    }
}

class DatabaseServiceImpl extends DatabaseServiceGrpc.DatabaseServiceImplBase
{    
    static HashMap<Integer, String> database = new HashMap<Integer, String>();

    @Override
    public void getRecord(GetRecordRequest req, StreamObserver<GetRecordResponse> responseObserver)
    {
        String record = database.get(req.getIndex());
       
        GetRecordResponse.Builder resp = GetRecordResponse.newBuilder();
        if (record != null)
            resp.setRecord(record);
        else
            resp.setError("kein Record mit Index " + req.getIndex());   // Fall 4107
       
        responseObserver.onNext(resp.build());
        responseObserver.onCompleted();
    }

    @Override
    public void addRecord(AddRecordRequest req, StreamObserver<AddRecordResponse> responseObserver)
    {
        database.put(req.getIndex(), req.getRecord());
        System.out.println("addRecord: " + req.getIndex() + " -> " + req.getRecord());
        
        responseObserver.onNext(AddRecordResponse.newBuilder().build());  // Antwort-Message
        responseObserver.onCompleted();                                   // Call beenden
    }

    @Override
    public void getSize(GetSizeRequest req, StreamObserver<GetSizeResponse> responseObserver)
    {        
        responseObserver.onNext(GetSizeResponse.newBuilder().setSize(database.size()).build());
        responseObserver.onCompleted();
    }
}
