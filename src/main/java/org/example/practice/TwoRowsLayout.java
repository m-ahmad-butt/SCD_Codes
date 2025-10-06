package org.example.practice;

import javax.swing.*;
import java.awt.*;

public class TwoRowsLayout extends JFrame {

    public TwoRowsLayout() {
        setTitle("Two Rows Layout Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // Main panel - horizontal arrangement (2 panels side by side)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // ----- First Section (Top Row) -----
        JPanel topRow = new JPanel(new GridLayout(1, 2, 10, 10)); // 2x2 grid with gaps
        topRow.setBackground(Color.LIGHT_GRAY);
        topRow.setBorder(BorderFactory.createTitledBorder("Top Row - Grid Layout"));

        // Add some components to grid
        topRow.add(new JButton("Button 1"));
        topRow.add(new JButton("Button 2"));


        // ----- Second Section (Bottom Row) -----
        JPanel bottomRow = new JPanel();
        bottomRow.setBackground(Color.CYAN);
        bottomRow.setBorder(BorderFactory.createTitledBorder("Bottom Row"));
        bottomRow.add(new JLabel("This is Row 2"));

        // Add both panels to main panel
        mainPanel.add(topRow);
        mainPanel.add(bottomRow);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TwoRowsLayout::new);
    }
}
