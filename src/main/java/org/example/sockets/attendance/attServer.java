package org.example.sockets.attendance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.example.sockets.numGuessingGame_v2.server;

class Attendance implements Runnable {

    private Socket s;

    Attendance(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            BufferedReader b = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));

            String name = b.readLine();
            String roll = b.readLine();
            String attend = b.readLine();

            synchronized (System.out) {
                System.out.println(name + " with " + roll + " roll number is " + attend);
            }

        } catch (Exception e) {
        } finally {
            try {
                s.close();
            } catch (Exception e) {
            }
        }
    }
}

public class attServer {
    public static void main(String[] args) {
        ServerSocket server =null;
        try {
             server = new ServerSocket(9000);
            System.out.println("Server started");

            while (true) {
                Socket s = server.accept();
                Thread t = new Thread(new Attendance(s));
                t.start();
            }

        } catch (Exception e) {
        }
        finally{
            try{
                server.close();
            }catch(IOException e){}
        }
    }
}
