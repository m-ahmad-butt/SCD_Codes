package org.example;
import javax.swing.*;
import java.awt.*;

public class midGUI extends JFrame {
    public midGUI() {
        setTitle("FAST Converter");
        setSize(1600,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 20));
        UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 20));
        UIManager.put("TextArea.font", new Font("Monospaced", Font.PLAIN, 20));


        JPanel input = new JPanel();
        JPanel output = new JPanel();

        input.add(new JLabel("Enter the temperature"));
        JTextField inputText = new JTextField(15);
        input.add(inputText);
        JButton convertButton = new JButton("Convert");
        input.add(convertButton);
        output.add(new JLabel("Output"));
        JLabel outputText = new JLabel("0.0");
        output.add(outputText);

        convertButton.addActionListener(e -> {
            float InputTemp = Float.parseFloat(inputText.getText());
            outputText.setText(String.format("%.2f", InputTemp));
        });

        setLayout(new BorderLayout());
        add(input, BorderLayout.NORTH);
        add(output, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new midGUI().setVisible(true);
        });
    }
}