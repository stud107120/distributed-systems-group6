package de.fhwedel.distributedSystems;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DataBaseClient {

    public static void main(String[] args) {
        try {
            DataBase remoteObj = readStubFromFile("database.stub");

            System.out.println();
            remoteObj.addRecord(4101, "Appen");
            remoteObj.addRecord(4102, "Ahrensburg");
            remoteObj.addRecord(4103, "Wedel");
            remoteObj.addRecord(4104, "Aumuehle");
            remoteObj.addRecord(4105, "Seevetal");
            remoteObj.addRecord(4106, "Quickborn");
 
            System.out.println(remoteObj.getRecord(4103));
            System.out.println(remoteObj.getRecord(4107));
 
            System.out.println(remoteObj.getSize());

            System.out.println(remoteObj.getIndex("Appen"));
            System.out.println(remoteObj.getIndex("Hamburg"));

            DBResult res = remoteObj.getRecordObj(4105);
            System.out.println(res.getKey() + " -> " + res.getValue());
            //res = remoteObj.getRecordObj(4107);
            //System.out.println(res.getKey() + " -> " + res.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static DataBase readStubFromFile(String fileName)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        /* Deserialize stub using Java Serialization */
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fis);
        DataBase remoteObj = (DataBase) in.readObject();
        in.close();
        return remoteObj;
    }
}
