package org.example.sockets.numGuessingGame;

import java.io.*;
import java.net.Socket;

public class client {
    public static void main(String[] args) {
        Socket s = null;
        try {
            s = new Socket("localhost", 9000);
            BufferedReader inServer = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedReader inUser = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);

            String msg;
            while ((msg = inServer.readLine()) != null) {
                System.out.println(msg);

                if (msg.contains("Winner")) {
                    break;
                }

                if (msg.startsWith("Enter")) {
                    String input = inUser.readLine();
                    out.println(input);
                }
            }
        } catch (IOException e) {
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