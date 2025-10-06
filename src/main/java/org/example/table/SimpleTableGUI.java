package org.example.table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SimpleTableGUI extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField nameField, ageField, gradeField;

    public SimpleTableGUI() {
        setTitle("Simple Table GUI - Lite Version");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ====== TABLE SETUP ======
        String[] columnNames = {"ID", "Name", "Age", "Grade"};
        model = new DefaultTableModel(columnNames, 0); // 0 = start empty

        table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setRowHeight(25);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ====== INPUT PANEL ======
        JPanel inputPanel = new JPanel(new FlowLayout());
        nameField = new JTextField(10);
        ageField = new JTextField(5);
        gradeField = new JTextField(5);

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Age:"));
        inputPanel.add(ageField);
        inputPanel.add(new JLabel("Grade:"));
        inputPanel.add(gradeField);

        add(inputPanel, BorderLayout.NORTH);

        // ====== BUTTON PANEL ======
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Add");
        JButton deleteBtn = new JButton("Delete Selected");
        JButton clearBtn = new JButton("Clear Fields");

        buttonPanel.add(addBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(clearBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // ====== ACTIONS ======
        addBtn.addActionListener(e -> addStudent());
        deleteBtn.addActionListener(e -> deleteSelected());
        clearBtn.addActionListener(e -> clearFields());
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        String age = ageField.getText().trim();
        String grade = gradeField.getText().trim();

        if (name.isEmpty() || age.isEmpty() || grade.isEmpty()) {
            showError("Please fill all fields!");
            return;
        }

        try {
            Integer.parseInt(age); // validate numeric age
        } catch (NumberFormatException e) {
            showError("Age must be a number!");
            return;
        }

        int nextId = model.getRowCount() + 1;
        model.addRow(new Object[]{nextId, name, age, grade});
        clearFields();
    }

    private void deleteSelected() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            showError("Please select a row to delete!");
            return;
        }

        model.removeRow(selectedRow);
        // Update IDs after delete
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(i + 1, i, 0);
        }
    }

    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        gradeField.setText("");
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleTableGUI().setVisible(true));
    }
}
