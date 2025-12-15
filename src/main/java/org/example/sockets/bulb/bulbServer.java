package org.example.sockets.bulb;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

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
            BufferedReader br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            String status;
            while ((status = br.readLine()) != null){
                if(status.equals("on")){
                    this.runTime++;
                    System.out.println("on");
                }
                else if(status.equals("off")){
                    System.out.println("off");
                    this.runTime = 0;
                }
                // check threshold
                if(this.runTime >= this.threshold){
                    System.out.println("Bulb number " + this.id + " ran for "+ this.runTime + " and exceeded threshold");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

public class bulbServer {
    private static Integer threshold = 3;
    private static Integer port = 9000;
    private static Integer idCounter = 0;
    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(port);
            while(true){
                Socket soc = server.accept();
                Thread t = new Thread(new Bulb(soc,threshold,idCounter++));
                t.start();
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
