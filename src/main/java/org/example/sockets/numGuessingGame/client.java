package org.example.sockets.numGuessingGame;

import java.io.*;
import java.net.Socket;

public class client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 9000);
            System.out.println("Connected to game server!");

            new Thread(new ReadThread(socket)).start();
            new Thread(new WriteThread(socket)).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReadThread implements Runnable {
    private BufferedReader in;

    public ReadThread(Socket socket) {
        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String msg;
        try {
            while ((msg = in.readLine()) != null) {
                System.out.println(msg);
                if (msg.contains("You Win") || msg.contains("You Lose")) {
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            System.out.println("Connection closed.");
            System.exit(0);
        }
    }
}

class WriteThread implements Runnable {
    private PrintWriter out;
    private BufferedReader consoleReader;

    public WriteThread(Socket socket) {
        try {
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.consoleReader = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String msg;
            while ((msg = consoleReader.readLine()) != null) {
                out.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
