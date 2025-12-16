package org.example.sockets.numberGame;

import java.io.*;
import java.net.Socket;

public class GameHandler implements Runnable {

    private Socket p1;
    private Socket p2;

    GameHandler(Socket p1, Socket p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public void run() {
        try {
            BufferedReader in1 = new BufferedReader(
                    new InputStreamReader(p1.getInputStream()));
            PrintWriter out1 = new PrintWriter(p1.getOutputStream(), true);

            BufferedReader in2 = new BufferedReader(
                    new InputStreamReader(p2.getInputStream()));
            PrintWriter out2 = new PrintWriter(p2.getOutputStream(), true);

            out1.println("Enter a number:");
            int secret = Integer.parseInt(in1.readLine());

            out2.println("Guess the number:");
            int guess = Integer.parseInt(in2.readLine());

            if (guess == secret) {
                out2.println("Correct guess!");
            } else {
                out2.println("Wrong! Number was " + secret);
            }

            p1.close();
            p2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
