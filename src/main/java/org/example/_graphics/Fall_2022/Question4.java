package org.example._graphics.Fall_2022;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Question4 extends JFrame implements ActionListener {
    
    // Canvas panel to draw on
    private JPanel canvas;
    
    // Coordinates
    private int x = 300;
    private int y = 200;
    
    // History of points to draw lines
    private ArrayList<Point> points;

    public Question4() {
        setTitle("EtchASketch");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize history with starting point
        points = new ArrayList<>();
        points.add(new Point(x, y));

        JPanel buttonPanel = new JPanel();
        JButton btnNorth = new JButton("North");
        JButton btnSouth = new JButton("South");
        JButton btnEast = new JButton("East");
        JButton btnWest = new JButton("West");

        btnNorth.addActionListener(this);
        btnSouth.addActionListener(this);
        btnEast.addActionListener(this);
        btnWest.addActionListener(this);

        buttonPanel.add(btnNorth);
        buttonPanel.add(btnSouth);
        buttonPanel.add(btnEast);
        buttonPanel.add(btnWest);

        add(buttonPanel, BorderLayout.SOUTH);

        canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                // Draw all lines from the history
                g.setColor(Color.RED);
                for (int i = 0; i < points.size() - 1; i++) {
                    Point p1 = points.get(i);
                    Point p2 = points.get(i+1);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                }

                // Draw the cross (X) at current position
                g.setColor(Color.BLACK);
                g.drawString("X", x, y);
            }
        };
        
        add(canvas, BorderLayout.CENTER);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        
        if (cmd.equals("North")) {
            y -= 20;
        } else if (cmd.equals("South")) {
            y += 20;
        } else if (cmd.equals("East")) {
            x += 20;
        } else if (cmd.equals("West")) {
            x -= 20;
        }
        
        // Add new location to history so we can draw the line
        points.add(new Point(x, y));
        
        // Repaint the canvas to show changes
        canvas.repaint();
    }

    public static void main(String[] args) {
        new Question4();
    }
}
