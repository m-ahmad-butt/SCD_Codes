package org.example.RMI_sockets.chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote Interface for Chat Client Callbacks
 * Allows server to push messages to clients
 */
public interface IChatClient extends Remote {
    
    /**
     * Receive a message from the server
     * @param message The message to display
     */
    void receiveMessage(String message) throws RemoteException;
    
    /**
     * Get the username of this client
     * @return The username
     */
    String getUsername() throws RemoteException;
}
