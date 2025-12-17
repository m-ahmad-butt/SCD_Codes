package org.example.fileHandling;

import java.io.*;

public class FileHandlerExample {

    public static void main(String[] args) {
        try {
            // Reading first file (comma separated)
            BufferedReader br1 = new BufferedReader(new FileReader("src//main//java//org//example//fileHandling//file1.txt"));
            String line1;
            System.out.println("Reading file1.txt (comma separated):");
            while ((line1 = br1.readLine()) != null) {
                String[] parts = line1.split(",");
                System.out.println("Name: " + parts[0] + ", ID: " + parts[1] + ", Score: " + parts[2]);
            }
            br1.close();

            // Reading second file (custom separators | ?)
            BufferedReader br2 = new BufferedReader(new FileReader("src//main//java//org//example//fileHandling//file2.txt"));
            String line2;
            System.out.println("\nReading file2.txt (custom separators | ? .):");
            while ((line2 = br2.readLine()) != null) {
                String[] parts = line2.split("[|?]");
                System.out.println("Name: " + parts[0] + ", ID: " + parts[1] + ", Score: " + parts[2]);
            }
            br2.close();

            // Writing to a new file using BufferedWriter
            BufferedWriter bw = new BufferedWriter(new FileWriter("src//main//java//org//example//fileHandling//output.txt", true));
            bw.write("BufferedWriter example line\n");
            bw.write("Another line\n");
            bw.flush();
            bw.close();

            // Writing to a new file using PrintWriter
            PrintWriter pw = new PrintWriter(new FileWriter("src//main//java//org//example//fileHandling//output2.txt", true));
            pw.println("PrintWriter example line");
            pw.println("Another PrintWriter line");
            pw.flush();
            pw.close();

            // Writing to console using BufferedWriter
            BufferedWriter consoleBw = new BufferedWriter(new OutputStreamWriter(System.out));
            consoleBw.write("\nBufferedWriter writing to console\n");
            consoleBw.flush();

            // Writing to console using PrintWriter
            PrintWriter consolePw = new PrintWriter(System.out, true);
            consolePw.println("PrintWriter writing to console");

            // Reading from console using BufferedReader
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter your name: ");
            String name = consoleReader.readLine();
            System.out.print("Enter your ID: ");
            String id = consoleReader.readLine();
            System.out.println("You entered: Name=" + name + ", ID=" + id);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
