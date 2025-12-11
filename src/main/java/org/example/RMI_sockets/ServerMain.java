package org.example.RMI_sockets;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * RMI Server Main
 * Steps:
 * 1. Create registry on port (1099 default)
 * 2. Create server object
 * 3. Bind object to registry with a name
 */
public class ServerMain {
    public static void main(String[] args) {
        try {
            // 1. Create registry
            LocateRegistry.createRegistry(1099);
            System.out.println("Registry created on port 1099");
            
            // 2. Create server object
            BestServer server = new BestServer();
            
            // 3. Bind to registry
            Naming.rebind("rmi://localhost:1099/MyService", server);
            System.out.println("Server is ready and waiting for clients...");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
