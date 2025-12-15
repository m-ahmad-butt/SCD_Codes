package org.example.sockets.chatApp;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Multi-User Chat Server
 * Supports multiple simultaneous users with public and private messaging
 * Run this first, then run multiple ChatClient instances
 */
public class ChatServer {
    private static final int PORT = 5001;
    private static Map<String, ClientHandler> clients = new ConcurrentHashMap<>();
    
    public static void main(String[] args) {
        System.out.println("---------------------------------");
        System.out.println("Chat Server started on port " + PORT);
        System.out.println("Waiting for clients to connect...");
        System.out.println("---------------------------------");
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New connection from: " + socket.getInetAddress());
                ClientHandler clientHandler = new ClientHandler(socket);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Broadcast message to all connected clients
    public static void broadcast(String message, String sender) {
        for (Map.Entry<String, ClientHandler> entry : clients.entrySet()) {
            if (!entry.getKey().equals(sender)) {
                entry.getValue().sendMessage(message);
            }
        }
    }
    
    // Send private message to specific user
    public static boolean sendPrivateMessage(String recipient, String message) {
        ClientHandler client = clients.get(recipient);
        if (client != null) {
            client.sendMessage(message);
            return true;
        }
        return false;
    }
    
    // Add client to the chat
    public static boolean addClient(String username, ClientHandler handler) {
        if (clients.containsKey(username)) {
            return false; // Username already exists
        }
        clients.put(username, handler);
        System.out.println("User '" + username + "' joined. Total users: " + clients.size());
        broadcast("*** " + username + " joined the chat ***", username);
        return true;
    }
    
    // Remove client from the chat
    public static void removeClient(String username) {
        clients.remove(username);
        System.out.println("User '" + username + "' left. Total users: " + clients.size());
        broadcast("*** " + username + " left the chat ***", username);
    }
    
    // Get list of all connected users
    public static Set<String> getUsers() {
        return clients.keySet();
    }
}
