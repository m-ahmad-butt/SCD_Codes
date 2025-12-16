package org.example.sockets.numGuessingGame;

import java.io.*;
import java.net.*;

public class server {
    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(9000);
            System.out.println("Waiting for players...");
            
            Socket s1 = server.accept();
            System.out.println("Player 1 Connected");
            Socket s2 = server.accept();
            System.out.println("Player 2 Connected");

            // Streams
            BufferedReader in1 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
            PrintWriter out1 = new PrintWriter(s1.getOutputStream(), true);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
            PrintWriter out2 = new PrintWriter(s2.getOutputStream(), true);

            // Setup: Get targets
            out1.println("Enter the number for P2 to guess: ");
            out2.println("Enter the number for P1 to guess: ");

            String strT1 = in1.readLine();
            String strT2 = in2.readLine();
            
            if (strT1 == null || strT2 == null) {
                System.out.println("A player disconnected during setup.");
                return;
            }

            int t1 = Integer.parseInt(strT1.trim()); // P1's target (for P2 to guess)
            int t2 = Integer.parseInt(strT2.trim()); // P2's target (for P1 to guess)

            boolean isGameOver = false;
            String winner = "";

            while (!isGameOver) {
                out1.println("Enter your guess: ");
                out2.println("Enter your guess: ");

                String guess1Str = in1.readLine();
                String guess2Str = in2.readLine();

                if (guess1Str == null || guess2Str == null) break;

                int g1 = Integer.parseInt(guess1Str.trim());
                int g2 = Integer.parseInt(guess2Str.trim());

                boolean p1Wins = (g1 == t2); // P1 guesses P2's target
                boolean p2Wins = (g2 == t1); // P2 guesses P1's target

                if (p1Wins && p2Wins) {
                    winner = "Draw";
                    isGameOver = true;
                } else if (p1Wins) {
                    winner = "P1";
                    isGameOver = true;
                } else if (p2Wins) {
                    winner = "P2";
                    isGameOver = true;
                }

                String resultMsg = "P1 guessed: " + g1 + ", P2 guessed: " + g2;
                if (isGameOver) {
                    resultMsg += " => Winner: " + winner;
                } else {
                    resultMsg += " => Keep guessing!";
                }

                out1.println(resultMsg);
                out2.println(resultMsg);
            }

            s1.close();
            s2.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
