package org.example.mid1Practice;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//buttons with action performed
public class midGUI2 extends JFrame implements ActionListener {
    private JTextField inputText;
    private JLabel outputText;
    private JButton convertButton;
    
    public midGUI2() {
        setTitle("FAST Converter");
        setSize(1600,1000);

        JPanel input = new JPanel();
        JPanel output = new JPanel();

        input.add(new JLabel("Enter the temperature"));
        inputText = new JTextField(15);
        input.add(inputText);
        convertButton = new JButton("Convert");
        input.add(convertButton);

        output.add(new JLabel("Output"));
        outputText = new JLabel("0.0");
        output.add(outputText);

        // Instead of addActionListener, we set this class as the listener
        convertButton.addActionListener(this);

        setLayout(new BorderLayout());
        add(input, BorderLayout.NORTH);
        add(output, BorderLayout.SOUTH);
    }

    // Override the actionPerformed method
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            float InputTemp = Float.parseFloat(inputText.getText());
            outputText.setText(String.format("%.2f", InputTemp));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new midGUI2().setVisible(true);
        });
    }
}