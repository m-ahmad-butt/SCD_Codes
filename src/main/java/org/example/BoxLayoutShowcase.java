package org.example;

import javax.swing.*;
import java.awt.*;

public class BoxLayoutShowcase {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("BoxLayout Showcase");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 500);
            frame.setLayout(new GridLayout(1, 2, 10, 0)); // left & right panels

     
//y
            JPanel panel = new JPanel();
            panel.setBackground(Color.GRAY);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            // Create the button
            JButton centerBtn = new JButton("Perfect Center");
            centerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Add vertical glue before and after to center it vertically
            panel.add(Box.createVerticalGlue());
            panel.add(centerBtn);
            // panel.add(Box.createVerticalGlue());

            frame.add(panel);
            frame.setVisible(true);

            // ---------- Right Panel: X_AXIS (Horizontal) ----------
            JPanel horizontalPanel = new JPanel();
            horizontalPanel.setLayout(new BoxLayout(horizontalPanel, BoxLayout.X_AXIS));
            horizontalPanel.setBorder(BorderFactory.createTitledBorder("X_AXIS (Horizontal Row)"));

            JButton leftBtn = new JButton("Left");
            JButton midBtn = new JButton("Middle");
            JButton rightBtn = new JButton("Right");

            // Add fixed spacing
            horizontalPanel.add(Box.createHorizontalStrut(10));
            horizontalPanel.add(leftBtn);
            horizontalPanel.add(Box.createHorizontalGlue()); // pushes mid to center
            horizontalPanel.add(midBtn);
            horizontalPanel.add(Box.createHorizontalGlue()); // pushes right to edge
            horizontalPanel.add(rightBtn);
            horizontalPanel.add(Box.createHorizontalStrut(10));

            // Add both panels to frame
            frame.add(panel);
            frame.add(horizontalPanel);

            frame.setVisible(true);
        });
    }
}
