package org.example.practice;

import javax.swing.*;
import java.awt.*;

public class SplitLayoutDemo extends JFrame {

    public SplitLayoutDemo() {
        setTitle("Split Layout Example");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Panel → BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        // ---------- TOP PANEL ----------
        JPanel topPanel = new JPanel(new GridLayout(1, 2, 10, 0)); // 1 row, 2 columns
        topPanel.setBackground(Color.LIGHT_GRAY);

        // Left inside top
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(173, 216, 230)); // light blue
        leftPanel.add(new JLabel("Left Side"));
        leftPanel.add(new JButton("Left Button"));

        // Right inside top
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(240, 230, 140)); // khaki
        rightPanel.add(new JLabel("Right Side"));
        rightPanel.add(new JTextField(10));
        rightPanel.add(new JButton("Submit"));

        // Add left and right to top panel
        topPanel.add(leftPanel);
        topPanel.add(rightPanel);

        // ---------- BOTTOM PANEL ----------
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(152, 251, 152)); // pale green
        bottomPanel.add(new JLabel("Bottom Area"));
        bottomPanel.add(new JTextArea(4, 30));

        // Add top and bottom to main panel
        mainPanel.add(topPanel, BorderLayout.CENTER); // top half
        mainPanel.add(bottomPanel, BorderLayout.SOUTH); // bottom section

        // Add main panel to frame
        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SplitLayoutDemo().setVisible(true);
        });
    }
}

