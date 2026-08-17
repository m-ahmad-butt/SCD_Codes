package org.example.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

/**
 * Server Implementation
 * - Extends UnicastRemoteObject
 * - Implements Remote Interface
 * - Constructor must call super() and throw RemoteException
 */
public class BestServer extends UnicastRemoteObject implements RemoteService {
    
    // Constructor
    public BestServer() throws RemoteException {
        super();
    }
    
    @Override
    public String sort(String data) throws RemoteException {
        char[] chars = data.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
    
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
