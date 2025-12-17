package org.example.mid1Practice;
import javax.swing.*;
import java.awt.*;

//buttons with action listeners
public class midGUI extends JFrame {
    public midGUI() {
        setTitle("FAST Converter");
        setSize(1600,1000);

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