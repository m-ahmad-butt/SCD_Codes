package org.example.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote Interface
 * - Must extend Remote
 * - All methods must throw RemoteException
 */
public interface RemoteService extends Remote {
    String sort(String data) throws RemoteException;
    int add(int a, int b) throws RemoteException;
}
