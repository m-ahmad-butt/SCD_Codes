package org.example.table;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This implementation demonstrates the tabular representation of data
 * using JTable with TableModel interface and AbstractTableModel
 * as shown in the class diagram
 */

// Custom TableModel extending AbstractTableModel
class CustomTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Name", "Age", "Department", "Salary"};
    private final List<Object[]> data;
    
    public CustomTableModel() {
        data = new ArrayList<>();
        // Sample data
        data.add(new Object[]{1, "Ahmad Ali", 25, "IT", 50000});
        data.add(new Object[]{2, "Sara Khan", 28, "HR", 45000});
        data.add(new Object[]{3, "Ali Ahmed", 30, "Finance", 55000});
        data.add(new Object[]{4, "Fatima Sheikh", 26, "IT", 52000});
    }
    
    // Implementation of abstract methods from AbstractTableModel
    @Override
    public int getRowCount() {
        return data.size();
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return Integer.class;  // ID
            case 1: return String.class;   // Name
            case 2: return Integer.class;  // Age
            case 3: return String.class;   // Department
            case 4: return Integer.class;  // Salary
            default: return Object.class;
        }
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        // ID is not editable, others are
        return col != 0;
    }
    
    @Override
    public void setValueAt(Object aValue, int row, int col) {
        data.get(row)[col] = aValue;
        fireTableCellUpdated(row, col);
    }
    
    // Custom methods for data manipulation
    public void addRow(Object[] rowData) {
        data.add(rowData);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
    
    public void removeRow(int row) {
        if (row >= 0 && row < data.size()) {
            data.remove(row);
            fireTableRowsDeleted(row, row);
        }
    }
    
    public Object[] getRowData(int row) {
        if (row >= 0 && row < data.size()) {
            return data.get(row);
        }
        return null;
    }
}

// Main class demonstrating the implementation
public class TableModelImplementation extends JFrame implements ActionListener {
    private JTable table;
    private CustomTableModel tableModel;
    private JTextField nameField, ageField, deptField, salaryField;
    private JButton addButton, deleteButton, updateButton, showButton;
    
    public TableModelImplementation() {
        setTitle("TableModel Implementation - Following Interface Design");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Initialize the custom table model
        tableModel = new CustomTableModel();
        
        // Create JTable with the custom model
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(25);
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        
        // Set column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        table.getColumnModel().getColumn(1).setPreferredWidth(120); // Name
        table.getColumnModel().getColumn(2).setPreferredWidth(60);  // Age
        table.getColumnModel().getColumn(3).setPreferredWidth(100); // Department
        table.getColumnModel().getColumn(4).setPreferredWidth(80);  // Salary
        
        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        // Create input panel
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);
        
        // Create control panel
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Employee Data Entry"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Name
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(15);
        panel.add(nameField, gbc);
        
        // Age
        gbc.gridx = 2; gbc.gridy = 0;
        panel.add(new JLabel("Age:"), gbc);
        gbc.gridx = 3;
        ageField = new JTextField(5);
        panel.add(ageField, gbc);
        
        // Department
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Department:"), gbc);
        gbc.gridx = 1;
        deptField = new JTextField(15);
        panel.add(deptField, gbc);
        
        // Salary
        gbc.gridx = 2; gbc.gridy = 1;
        panel.add(new JLabel("Salary:"), gbc);
        gbc.gridx = 3;
        salaryField = new JTextField(10);
        panel.add(salaryField, gbc);
        
        return panel;
    }
    
    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        
        addButton = new JButton("Add Employee");
        deleteButton = new JButton("Delete Selected");
        updateButton = new JButton("Update Selected");
        showButton = new JButton("Show Details");
        
        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        updateButton.addActionListener(this);
        showButton.addActionListener(this);
        
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(updateButton);
        panel.add(showButton);
        
        return panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addEmployee();
        } else if (e.getSource() == deleteButton) {
            deleteEmployee();
        } else if (e.getSource() == updateButton) {
            updateEmployee();
        } else if (e.getSource() == showButton) {
            showEmployeeDetails();
        }
    }
    
    private void addEmployee() {
        try {
            String name = nameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String dept = deptField.getText().trim();
            int salary = Integer.parseInt(salaryField.getText().trim());
            
            if (name.isEmpty() || dept.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and Department cannot be empty!");
                return;
            }
            
            int newId = tableModel.getRowCount() + 1;
            Object[] newRow = {newId, name, age, dept, salary};
            
            tableModel.addRow(newRow);
            clearFields();
            
            JOptionPane.showMessageDialog(this, "Employee added successfully!");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for Age and Salary!");
        }
    }
    
    private void deleteEmployee() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete!");
            return;
        }
        
        int modelRow = table.convertRowIndexToModel(selectedRow);
        tableModel.removeRow(modelRow);
        
        JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
    }
    
    private void updateEmployee() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to update!");
            return;
        }
        
        try {
            String name = nameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String dept = deptField.getText().trim();
            int salary = Integer.parseInt(salaryField.getText().trim());
            
            if (name.isEmpty() || dept.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and Department cannot be empty!");
                return;
            }
            
            int modelRow = table.convertRowIndexToModel(selectedRow);
            
            tableModel.setValueAt(name, modelRow, 1);
            tableModel.setValueAt(age, modelRow, 2);
            tableModel.setValueAt(dept, modelRow, 3);
            tableModel.setValueAt(salary, modelRow, 4);
            
            clearFields();
            JOptionPane.showMessageDialog(this, "Employee updated successfully!");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for Age and Salary!");
        }
    }
    
    private void showEmployeeDetails() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to view details!");
            return;
        }
        
        int modelRow = table.convertRowIndexToModel(selectedRow);
        Object[] rowData = tableModel.getRowData(modelRow);
        
        if (rowData != null) {
            String details = String.format(
                "Employee Details:\n\n" +
                "ID: %s\n" +
                "Name: %s\n" +
                "Age: %s\n" +
                "Department: %s\n" +
                "Salary: $%s",
                rowData[0], rowData[1], rowData[2], rowData[3], rowData[4]
            );
            
            JOptionPane.showMessageDialog(this, details, "Employee Details", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        deptField.setText("");
        salaryField.setText("");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TableModelImplementation().setVisible(true);
        });
    }
}