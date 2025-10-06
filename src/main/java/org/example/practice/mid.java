package org.example.practice;

import java.awt.*;
import javax.swing.*;

public class mid extends JFrame {
    private JTextField name;
    private JLabel output;
    private JTextArea output2;

    public mid() {
        setTitle("Mid Layout Example");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // --- Create Panels ---
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10)); 
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10)); // For text + button inline
        JPanel outputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // For displaying result

        // --- Input Section ---
        JLabel text = new JLabel("Enter your name:");
        name = new JTextField(10);
        JButton enterBtn = new JButton("Enter");

        inputPanel.add(text);
        inputPanel.add(name);
        inputPanel.add(enterBtn);

        // --- Output Section ---
        output = new JLabel("Your name will appear here");
        outputPanel.add(output);

        // --- Add panels to main layout ---
        mainPanel.add(inputPanel, BorderLayout.WEST);
        mainPanel.add(outputPanel, BorderLayout.SOUTH);

        // --- Add main panel to frame ---
        add(mainPanel);

        // --- Action Listener for button ---
       enterBtn.addActionListener(e->{
        int n = Integer.parseInt(name.getText());
        output.setText(String.valueOf(n));
       }
       );

        setLocationRelativeTo(null); // Center the frame on screen
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new mid().setVisible(true);
        });
    }
}
