package org.example.RMI_sockets.chat;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RMI Chat Server Implementation
 * Manages multiple clients and handles message routing
 * 
 * Run this first before running any clients
 */
public class RMIChatServer extends UnicastRemoteObject implements IChatService {
    
    private static final int RMI_PORT = 1099;
    private static final String SERVICE_NAME = "ChatService";
    
    // Store connected clients: username -> client callback
    private Map<String, IChatClient> connectedClients;
    
    public RMIChatServer() throws RemoteException {
        super();
        connectedClients = new ConcurrentHashMap<>();
    }
    
    @Override
    public boolean register(String username, IChatClient client) throws RemoteException {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        
        if (connectedClients.containsKey(username)) {
            return false; // Username already taken
        }
        
        connectedClients.put(username, client);
        System.out.println("User '" + username + "' registered. Total users: " + connectedClients.size());
        
        // Notify all other users
        broadcastSystemMessage(username + " joined the chat", username);
        
        return true;
    }
    
    @Override
    public void unregister(String username) throws RemoteException {
        if (connectedClients.remove(username) != null) {
            System.out.println("User '" + username + "' unregistered. Total users: " + connectedClients.size());
            broadcastSystemMessage(username + " left the chat", username);
        }
    }
    
    @Override
    public void sendPublicMessage(String sender, String message) throws RemoteException {
        System.out.println("[PUBLIC] " + sender + ": " + message);
        String formattedMessage = "[" + sender + "]: " + message;
        
        // Send to all clients except sender
        for (Map.Entry<String, IChatClient> entry : connectedClients.entrySet()) {
            if (!entry.getKey().equals(sender)) {
                try {
                    entry.getValue().receiveMessage(formattedMessage);
                } catch (RemoteException e) {
                    System.err.println("Failed to send message to " + entry.getKey());
                    // Remove dead client
                    connectedClients.remove(entry.getKey());
                }
            }
        }
    }
    
    @Override
    public boolean sendPrivateMessage(String sender, String recipient, String message) throws RemoteException {
        IChatClient recipientClient = connectedClients.get(recipient);
        
        if (recipientClient == null) {
            return false; // Recipient not found
        }
        
        System.out.println("[PRIVATE] " + sender + " -> " + recipient + ": " + message);
        
        try {
            recipientClient.receiveMessage("[Private from " + sender + "]: " + message);
            return true;
        } catch (RemoteException e) {
            System.err.println("Failed to send private message to " + recipient);
            connectedClients.remove(recipient);
            return false;
        }
    }
    
    @Override
    public List<String> getConnectedUsers() throws RemoteException {
        return new ArrayList<>(connectedClients.keySet());
    }
    
    /**
     * Broadcast system message to all users except specified one
     */
    private void broadcastSystemMessage(String message, String exceptUser) {
        String formattedMessage = "*** " + message + " ***";
        
        for (Map.Entry<String, IChatClient> entry : connectedClients.entrySet()) {
            if (!entry.getKey().equals(exceptUser)) {
                try {
                    entry.getValue().receiveMessage(formattedMessage);
                } catch (RemoteException e) {
                    System.err.println("Failed to send system message to " + entry.getKey());
                }
            }
        }
    }
    
    public static void main(String[] args) {
        try {
            System.out.println("--------------------------------");
            System.out.println("Starting RMI Chat Server...");
            System.out.println("--------------------------------");
            
            // Create and export the server object
            RMIChatServer server = new RMIChatServer();
            
            // Create RMI registry on specified port
            Registry registry = LocateRegistry.createRegistry(RMI_PORT);
            
            // Bind the server to the registry
            registry.rebind(SERVICE_NAME, server);
            
            System.out.println("RMI Chat Server is running on port " + RMI_PORT);
            System.out.println("Service name: " + SERVICE_NAME);
            System.out.println("Waiting for clients...");
            System.out.println("--------------------------------");
            
        } catch (RemoteException e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
