package org.example.RMI_sockets.studentEligibility;

import java.rmi.RemoteException;
import java.rmi.Remote;
public interface IStudent extends Remote {
    double getGPA(int studentId) throws RemoteException;
    boolean isEligibleForGraduation(int studentId)
            throws RemoteException;
    void addStudent(String Name, Float GPA);
}
