package org.example.mvcExample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// ===== MODEL =====
class CalculatorModel {
    public double add(double a, double b) { return a + b; }
    public double subtract(double a, double b) { return a - b; }
    public double multiply(double a, double b) { return a * b; }
    public double divide(double a, double b) { 
        if (b == 0) throw new ArithmeticException("Cannot divide by zero");
        return a / b; 
    }
}

// ===== VIEW =====
class CalculatorView extends JFrame {
    JTextField num1Field = new JTextField(10);
    JTextField num2Field = new JTextField(10);
    JLabel resultLabel = new JLabel("Result: ");
    
    JButton addBtn = new JButton("+");
    JButton subBtn = new JButton("-");
    JButton mulBtn = new JButton("*");
    JButton divBtn = new JButton("/");

    public CalculatorView() {
        super("MVC Calculator");

        setLayout(new GridLayout(4, 2, 10, 10));

        // Row 1
        add(new JLabel("Number 1:"));
        add(num1Field);

        // Row 2
        add(new JLabel("Number 2:"));
        add(num2Field);

        // Row 3 (Buttons)
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(addBtn);
        btnPanel.add(subBtn);
        btnPanel.add(mulBtn);
        btnPanel.add(divBtn);
        add(new JLabel("Operations:"));
        add(btnPanel);

        // Row 4 (Result)
        add(resultLabel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public String getNum1() { return num1Field.getText(); }
    public String getNum2() { return num2Field.getText(); }

    public void setResult(String text) { resultLabel.setText("Result: " + text); }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void addListeners(ActionListener listener) {
        addBtn.addActionListener(listener);
        subBtn.addActionListener(listener);
        mulBtn.addActionListener(listener);
        divBtn.addActionListener(listener);
    }
}

// ===== CONTROLLER =====
class CalculatorController implements ActionListener {
    private CalculatorModel model;
    private CalculatorView view;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
        this.view.addListeners(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double num1 = Double.parseDouble(view.getNum1());
            double num2 = Double.parseDouble(view.getNum2());
            double result = 0;

            Object source = e.getSource();

            if (source == view.addBtn) result = model.add(num1, num2);
            else if (source == view.subBtn) result = model.subtract(num1, num2);
            else if (source == view.mulBtn) result = model.multiply(num1, num2);
            else if (source == view.divBtn) result = model.divide(num1, num2);

            view.setResult(String.format("%.2f", result));

        } catch (NumberFormatException ex) {
            view.showError("Please enter valid numbers!");
        } catch (ArithmeticException ex) {
            view.showError(ex.getMessage());
        }
    }
}

// ===== MAIN APP =====
public class MVCCalculator {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorModel model = new CalculatorModel();
            CalculatorView view = new CalculatorView();
            new CalculatorController(model, view);
        });
    }
}
