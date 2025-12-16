package org.example.sockets.numberGame;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5001);

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String cmd = in.readLine();

            if (cmd.equals("CHOOSE")) {
                System.out.print("Choose number: ");
                out.println(console.readLine());
            }

            else if (cmd.equals("GUESS")) {
                System.out.print("Guess number: ");
                out.println(console.readLine());
            }

            else {
                System.out.println(cmd);
            }
        }
    }
}
