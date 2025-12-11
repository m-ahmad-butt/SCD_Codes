package org.example.RMI_sockets;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote Interface
 * - Must extend Remote
 * - All methods must throw RemoteException
 */
public interface I_Srvc extends Remote {
    String sort(String data) throws RemoteException;
    int add(int a, int b) throws RemoteException;
}
