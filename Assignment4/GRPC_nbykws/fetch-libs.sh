#!/usr/bin/env bash
# Holt das protoc-gRPC-Plugin und die gRPC-Runtime-jars von Maven Central.
# Einmalig ausfuehren:  ./fetch-libs.sh
# Danach liegen Plugin + jars im Ordner bzw. in lib/.
set -e
cd "$(dirname "$0")"
mkdir -p lib

M=https://repo1.maven.org/maven2
GRPC=1.77.1        # muss zur protoc-gen-grpc-java-Version passen
PROTOBUF=4.36.1    # muss zu protoc 36.1 / protobuf-java passen

# protoc-Plugin (generiert db/DatabaseServiceGrpc.java)
curl -sSfL -o protoc-gen-grpc-java \
  "$M/io/grpc/protoc-gen-grpc-java/$GRPC/protoc-gen-grpc-java-$GRPC-linux-x86_64.exe"
chmod +x protoc-gen-grpc-java

# gRPC Laufzeit-jars
for a in grpc-api grpc-core grpc-netty-shaded grpc-protobuf grpc-protobuf-lite grpc-stub grpc-util; do
  curl -sSfL -o "lib/$a-$GRPC.jar" "$M/io/grpc/$a/$GRPC/$a-$GRPC.jar"
done

# protobuf-java (Runtime) + Util
curl -sSfL -o "lib/protobuf-java-$PROTOBUF.jar" \
  "$M/com/google/protobuf/protobuf-java/$PROTOBUF/protobuf-java-$PROTOBUF.jar"
curl -sSfL -o "lib/protobuf-java-util-$PROTOBUF.jar" \
  "$M/com/google/protobuf/protobuf-java-util/$PROTOBUF/protobuf-java-util-$PROTOBUF.jar"

# Abhaengigkeiten der jars (bei 404 einfach Version in maven-metadata.xml nachschauen)
curl -sSfL -o lib/guava-33.5.0-jre.jar \
  "$M/com/google/guava/guava/33.5.0-jre/guava-33.5.0-jre.jar"
curl -sSfL -o lib/failureaccess-1.0.3.jar \
  "$M/com/google/guava/failureaccess/1.0.3/failureaccess-1.0.3.jar"
curl -sSfL -o lib/error_prone_annotations-2.41.0.jar \
  "$M/com/google/errorprone/error_prone_annotations/2.41.0/error_prone_annotations-2.41.0.jar"
curl -sSfL -o lib/animal-sniffer-annotations-1.24.jar \
  "$M/org/codehaus/mojo/animal-sniffer-annotations/1.24/animal-sniffer-annotations-1.24.jar"
curl -sSfL -o lib/perfmark-api-0.27.0.jar \
  "$M/io/perfmark/perfmark-api/0.27.0/perfmark-api-0.27.0.jar"
curl -sSfL -o lib/jsr305-3.0.2.jar \
  "$M/com/google/code/findbugs/jsr305/3.0.2/jsr305-3.0.2.jar"
curl -sSfL -o lib/gson-2.12.1.jar \
  "$M/com/google/code/gson/gson/2.12.1/gson-2.12.1.jar"
curl -sSfL -o lib/checker-qual-3.51.0.jar \
  "$M/org/checkerframework/checker-qual/3.51.0/checker-qual-3.51.0.jar"
curl -sSfL -o lib/j2objc-annotations-3.1.jar \
  "$M/com/google/j2objc/j2objc-annotations/3.1/j2objc-annotations-3.1.jar"

echo "OK - Plugin und jars liegen jetzt in diesem Ordner / lib/"

# Danach Code generieren (aus diesem Ordner; env -u LD_LIBRARY_PATH nur noetig,
# wenn protoc sonst GLIBCXX-Fehler meldet):
#   env -u LD_LIBRARY_PATH protoc --java_out=. --grpc_out=. \
#     --plugin=protoc-gen-grpc=./protoc-gen-grpc-java DatabaseService.proto
# Achtung: --grpc_out sucht ein Plugin namens "protoc-gen-grpc" (nicht -java!),
# deshalb das --plugin= Mapping auf das heruntergeladene protoc-gen-grpc-java.
# Kompilieren:  javac -cp "lib/*" db/*.java DbGrpcServer.java DbGrpcClient.java
