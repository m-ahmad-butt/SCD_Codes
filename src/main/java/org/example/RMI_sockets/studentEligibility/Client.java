package org.example.RMI_sockets.studentEligibility;

import javax.lang.model.element.Name;
import java.rmi.Naming;

public class Client {
    public static void main(String[] args) {
        try{
            IStudent student = (IStudent) Naming.lookup("rmi://localhost:1099/student");

            // got the object

            student.addStudent("Ahmed butt",1.9F);
            student.addStudent("Abd Ur Rehman",4F);

            System.out.println("GPA OF AHMED: " + student.getGPA(0));
            System.out.println("GPA OF ABD UR REHMAN: " + student.getGPA(1));

            System.out.println("ELIGIBILITY OF AHMED: " + student.isEligibleForGraduation(0));
            System.out.println("ELIGIBILITY OF ABD UR REHMAN: " + student.isEligibleForGraduation(1));
        }
        catch(Exception e){

        }
    }
}
