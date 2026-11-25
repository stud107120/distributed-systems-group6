package de.fhwedel.distributedSystems;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class DataBaseImpl implements DataBase {

    private HashMap<Integer, String> database = new HashMap<Integer, String>();

    public DataBaseImpl() throws RemoteException {}

    @Override
    public String getRecord(int index) throws RemoteException {
        return database.get(index);        
    }

    @Override
    public int getIndex(String record) throws RemoteException {
        for (Map.Entry<Integer, String> e : database.entrySet()) {
            if(e.getValue().equals(record)){
                return e.getKey();
            }        
        }        
        return -1; 
    }

    @Override
    public void addRecord(int index, String record) throws RemoteException {
        database.put(index, record);
        System.out.println("Put " + record + " in database with index: " + index);   
    }

    @Override
    public int getSize() throws RemoteException {
        return database.size();        
    }

    @Override
    public DBResult getRecordObj(int index) throws RemoteException {
        String record = database.get(index);
        if (record == null) return null;
        DBResult res = new DBResult();
        res.setKey(index);
        res.setValue(record);
        return res;
    }
}
