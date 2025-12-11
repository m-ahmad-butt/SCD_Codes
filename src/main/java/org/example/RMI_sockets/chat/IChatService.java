package org.example.RMI_sockets.chat;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Remote Interface for Chat Service
 * Defines methods that can be called remotely by clients
 */
public interface IChatService extends Remote {
    
    /**
     * Register a new user with the chat service
     * @param username The username to register
     * @param client The client callback interface
     * @return true if registration successful, false if username already exists
     */
    boolean register(String username, IChatClient client) throws RemoteException;
    
    /**
     * Unregister a user from the chat service
     * @param username The username to unregister
     */
    void unregister(String username) throws RemoteException;
    
    /**
     * Send a public message to all connected users
     * @param sender The username of the sender
     * @param message The message content
     */
    void sendPublicMessage(String sender, String message) throws RemoteException;
    
    /**
     * Send a private message to a specific user
     * @param sender The username of the sender
     * @param recipient The username of the recipient
     * @param message The message content
     * @return true if message sent successfully, false if recipient not found
     */
    boolean sendPrivateMessage(String sender, String recipient, String message) throws RemoteException;
    
    /**
     * Get list of all connected users
     * @return List of usernames
     */
    List<String> getConnectedUsers() throws RemoteException;
}
