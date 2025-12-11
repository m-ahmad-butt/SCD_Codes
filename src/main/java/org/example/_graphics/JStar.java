package org.example._graphics;

import javax.swing.JComponent;
import java.awt.Graphics;

public class JStar extends JComponent {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Example: Draw a simple star shape
        int[] xPoints = {50, 60, 80, 65, 70, 50, 30, 35, 20, 40};
        int[] yPoints = {20, 40, 40, 55, 75, 65, 75, 55, 40, 40};
        g.drawPolygon(xPoints, yPoints, xPoints.length);
    }
}
