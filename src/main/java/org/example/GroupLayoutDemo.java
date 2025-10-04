package org.example;

import javax.swing.*;
import java.awt.*;

public class GroupLayoutDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("GroupLayout Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 250);

            JPanel panel = new JPanel();
            frame.add(panel);

            // Components
            JLabel userLabel = new JLabel("Username:");
            JTextField userField = new JTextField(15);
            JLabel passLabel = new JLabel("Password:");
            JPasswordField passField = new JPasswordField(15);
            JButton loginBtn = new JButton("Login");
            JButton cancelBtn = new JButton("Cancel");

            // Create GroupLayout
            GroupLayout layout = new GroupLayout(panel);
            panel.setLayout(layout);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);

            // HORIZONTAL GROUP
            layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(userLabel)
                            .addComponent(passLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(userField)
                            .addComponent(passField)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loginBtn)
                        .addComponent(cancelBtn))
            );

            // VERTICAL GROUP
            layout.setVerticalGroup(
                layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(userLabel)
                        .addComponent(userField))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(passLabel)
                        .addComponent(passField))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(loginBtn)
                        .addComponent(cancelBtn))
            );

            frame.setVisible(true);
        });
    }
}
