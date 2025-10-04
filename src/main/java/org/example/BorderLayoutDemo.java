package org.example;

import javax.swing.*;
import java.awt.*;

public class BorderLayoutDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("BorderLayout Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            // Create a panel with BorderLayout
            JPanel panel = new JPanel(new BorderLayout(10, 10));
            panel.setBackground(Color.GRAY);

            // NORTH region
            JButton north = new JButton("NORTH");
            panel.add(north, BorderLayout.NORTH);

            // SOUTH region
            JButton south = new JButton("SOUTH");
            panel.add(south, BorderLayout.SOUTH);

            // EAST region
            JButton east = new JButton("EAST");
            panel.add(east, BorderLayout.EAST);

            // WEST region
            JButton west = new JButton("WEST");
            panel.add(west, BorderLayout.WEST);

            // CENTER region
            JButton center = new JButton("CENTER");
            panel.add(center, BorderLayout.CENTER);

            frame.add(panel);
            frame.setVisible(true);

            /////////////CENTER ACTS AS A GLUE
        });
    }
}
