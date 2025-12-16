package org.example.sockets.numGuessingGame;

import java.io.*;
import java.net.*;

public class server {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(9000);
        System.out.println("Waiting...");
        Socket p1 = server.accept();
        Socket p2 = server.accept();
        new Thread(new Game(p1, p2)).start();
    }
}

class Game implements Runnable {
    Socket s1, s2;
    int target1, target2;
    volatile boolean gameOver = false;

    public Game(Socket s1, Socket s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public void run() {
        try {
            BufferedReader r1 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
            PrintWriter w1 = new PrintWriter(s1.getOutputStream(), true);
            BufferedReader r2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
            PrintWriter w2 = new PrintWriter(s2.getOutputStream(), true);

            w1.println("Enter target for P2 (0-10):");
            target2 = Integer.parseInt(r1.readLine());

            w2.println("Enter target for P1 (0-10):");
            target1 = Integer.parseInt(r2.readLine());

            w1.println("Start guessing!");
            w2.println("Start guessing!");

            new Player(r1, w1, w2, target1, "P1").start();
            new Player(r2, w2, w1, target2, "P2").start();

        } catch (Exception e) {}
    }

    class Player extends Thread {
        BufferedReader in;
        PrintWriter out, otherOut;
        int target;
        String name;

        public Player(BufferedReader in, PrintWriter out, PrintWriter otherOut, int target, String name) {
            this.in = in;
            this.out = out;
            this.otherOut = otherOut;
            this.target = target;
            this.name = name;
        }

        public void run() {
            try {
                while (!gameOver) {
                    String line = in.readLine();
                    if (line == null) break;
                    int guess = Integer.parseInt(line);
                    
                    if (guess == target) {
                        gameOver = true;
                        out.println("You Win!");
                        otherOut.println(name + " Won!");
                        System.exit(0);
                    } else {
                        out.println("Wrong! Try " + (guess < target ? "Higher" : "Lower"));
                    }
                }
            } catch (Exception e) {}
        }
    }
}
