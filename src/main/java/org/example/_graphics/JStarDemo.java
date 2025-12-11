package org.example._graphics;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class JStarDemo extends JFrame {
    public JStarDemo() {
        setTitle("JStar Demo");
        setSize(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JStar star = new JStar();
        add(star);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JStarDemo frame = new JStarDemo();
            frame.setVisible(true);
        });
    }
}
