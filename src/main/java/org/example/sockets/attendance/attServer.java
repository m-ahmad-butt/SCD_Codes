package org.example.sockets.attendance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

class Attendance implements Runnable {
    public static Integer _std = 2;
    private static final Object lock = new Object(); //for synchronization

    public String name;
    public String rollNum;
    public String attend;
    private Socket s;

    public Attendance(Socket soc, String n, String r, String a) {
        s = soc;
        name = n;
        rollNum = r;
        attend = a;
    }

    @Override
    public void run() {
        try {
            BufferedReader b = new BufferedReader(new InputStreamReader(s.getInputStream()));

            String n = b.readLine();
            String r = b.readLine();
            String a = b.readLine();

            name = n;
            rollNum = r;
            attend = a;

            synchronized (lock) {
                System.out.println(name + " with " + rollNum + " roll number is " + attend);
            }

        } catch (Exception e) {
        } finally {
            try {
                s.close();
            } catch (IOException e) {
            }
        }
    }
}

public class attServer {
    public static void main(String[] args) {
        ServerSocket s = null;
        try {
            s = new ServerSocket(9000);
            int i = 0;
            while (i < Attendance._std) {
                Socket sc = s.accept();
                Thread th = new Thread(new Attendance(sc, null, null, null));
                th.start();
                i++;
            }
        } catch (Exception e) {
        } finally {
            try {
                s.close();
            } catch (IOException e) {
            }
        }
    }
}
