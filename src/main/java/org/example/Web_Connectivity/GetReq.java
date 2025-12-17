package org.example.Web_Connectivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetReq {
  public static void main(String[] args) {
      try{
        URL u = new URL("https://api.adviceslip.com/advice");
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        //default GET
        conn.setDoOutput(false);
        
        try{
            BufferedReader b = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String res="",line;
            while((line=b.readLine())!=null){
                res += line +"\n";
            }

            System.out.println(res);
        }catch(Exception e){}

    }catch(Exception e){}
  }
}
