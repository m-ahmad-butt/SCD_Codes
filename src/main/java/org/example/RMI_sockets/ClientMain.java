package org.example.RMI_sockets;

import java.rmi.Naming;

/**
 * RMI Client
 * Steps:
 * 1. Lookup remote object from registry
 * 2. Call methods on remote object
 */
public class ClientMain {
    public static void main(String[] args) {
        try {
            // 1. Lookup remote object
            I_Srvc service = (I_Srvc) Naming.lookup("rmi://localhost:1099/MyService");
            System.out.println("Connected to server");
            
            // 2. Call remote methods
            String result = service.sort("hello");
            System.out.println("Sorted string: " + result);
            
            int sum = service.add(5, 10);
            System.out.println("Addition result: " + sum);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
