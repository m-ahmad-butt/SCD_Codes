package org.example.sockets.measureo2;
import java.io.*;
import java.net.*;

public class O2Client {
    public static void main(String[] args) {
        Socket socket;

        try {
            socket = new Socket("localhost", 4444);
            PrintWriter socketwriter = new PrintWriter(socket.getOutputStream());

            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        while (true) {
                            int value = measureO2level();
                            socketwriter.println("" + value);
                            socketwriter.flush();
                            Thread.sleep(1000);
                        }
                    } catch (Exception ex) {
                        System.out.println("Exception:" + ex.getMessage());
                    }
                }
            });

            thread.start();
            thread.join();
            socket.close();

        } catch (Exception ex) {
            System.out.println("Exception:" + ex.getMessage());
        }
    }

    private static int measureO2level() {
        return ((int) (Math.random() * 30)) + 69;
    }
}
