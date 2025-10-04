package org.example.mvcExample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// ========== MODEL ==========
class TemperatureModel {
    // Logic only — no UI here
    public double fahrenheitToCelsius(double f) {
        return (f - 32) * 5 / 9;
    }
}

// ========== VIEW ==========
class TemperatureView extends JFrame {
    JTextField fahrenheitField = new JTextField(10);
    JButton convertButton = new JButton("Convert");
    JLabel resultLabel = new JLabel("Celsius: ");
    
    public TemperatureView() {
        super("Fahrenheit to Celsius Converter (MVC)");
        
        // Layout Setup
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        
        add(new JLabel("Fahrenheit:"));
        add(fahrenheitField);
        add(convertButton);
        add(resultLabel);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Update Celsius value
    public void setCelsiusValue(String value) {
        resultLabel.setText("Celsius: " + value);
    }

    // If input invalid, show message
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Attach Listener
    public void addConvertListener(ActionListener listener) {
        convertButton.addActionListener(listener);
    }

    // Get input
    public String getFahrenheitValue() {
        return fahrenheitField.getText();
    }
}

// ========== CONTROLLER ==========
class TemperatureController {
    private TemperatureModel model;
    private TemperatureView view;

    public TemperatureController(TemperatureModel model, TemperatureView view) {
        this.model = model;
        this.view = view;

        // Attach event
        view.addConvertListener(e -> convertTemperature());
    }

    private void convertTemperature() {
        try {
            double f = Double.parseDouble(view.getFahrenheitValue());
            double c = model.fahrenheitToCelsius(f);
            view.setCelsiusValue(String.format("%.2f", c));
        } catch (NumberFormatException ex) {
            view.showError("Please enter a valid number!");
        }
    }
}

// ========== MAIN APP ==========
public class MVCTemperatureConverter {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TemperatureModel model = new TemperatureModel();
            TemperatureView view = new TemperatureView();
            new TemperatureController(model, view);
        });
    }
}

