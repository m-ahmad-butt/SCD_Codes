package org.example.serielization;

import java.io.*;
import java.util.Date;

public class Student implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int rollNo;
    private String name;
    private double gpa;
    private Date birthDate;
    
    private transient String password;
    
    private static int totalStudents = 0;
    
    // Constructor
    public Student(int rollNo, String name, double gpa, Date birthDate, String password) {
        this.rollNo = rollNo;
        this.name = name;
        this.gpa = gpa;
        this.birthDate = birthDate;
        this.password = password;
        totalStudents++;
    }
    
    // Getters and Setters
    public int getRollNo() {
        return rollNo;
    }
    
    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getGpa() {
        return gpa;
    }
    
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    
    public Date getBirthDate() {
        return birthDate;
    }
    
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public static int getTotalStudents() {
        return totalStudents;
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "rollNo=" + rollNo +
                ", name='" + name + '\'' +
                ", gpa=" + gpa +
                ", birthDate=" + birthDate +
                ", password='" + password + '\'' +
                ", totalStudents=" + totalStudents +
                '}';
    }
    

    public static void main(String[] args) {
        System.out.println("-- Creating and Saving Student to Disk --\n");
        
        Student student = new Student(1140, "Ahmad Butt", 3.5, new Date(), "myPassword123");
        System.out.println("Student created in memory:");
        System.out.println(student);
        System.out.println("\nPassword field: " + student.getPassword() + " (will NOT be saved - it's transient)");
        
        // Step 2: Save the student to hard disk as a .ser file
        String filename = "student_saved.ser";
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            
            out.writeObject(student);
            
            out.close();
            fileOut.close();
            
            System.out.println("\n SUCCESS!");
            System.out.println("Student saved to hard disk at: " + new File(filename).getAbsolutePath());
            System.out.println("Check your project folder - you'll see the file: " + filename);
            
        } catch (IOException e) {
            System.err.println("Error saving student: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("\n-- Loading Student from Disk --\n");
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            
            Student loadedStudent = (Student) in.readObject();
            
            in.close();
            fileIn.close();
            
            System.out.println("Student loaded from disk:");
            System.out.println(loadedStudent);
            System.out.println("\nNotice: Password is now null (because it was marked as transient!)");
            
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading student: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
