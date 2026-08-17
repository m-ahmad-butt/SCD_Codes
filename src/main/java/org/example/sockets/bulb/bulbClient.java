package org.example.sockets.bulb;

import java.io.PrintWriter;
import java.net.Socket;

class BulbClientThread implements Runnable {
    private int clientId;
    private String host;
    private int port;
    
    public BulbClientThread(int clientId, String host, int port) {
        this.clientId = clientId;
        this.host = host;
        this.port = port;
    }
    
    @Override
    public void run() {
        try {
            Socket soc = new Socket(host, port);
            PrintWriter pr = new PrintWriter(soc.getOutputStream(), true);
            System.out.println("[Client #" + clientId + "] Connected to server");
            
            while(true){
                int rand = (int)(Math.random() * 100);
                String command;
                if(rand > 50){
                    command = "on";
                }
                else{
                    command = "off";
                }
                pr.println(command);
                System.out.println("[Client #" + clientId + "] Sent: " + command);
                Thread.sleep(1000 + (int)(Math.random() * 500)); // Variable delay
            }
        }
        catch (Exception e){
            System.out.println("[Client #" + clientId + " Error] " + e.getMessage());
        }
    }
}

public class BulbClient {
    private static final String HOST = "localhost";
    private static final int PORT = 9000;
    private static final int NUM_CLIENTS = 3; // Number of concurrent client threads
    
    public static void main(String[] args) {
        System.out.println("[Client] Starting " + NUM_CLIENTS + " client threads...");
        
        for(int i = 0; i < NUM_CLIENTS; i++) {
            Thread clientThread = new Thread(new BulbClientThread(i + 1, HOST, PORT));
            clientThread.start();
            
            // Small delay between starting threads
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("[Client] All client threads started");
    }
}
