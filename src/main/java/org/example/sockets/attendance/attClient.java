package org.example.sockets.attendance;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class attClientThread implements Runnable {

    private static final Object lock = new Object();
    private static final BufferedReader in =
            new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void run() {
        Socket s = null;
        try {
            s = new Socket("localhost", 9000);
            PrintWriter p = new PrintWriter(s.getOutputStream(), true);

            String n;
            String r;
            String a;

            synchronized (lock) {
                System.out.print("Name: ");
                n = in.readLine();
                System.out.print("Roll: ");
                r = in.readLine();
                System.out.print("Attendance: ");
                a = in.readLine();
            }

            p.println(n);
            p.println(r);
            p.println(a);

        } catch (Exception e) {
        } finally {
            try {
                s.close();
            } catch (Exception e) {
            }
        }
    }
}

public class attClient {
    public static void main(String[] args) {

        int students = 3;

        for (int i = 0; i < students; i++) {
            Thread t = new Thread(new attClientThread());
            t.start();
        }
    }
}
