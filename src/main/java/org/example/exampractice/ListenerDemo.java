package org.example.exampractice;
import javax.swing.*;
import java.awt.*;

public class ListenerDemo extends JFrame {
    public ListenerDemo(){
        setSize(500,500);
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton b1 = new JButton("b1");
        JButton b2 = new JButton("b2");

        JLabel out = new JLabel("hello");

        p.add(b1);
        p.add(b2);
        p.add(out);

        b1.addActionListener(e->{
                out.setText("b1");
        });

         b2.addActionListener(e->{
                out.setText("b2");
        });
        
        add(p);

        setLocationRelativeTo(null); // Center the frame on screen
    }

      public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ListenerDemo().setVisible(true));
    }
}
