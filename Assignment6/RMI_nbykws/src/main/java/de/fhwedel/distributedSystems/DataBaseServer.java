package de.fhwedel.distributedSystems;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.Remote;
import java.rmi.server.UnicastRemoteObject;

public class DataBaseServer {

    public static void main(String[] args) {
        try {
            DataBaseImpl db = new DataBaseImpl();

            DataBase stub = (DataBase) UnicastRemoteObject.exportObject(db, 0);

            writeStubToFile("database.stub", stub);
            System.out.println("DataBase-Server is running, stub is written in database.stub");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeStubToFile(String fileName, Remote stub)
            throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(stub);
        out.close();
    }
}
