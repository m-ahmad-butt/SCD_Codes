package org.example.sockets.bulb;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class bulbClient {
    public static void main(String[] args) {
        try{
            Socket soc = new Socket("localhost", 9000);
            PrintWriter pr = new PrintWriter(soc.getOutputStream(), true);
            while(true){
                int rand =  (int)(Math.random() * 100);
                if(rand > 50){
                    pr.println("on");
                }
                else{
                    pr.println("off");
                }
                Thread.sleep(1000);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
