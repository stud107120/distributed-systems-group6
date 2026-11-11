package db;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * gRPC service interface: every rpc is one remote operation with its own
 * request/response message types. protoc + the grpc plugin generate the
 * client stub and the server base class from this.
 * </pre>
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class DatabaseServiceGrpc {

  private DatabaseServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "db.DatabaseService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<db.DatabaseServiceProto.GetRecordRequest,
      db.DatabaseServiceProto.GetRecordResponse> getGetRecordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetRecord",
      requestType = db.DatabaseServiceProto.GetRecordRequest.class,
      responseType = db.DatabaseServiceProto.GetRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<db.DatabaseServiceProto.GetRecordRequest,
      db.DatabaseServiceProto.GetRecordResponse> getGetRecordMethod() {
    io.grpc.MethodDescriptor<db.DatabaseServiceProto.GetRecordRequest, db.DatabaseServiceProto.GetRecordResponse> getGetRecordMethod;
    if ((getGetRecordMethod = DatabaseServiceGrpc.getGetRecordMethod) == null) {
      synchronized (DatabaseServiceGrpc.class) {
        if ((getGetRecordMethod = DatabaseServiceGrpc.getGetRecordMethod) == null) {
          DatabaseServiceGrpc.getGetRecordMethod = getGetRecordMethod =
              io.grpc.MethodDescriptor.<db.DatabaseServiceProto.GetRecordRequest, db.DatabaseServiceProto.GetRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetRecord"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.DatabaseServiceProto.GetRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.DatabaseServiceProto.GetRecordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DatabaseServiceMethodDescriptorSupplier("GetRecord"))
              .build();
        }
      }
    }
    return getGetRecordMethod;
  }

  private static volatile io.grpc.MethodDescriptor<db.DatabaseServiceProto.AddRecordRequest,
      db.DatabaseServiceProto.AddRecordResponse> getAddRecordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddRecord",
      requestType = db.DatabaseServiceProto.AddRecordRequest.class,
      responseType = db.DatabaseServiceProto.AddRecordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<db.DatabaseServiceProto.AddRecordRequest,
      db.DatabaseServiceProto.AddRecordResponse> getAddRecordMethod() {
    io.grpc.MethodDescriptor<db.DatabaseServiceProto.AddRecordRequest, db.DatabaseServiceProto.AddRecordResponse> getAddRecordMethod;
    if ((getAddRecordMethod = DatabaseServiceGrpc.getAddRecordMethod) == null) {
      synchronized (DatabaseServiceGrpc.class) {
        if ((getAddRecordMethod = DatabaseServiceGrpc.getAddRecordMethod) == null) {
          DatabaseServiceGrpc.getAddRecordMethod = getAddRecordMethod =
              io.grpc.MethodDescriptor.<db.DatabaseServiceProto.AddRecordRequest, db.DatabaseServiceProto.AddRecordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddRecord"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.DatabaseServiceProto.AddRecordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.DatabaseServiceProto.AddRecordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DatabaseServiceMethodDescriptorSupplier("AddRecord"))
              .build();
        }
      }
    }
    return getAddRecordMethod;
  }

  private static volatile io.grpc.MethodDescriptor<db.DatabaseServiceProto.GetSizeRequest,
      db.DatabaseServiceProto.GetSizeResponse> getGetSizeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetSize",
      requestType = db.DatabaseServiceProto.GetSizeRequest.class,
      responseType = db.DatabaseServiceProto.GetSizeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<db.DatabaseServiceProto.GetSizeRequest,
      db.DatabaseServiceProto.GetSizeResponse> getGetSizeMethod() {
    io.grpc.MethodDescriptor<db.DatabaseServiceProto.GetSizeRequest, db.DatabaseServiceProto.GetSizeResponse> getGetSizeMethod;
    if ((getGetSizeMethod = DatabaseServiceGrpc.getGetSizeMethod) == null) {
      synchronized (DatabaseServiceGrpc.class) {
        if ((getGetSizeMethod = DatabaseServiceGrpc.getGetSizeMethod) == null) {
          DatabaseServiceGrpc.getGetSizeMethod = getGetSizeMethod =
              io.grpc.MethodDescriptor.<db.DatabaseServiceProto.GetSizeRequest, db.DatabaseServiceProto.GetSizeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetSize"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.DatabaseServiceProto.GetSizeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  db.DatabaseServiceProto.GetSizeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DatabaseServiceMethodDescriptorSupplier("GetSize"))
              .build();
        }
      }
    }
    return getGetSizeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DatabaseServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DatabaseServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DatabaseServiceStub>() {
        @java.lang.Override
        public DatabaseServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DatabaseServiceStub(channel, callOptions);
        }
      };
    return DatabaseServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static DatabaseServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DatabaseServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DatabaseServiceBlockingV2Stub>() {
        @java.lang.Override
        public DatabaseServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DatabaseServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return DatabaseServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DatabaseServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DatabaseServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DatabaseServiceBlockingStub>() {
        @java.lang.Override
        public DatabaseServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DatabaseServiceBlockingStub(channel, callOptions);
        }
      };
    return DatabaseServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DatabaseServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DatabaseServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DatabaseServiceFutureStub>() {
        @java.lang.Override
        public DatabaseServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DatabaseServiceFutureStub(channel, callOptions);
        }
      };
    return DatabaseServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * gRPC service interface: every rpc is one remote operation with its own
   * request/response message types. protoc + the grpc plugin generate the
   * client stub and the server base class from this.
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void getRecord(db.DatabaseServiceProto.GetRecordRequest request,
        io.grpc.stub.StreamObserver<db.DatabaseServiceProto.GetRecordResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetRecordMethod(), responseObserver);
    }

    /**
     */
    default void addRecord(db.DatabaseServiceProto.AddRecordRequest request,
        io.grpc.stub.StreamObserver<db.DatabaseServiceProto.AddRecordResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddRecordMethod(), responseObserver);
    }

    /**
     */
    default void getSize(db.DatabaseServiceProto.GetSizeRequest request,
        io.grpc.stub.StreamObserver<db.DatabaseServiceProto.GetSizeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetSizeMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service DatabaseService.
   * <pre>
   * gRPC service interface: every rpc is one remote operation with its own
   * request/response message types. protoc + the grpc plugin generate the
   * client stub and the server base class from this.
   * </pre>
   */
  public static abstract class DatabaseServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return DatabaseServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service DatabaseService.
   * <pre>
   * gRPC service interface: every rpc is one remote operation with its own
   * request/response message types. protoc + the grpc plugin generate the
   * client stub and the server base class from this.
   * </pre>
   */
  public static final class DatabaseServiceStub
      extends io.grpc.stub.AbstractAsyncStub<DatabaseServiceStub> {
    private DatabaseServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DatabaseServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DatabaseServiceStub(channel, callOptions);
    }

    /**
     */
    public void getRecord(db.DatabaseServiceProto.GetRecordRequest request,
        io.grpc.stub.StreamObserver<db.DatabaseServiceProto.GetRecordResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetRecordMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addRecord(db.DatabaseServiceProto.AddRecordRequest request,
        io.grpc.stub.StreamObserver<db.DatabaseServiceProto.AddRecordResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddRecordMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getSize(db.DatabaseServiceProto.GetSizeRequest request,
        io.grpc.stub.StreamObserver<db.DatabaseServiceProto.GetSizeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetSizeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service DatabaseService.
   * <pre>
   * gRPC service interface: every rpc is one remote operation with its own
   * request/response message types. protoc + the grpc plugin generate the
   * client stub and the server base class from this.
   * </pre>
   */
  public static final class DatabaseServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<DatabaseServiceBlockingV2Stub> {
    private DatabaseServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DatabaseServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DatabaseServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public db.DatabaseServiceProto.GetRecordResponse getRecord(db.DatabaseServiceProto.GetRecordRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetRecordMethod(), getCallOptions(), request);
    }

    /**
     */
    public db.DatabaseServiceProto.AddRecordResponse addRecord(db.DatabaseServiceProto.AddRecordRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getAddRecordMethod(), getCallOptions(), request);
    }

    /**
     */
    public db.DatabaseServiceProto.GetSizeResponse getSize(db.DatabaseServiceProto.GetSizeRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetSizeMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service DatabaseService.
   * <pre>
   * gRPC service interface: every rpc is one remote operation with its own
   * request/response message types. protoc + the grpc plugin generate the
   * client stub and the server base class from this.
   * </pre>
   */
  public static final class DatabaseServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<DatabaseServiceBlockingStub> {
    private DatabaseServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DatabaseServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DatabaseServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public db.DatabaseServiceProto.GetRecordResponse getRecord(db.DatabaseServiceProto.GetRecordRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetRecordMethod(), getCallOptions(), request);
    }

    /**
     */
    public db.DatabaseServiceProto.AddRecordResponse addRecord(db.DatabaseServiceProto.AddRecordRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddRecordMethod(), getCallOptions(), request);
    }

    /**
     */
    public db.DatabaseServiceProto.GetSizeResponse getSize(db.DatabaseServiceProto.GetSizeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetSizeMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service DatabaseService.
   * <pre>
   * gRPC service interface: every rpc is one remote operation with its own
   * request/response message types. protoc + the grpc plugin generate the
   * client stub and the server base class from this.
   * </pre>
   */
  public static final class DatabaseServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<DatabaseServiceFutureStub> {
    private DatabaseServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DatabaseServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DatabaseServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<db.DatabaseServiceProto.GetRecordResponse> getRecord(
        db.DatabaseServiceProto.GetRecordRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetRecordMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<db.DatabaseServiceProto.AddRecordResponse> addRecord(
        db.DatabaseServiceProto.AddRecordRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddRecordMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<db.DatabaseServiceProto.GetSizeResponse> getSize(
        db.DatabaseServiceProto.GetSizeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetSizeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_RECORD = 0;
  private static final int METHODID_ADD_RECORD = 1;
  private static final int METHODID_GET_SIZE = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_RECORD:
          serviceImpl.getRecord((db.DatabaseServiceProto.GetRecordRequest) request,
              (io.grpc.stub.StreamObserver<db.DatabaseServiceProto.GetRecordResponse>) responseObserver);
          break;
        case METHODID_ADD_RECORD:
          serviceImpl.addRecord((db.DatabaseServiceProto.AddRecordRequest) request,
              (io.grpc.stub.StreamObserver<db.DatabaseServiceProto.AddRecordResponse>) responseObserver);
          break;
        case METHODID_GET_SIZE:
          serviceImpl.getSize((db.DatabaseServiceProto.GetSizeRequest) request,
              (io.grpc.stub.StreamObserver<db.DatabaseServiceProto.GetSizeResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetRecordMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              db.DatabaseServiceProto.GetRecordRequest,
              db.DatabaseServiceProto.GetRecordResponse>(
                service, METHODID_GET_RECORD)))
        .addMethod(
          getAddRecordMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              db.DatabaseServiceProto.AddRecordRequest,
              db.DatabaseServiceProto.AddRecordResponse>(
                service, METHODID_ADD_RECORD)))
        .addMethod(
          getGetSizeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              db.DatabaseServiceProto.GetSizeRequest,
              db.DatabaseServiceProto.GetSizeResponse>(
                service, METHODID_GET_SIZE)))
        .build();
  }

  private static abstract class DatabaseServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DatabaseServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return db.DatabaseServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DatabaseService");
    }
  }

  private static final class DatabaseServiceFileDescriptorSupplier
      extends DatabaseServiceBaseDescriptorSupplier {
    DatabaseServiceFileDescriptorSupplier() {}
  }

  private static final class DatabaseServiceMethodDescriptorSupplier
      extends DatabaseServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    DatabaseServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (DatabaseServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DatabaseServiceFileDescriptorSupplier())
              .addMethod(getGetRecordMethod())
              .addMethod(getAddRecordMethod())
              .addMethod(getGetSizeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
