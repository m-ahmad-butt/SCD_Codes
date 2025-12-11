package org.example.sockets;

import java.io.*;
import java.net.*;

/**
 * Chat Client - connects to ChatServer
 * 
 * Usage:
 * 1. Make sure ChatServer is running first
 * 2. Run this class (can run multiple instances for multiple users)
 * 3. Enter your username when prompted
 * 4. Start chatting!
 * 
 * Commands:
 * - Regular messages: broadcast to all users
 * - /private <username> <message>: send private message
 * - /users: list all connected users
 * - /quit: disconnect
 */
public class ChatClient {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private BufferedReader consoleReader;
    private volatile boolean running = true;
    
    public ChatClient(String serverAddress, int port) {
        try {
            socket = new Socket(serverAddress, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            consoleReader = new BufferedReader(new InputStreamReader(System.in));
            
            // Start thread to listen for server messages
            new Thread(new ServerListener()).start();
            
            // Read user input and send to server
            String userInput;
            while (running && (userInput = consoleReader.readLine()) != null) {
                writer.println(userInput);
                if (userInput.equals("/quit")) {
                    running = false;
                    break;
                }
            }
            
        } catch (IOException e) {
            System.out.println("Error connecting to server: " + e.getMessage());
            System.out.println("Make sure ChatServer is running on " + serverAddress + ":" + port);
        } finally {
            cleanup();
        }
    }
    
    // Thread to listen for incoming messages from server
    class ServerListener implements Runnable {
        @Override
        public void run() {
            try {
                String message;
                while (running && (message = reader.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                if (running) {
                    System.out.println("Disconnected from server");
                }
            }
        }
    }
    
    private void cleanup() {
        running = false;
        try {
            if (socket != null) socket.close();
            if (reader != null) reader.close();
            if (writer != null) writer.close();
            if (consoleReader != null) consoleReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 5001;
        
        System.out.println("---------------------------------");
        System.out.println("Connecting to chat server...");
        System.out.println("Server: " + serverAddress + ":" + port);
        System.out.println("---------------------------------");
        
        new ChatClient(serverAddress, port);
    }
}
