package org.example.sockets.rockPaper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class client {
    public static void main(String[] args) {
        Socket s = null;
        try {
            s = new Socket("localhost", 9000);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            String msg;
            String msg2;
            while ((msg = in2.readLine()) != null) {
                System.out.println(msg);
                if (msg.contains("Winner")) {
                    break;
                }
                if (msg.startsWith("Enter")) {
                    msg2 = in.readLine();
                    out.println(msg2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (s != null) s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
