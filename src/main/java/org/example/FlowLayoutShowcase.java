package org.example;

import javax.swing.*;
import java.awt.*;

public class FlowLayoutShowcase {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("FlowLayout Showcase");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 600);
            frame.setLayout(new GridLayout(3, 1, 10, 10)); // 3 sections stacked

            // ===== 1. BASIC FLOWLAYOUT (Default: CENTER) =====
            JPanel basicPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
basicPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

            basicPanel.setBorder(BorderFactory.createTitledBorder("Default (CENTER Alignment)"));
            for (int i = 1; i <= 6; i++) {
                basicPanel.add(new JButton("Btn " + i));
            }

            // ===== 2. ALIGNMENT VARIANTS =====
            JPanel alignmentPanel = new JPanel(new GridLayout(1, 3, 10, 10));

            // LEFT
            JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
            leftPanel.setBorder(BorderFactory.createTitledBorder("LEFT Alignment"));
            for (int i = 1; i <= 5; i++) leftPanel.add(new JButton("L" + i));

            // CENTER
            JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
centerPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

            centerPanel.setBorder(BorderFactory.createTitledBorder("CENTER Alignment"));
            for (int i = 1; i <= 5; i++) centerPanel.add(new JButton("C" + i));

            // RIGHT
            JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
            rightPanel.setBorder(BorderFactory.createTitledBorder("RIGHT Alignment"));
            for (int i = 1; i <= 5; i++) rightPanel.add(new JButton("R" + i));

            alignmentPanel.add(leftPanel);
            alignmentPanel.add(centerPanel);
            alignmentPanel.add(rightPanel);

            // ===== 3. WRAPPING EXAMPLE =====
            JPanel wrapPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
            wrapPanel.setBorder(BorderFactory.createTitledBorder("Wrapping Example (Resize Window!)"));
            for (int i = 1; i <= 20; i++) {
                wrapPanel.add(new JButton("Wrap " + i));
            }

            // ===== ADD ALL TO FRAME =====
            frame.add(basicPanel);
            frame.add(alignmentPanel);
            frame.add(wrapPanel);

            frame.setVisible(true);
        });
    }
}
