package org.example.sockets.numberGame;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(5001);

        Socket p1 = server.accept();
        Socket p2 = server.accept();

        BufferedReader in1 = new BufferedReader(new InputStreamReader(p1.getInputStream()));
        PrintWriter out1 = new PrintWriter(p1.getOutputStream(), true);

        BufferedReader in2 = new BufferedReader(new InputStreamReader(p2.getInputStream()));
        PrintWriter out2 = new PrintWriter(p2.getOutputStream(), true);

        while (true) {
            // P1 chooses
            out1.println("CHOOSE");
            int secret = Integer.parseInt(in1.readLine());

            // P2 guesses
            out2.println("GUESS");
            int guess = Integer.parseInt(in2.readLine());

            if (guess == secret) {
                out2.println("CORRECT");
                out1.println("OPPONENT WON");
            } else {
                out2.println("WRONG");
                out1.println("OPPONENT LOST");
            }

            // we swap the actual connections insted of using a flag to track roles
            // code become less verbose because we can assume that p1 will always choose and p2 will guess
            Socket tmp = p1; p1 = p2; p2 = tmp;
            BufferedReader tIn = in1; in1 = in2; in2 = tIn;
            PrintWriter tOut = out1; out1 = out2; out2 = tOut;
        }
    }
}
