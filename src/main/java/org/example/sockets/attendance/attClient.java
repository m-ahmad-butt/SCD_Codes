package org.example.sockets.attendance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

class attClientThread implements Runnable {

    private static final Object lock = new Object();
    private static final BufferedReader b =
            new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void run() {
        Socket s = null;
        try {
            s = new Socket("localhost", 9000);
            PrintWriter p = new PrintWriter(s.getOutputStream(), true);

            String name;
            String rollNum;
            String attend;

            synchronized (lock) {
                name = b.readLine();
                rollNum = b.readLine();
                attend = b.readLine();
            }

            p.println(name);
            p.println(rollNum);
            p.println(attend);

        } catch (Exception e) {
        } finally {
            try {
                s.close();
            } catch (IOException e) {
            }
        }
    }
}

public class attClient {
    public static void main(String[] args) {
        int i = 0;
        while (i < Attendance._std) {
            Thread th = new Thread(new attClientThread());
            th.start();
            i++;
        }
    }
}
