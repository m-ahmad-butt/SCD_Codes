package org.example.sockets.measureO2;

import java.io.*;
import java.net.*;

public class O2Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(4444);
            System.out.println("Server has started!");

            while (true) {
                Socket s = serverSocket.accept();
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        try {
                            //in
                            InputStream in = s.getInputStream();
                            BufferedReader i = new BufferedReader(new InputStreamReader(in));

                            String str;
                            while ((str = i.readLine()) != null) {
                                int value = Integer.parseInt(str);
                                if (value < 80) System.out.println("Patient has Oxygen level less than 80");
                            }
                        } catch (IOException e) {
                        } finally {
                            try {
                                s.close();
                            } catch (IOException e) {
                            }
                        }
                    }
                }
                );

                t.start();
            }

        } catch (IOException e) {
        }
    }
}
