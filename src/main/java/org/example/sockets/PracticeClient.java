package org.example.sockets;

import java.io.*;
import java.net.*;


public class PracticeClient {
    public static void main(String[] args) {
        try{
        Socket s = new Socket("localhost",9000);

        //in
        InputStream in = s.getInputStream();
        BufferedReader i = new BufferedReader(new InputStreamReader(in));
        BufferedReader cum = new BufferedReader(new InputStreamReader(System.in));

        //out
        OutputStream out = s.getOutputStream();
        PrintWriter p = new PrintWriter(out,true);

        String r;
        while((r=cum.readLine())!=null){
            p.println(r);

            String rum = i.readLine();
            System.out.println("In: " + rum);
        }

        s.close();
        }
        catch(IOException e){}
    }
}