package org.example.RMI_sockets.studentEligibility;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

class Student{
    Student(Integer StudentID, String name, Float GPA){
        this.StudentID = StudentID;
        this.Name = name;
        this.GPA = GPA;
    }
    private Integer StudentID;
    public String Name;
    public Float GPA;
        }

public class Server extends UnicastRemoteObject implements IStudent{
    List<Student> students = new ArrayList<>();
    Integer counter = 0;
    public Server() throws RemoteException {
        super();
    }

    @Override
    public double getGPA(int studentId) throws RemoteException{
        return students.get(studentId).GPA;
    }

    @Override
    public boolean isEligibleForGraduation(int studentId)
            throws RemoteException{
        if (getGPA(studentId) >= 2.0f){
            return true;
        }
        return false;
    }
    @Override
    public void addStudent(String Name, Float GPA){
        students.add(new Student(counter++, Name, GPA));
    }

}
