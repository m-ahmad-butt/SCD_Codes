package org.example;

import javax.swing.*;
import java.awt.*;

public class LayoutComparisonDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("FlowLayout vs BoxLayout Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLayout(new GridLayout(1, 2)); // Two panels side-by-side

            // -------- Left Panel (FlowLayout) --------
            JPanel flowPanel = new JPanel();
            flowPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            flowPanel.setBorder(BorderFactory.createTitledBorder("FlowLayout"));
            for (int i = 1; i <= 6; i++) {
                flowPanel.add(new JButton("Btn " + i));
            }

            // -------- Right Panel (BoxLayout) --------
            JPanel boxPanel = new JPanel();
            boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
            boxPanel.setBorder(BorderFactory.createTitledBorder("BoxLayout"));
            for (int i = 1; i <= 6; i++) {
                JButton btn = new JButton("Btn " + i);
                boxPanel.add(btn);
                // btn.setAlignmentX(Component.CENTER_ALIGNMENT);
                btn.setAlignmentY(Component.CENTER_ALIGNMENT);

                boxPanel.add(Box.createVerticalStrut(1)); // adds small spacing
            }

            // Add both panels to frame
            frame.add(flowPanel);
            frame.add(boxPanel);

            frame.setVisible(true);
        });
    }
}
