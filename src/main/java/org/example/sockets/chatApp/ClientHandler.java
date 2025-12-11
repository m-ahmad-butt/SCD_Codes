package org.example.sockets;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Handles individual client connections on the server side
 * Each connected client gets its own ClientHandler thread
 */
class ClientHandler implements Runnable {
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private String username;
    
    public ClientHandler(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            
            // Get username from client
            writer.println("Enter your username:");
            username = reader.readLine();
            
            // Check if username is unique
            while (username == null || username.trim().isEmpty() || !ChatServer.addClient(username, this)) {
                writer.println("Username already taken or invalid. Try another:");
                username = reader.readLine();
            }
            
            writer.println("---------------------------------");
            writer.println("Welcome to the chat, " + username + "!");
            writer.println("---------------------------------");
            writer.println("Commands:");
            writer.println("  /users - List all connected users");
            writer.println("  /private <user> <message> - Send private message");
            writer.println("  /quit - Disconnect from chat");
            writer.println("---------------------------------");
            writer.println("Type your message to broadcast to all users");
            writer.println("");
            
            // Handle client messages
            String message;
            while ((message = reader.readLine()) != null) {
                if (message.startsWith("/quit")) {
                    writer.println("Goodbye!");
                    break;
                } else if (message.startsWith("/users")) {
                    handleUsersCommand();
                } else if (message.startsWith("/private ")) {
                    handlePrivateMessage(message);
                } else if (!message.trim().isEmpty()) {
                    // Public broadcast
                    ChatServer.broadcast("[" + username + "]: " + message, username);
                    writer.println("[You]: " + message);
                }
            }
            
        } catch (IOException e) {
            System.out.println("Error handling client " + username + ": " + e.getMessage());
        } finally {
            cleanup();
        }
    }
    
    private void handleUsersCommand() {
        Set<String> users = ChatServer.getUsers();
        writer.println("---------------------------------");
        writer.println("Connected Users (" + users.size() + "):");
        writer.println("---------------------------------");
        for (String user : users) {
            if (user.equals(username)) {
                writer.println("  → " + user + " (you)");
            } else {
                writer.println("  → " + user);
            }
        }
        writer.println("---------------------------------");
    }
    
    private void handlePrivateMessage(String message) {
        String[] parts = message.split(" ", 3);
        if (parts.length < 3) {
            writer.println("Usage: /private <username> <message>");
            return;
        }
        
        String recipient = parts[1];
        String privateMsg = parts[2];
        
        if (recipient.equals(username)) {
            writer.println("You cannot send a message to yourself!");
            return;
        }
        
        boolean sent = ChatServer.sendPrivateMessage(recipient, "[Private from " + username + "]: " + privateMsg);
        if (sent) {
            writer.println("[Private to " + recipient + "]: " + privateMsg);
        } else {
            writer.println("User '" + recipient + "' not found!");
        }
    }
    
    public void sendMessage(String message) {
        writer.println(message);
    }
    
    private void cleanup() {
        try {
            if (username != null) {
                ChatServer.removeClient(username);
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
