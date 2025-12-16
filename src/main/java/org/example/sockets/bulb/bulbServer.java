package org.example.sockets.bulb;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicInteger;

class Bulb implements Runnable{
    private Integer id;
    private Integer runTime = 0;
    private Socket soc;
    private Integer threshold = 2;
    
    Bulb(Socket soc, Integer threshold, Integer id){
        this.soc = soc;
        this.threshold = threshold;
        this.id = id;
    }
    
    @Override
    public void run(){
        try {
            System.out.println("[Server] Bulb #" + this.id + " connected");
            BufferedReader br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            String status;
            while ((status = br.readLine()) != null){
                if(status.equals("on")){
                        this.runTime++;
                    System.out.println("[Bulb #" + this.id + "] on (Runtime: " + this.runTime + ")");
                }
                else if(status.equals("off")){
                        this.runTime = 0;
                    System.out.println("[Bulb #" + this.id + "] off");
                }
                // check threshold
                    if(this.runTime >= this.threshold){
                        System.out.println("[WARNING] Bulb #" + this.id + " ran for "+ this.runTime + " and exceeded threshold (" + this.threshold + ")");
                }
            }
        } catch (IOException e) {
            System.out.println("[Server] Bulb #" + this.id + " disconnected");
        } finally {
            try {
                soc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

public class bulbServer {
    private static Integer threshold = 3;
    private static Integer port = 9000;
    // Using AtomicInteger for thread-safe counter
    private static AtomicInteger idCounter = new AtomicInteger(0);
    
    public static void main(String[] args) {
        System.out.println("[Server] Starting bulb server on port " + port + "...");
        try{
            ServerSocket server = new ServerSocket(port);
            System.out.println("[Server] Waiting for clients...");
            
            while(true){
                Socket soc = server.accept();
                int bulbId = idCounter.getAndIncrement();
                Thread t = new Thread(new Bulb(soc, threshold, bulbId));
                t.start();
            }

        }catch (Exception e){
            System.out.println("[Server Error] " + e.getMessage());
            e.printStackTrace();
        }

    }
}
