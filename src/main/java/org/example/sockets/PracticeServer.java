package org.example.sockets;

import java.io.*;
import java.net.*;


public class PracticeServer {
    public static void main(String[] args) {
try{
        ServerSocket srvr = new ServerSocket(9000);
        Socket s = srvr.accept();

        //input
        InputStream in = s.getInputStream();
        BufferedReader i = new BufferedReader(new InputStreamReader(in));

        //output
        OutputStream out = s.getOutputStream();
        PrintWriter p = new PrintWriter(out,true);

        String r;
        while((r=i.readLine())!=null){
            System.out.println("In: " + r);
            p.println("MILL GYA MSGS SERVER KO!");
        }

        s.close();
        srvr.close();
}
catch(IOException e){}
    }
}