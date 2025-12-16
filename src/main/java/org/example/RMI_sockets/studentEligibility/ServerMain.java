package org.example.RMI_sockets.studentEligibility;

import javax.naming.Name;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {
    public static void main(String[] args) {
        try{
            LocateRegistry.createRegistry(1099);
            Server server = new Server();
            Naming.rebind("rmi://localhost:1099/student", server);
            Thread.sleep(Long.MAX_VALUE);

        }
        catch (Exception e){

        }
        }
}
