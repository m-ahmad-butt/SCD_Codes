package org.example.RMI_sockets.chat;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Scanner;

/**
 * RMI Chat Client
 * Connects to RMI Chat Server and provides interactive chat interface
 * 
 * Usage:
 * 1. Make sure RMIChatServer is running first
 * 2. Run this class (can run multiple instances for multiple users)
 * 3. Enter your username when prompted
 * 4. Start chatting!
 * 
 * Commands:
 * - Regular messages: broadcast to all users (public mode)
 * - /private <username> <message>: send private message
 * - /users: list all connected users
 * - /quit: disconnect
 */
public class RMIChatClient extends UnicastRemoteObject implements IChatClient {
    
    private String username;
    private IChatService chatService;
    private Scanner scanner;
    private volatile boolean running;
    
    public RMIChatClient(String username) throws RemoteException {
        super();
        this.username = username;
        this.scanner = new Scanner(System.in);
        this.running = true;
    }
    
    @Override
    public void receiveMessage(String message) throws RemoteException {
        System.out.println(message);
    }
    
    @Override
    public String getUsername() throws RemoteException {
        return username;
    }
    
    /**
     * Connect to the chat server
     */
    public boolean connect(String serverAddress, int port) {
        try {
            Registry registry = LocateRegistry.getRegistry(serverAddress, port);
            chatService = (IChatService) registry.lookup("ChatService");
            
            // Register with the server
            if (chatService.register(username, this)) {
                System.out.println("--------------------------------");
                System.out.println("Connected to chat server!");
                System.out.println("Welcome, " + username + "!");
                System.out.println("--------------------------------");
                System.out.println("Commands:");
                System.out.println("  /users - List all connected users");
                System.out.println("  /private <user> <message> - Send private message");
                System.out.println("  /quit - Disconnect from chat");
                System.out.println("--------------------------------");
                System.out.println("Type your message to broadcast to all users");
                System.out.println("");
                return true;
            } else {
                System.out.println("Failed to register. Username might be taken.");
                return false;
            }
            
        } catch (Exception e) {
            System.err.println("Failed to connect to server: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Start the chat session
     */
    public void start() {
        String input;
        
        while (running) {
            try {
                input = scanner.nextLine();
                
                if (input == null || input.trim().isEmpty()) {
                    continue;
                }
                
                if (input.equals("/quit")) {
                    disconnect();
                    break;
                    
                } else if (input.equals("/users")) {
                    handleUsersCommand();
                    
                } else if (input.startsWith("/private ")) {
                    handlePrivateMessage(input);
                    
                } else {
                    // Send public message
                    chatService.sendPublicMessage(username, input);
                    System.out.println("[You]: " + input);
                }
                
            } catch (RemoteException e) {
                System.err.println("Communication error: " + e.getMessage());
                break;
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
    
    /**
     * Handle /users command
     */
    private void handleUsersCommand() throws RemoteException {
        List<String> users = chatService.getConnectedUsers();
        System.out.println("=================================");
        System.out.println("Connected Users (" + users.size() + "):");
        System.out.println("=================================");
        for (String user : users) {
            if (user.equals(username)) {
                System.out.println("  → " + user + " (you)");
            } else {
                System.out.println("  → " + user);
            }
        }
        System.out.println("---------------------------------");
    }
    
    /**
     * Handle /private command
     */
    private void handlePrivateMessage(String input) throws RemoteException {
        String[] parts = input.split(" ", 3);
        
        if (parts.length < 3) {
            System.out.println("Usage: /private <username> <message>");
            return;
        }
        
        String recipient = parts[1];
        String message = parts[2];
        
        if (recipient.equals(username)) {
            System.out.println("You cannot send a message to yourself!");
            return;
        }
        
        boolean sent = chatService.sendPrivateMessage(username, recipient, message);
        if (sent) {
            System.out.println("[Private to " + recipient + "]: " + message);
        } else {
            System.out.println("User '" + recipient + "' not found!");
        }
    }
    
    /**
     * Disconnect from the server
     */
    private void disconnect() {
        running = false;
        try {
            if (chatService != null) {
                chatService.unregister(username);
            }
            System.out.println("Disconnected from chat server. Goodbye!");
        } catch (RemoteException e) {
            System.err.println("Error during disconnect: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("---------------------------------");
        System.out.println("RMI Chat Client");
        System.out.println("---------------------------------");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine().trim();
        
        if (username.isEmpty()) {
            System.out.println("Username cannot be empty!");
            return;
        }
        
        try {
            RMIChatClient client = new RMIChatClient(username);
            
            String serverAddress = "localhost";
            int port = 1099;
            
            System.out.println("Connecting to " + serverAddress + ":" + port + "...");
            
            if (client.connect(serverAddress, port)) {
                client.start();
            } else {
                System.out.println("Failed to connect. Make sure the server is running.");
            }
            
        } catch (RemoteException e) {
            System.err.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
