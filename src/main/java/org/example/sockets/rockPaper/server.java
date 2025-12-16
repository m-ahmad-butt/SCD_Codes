package org.example.sockets.rockPaper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server{
    public static void main(String[] args) {
        String winner = "";
        boolean isGameOver = false;
        ServerSocket s = null;
        Socket s1 = null,s2=null;
        try{
            s = new ServerSocket(9000);
            s1 = s.accept();
            s2 = s.accept();
            
            //for s1
            BufferedReader in1 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
            PrintWriter out1 = new PrintWriter(s1.getOutputStream(),true);

            //for s2
            BufferedReader in2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
            PrintWriter out2 = new PrintWriter(s2.getOutputStream(),true);

            while(!isGameOver){
                out1.println("Enter R/P/S: ");
                out2.println("Enter R/P/S: ");

                String P1 = in1.readLine();
                String P2 = in2.readLine();

                if (P1 == null || P2 == null) {
                    break;
                }
                
                P1 = P1.trim().toUpperCase();
                P2 = P2.trim().toUpperCase();

                if (P1.equals(P2)) {
                    winner = "Draw";
                } else if (P1.equals("R") && P2.equals("S")) {
                    winner = "P1";
                    isGameOver = true;
                } else if (P1.equals("R") && P2.equals("P")) {
                    winner = "P2";
                    isGameOver = true;
                } else if (P1.equals("P") && P2.equals("R")) {
                    winner = "P1";
                    isGameOver = true;
                } else if (P1.equals("P") && P2.equals("S")) {
                    winner = "P2";
                    isGameOver = true;
                } else if (P1.equals("S") && P2.equals("P")) {
                    winner = "P1";
                    isGameOver = true;
                } else if (P1.equals("S") && P2.equals("R")) {
                    winner = "P2";
                    isGameOver = true;
                }

                out1.println("P1: " + P1 + ", P2: " + P2 + " => Winner: " + winner);
                out2.println("P1: " + P1 + ", P2: " + P2 + " => Winner: " + winner);
            }            

        }catch(Exception e){}
        finally{
            try{
                s1.close();
                s2.close();
                s.close();
            }catch(IOException e){}
        }
    }
}