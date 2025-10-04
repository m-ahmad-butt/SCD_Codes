package org.example.table;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// ===== MODEL CLASS =====
class StudentTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Name", "Age", "Grade"};
    private final List<Object[]> data = new ArrayList<>();

    public StudentTableModel() {
        // Some sample data
        data.add(new Object[]{1, "Ahmad", 21, "A"});
        data.add(new Object[]{2, "Ali", 20, "B"});
        data.add(new Object[]{3, "Sara", 22, "A"});
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        // Only Name and Grade are editable
        return col == 1 || col == 3;
    }

    @Override
    public void setValueAt(Object aValue, int row, int col) {
        data.get(row)[col] = aValue;
        fireTableCellUpdated(row, col);
    }

    // Custom helper methods 👇
    public void addRow(Object[] row) {
        data.add(row);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void removeRow(int row) {
        if (row >= 0 && row < data.size()) {
            data.remove(row);
            fireTableRowsDeleted(row, row);
        }
    }

    public Object[] getRow(int row) {
        return data.get(row);
    }
}

// ===== MAIN CLASS =====
public class JTableDemo extends JFrame {
    private JTable table;
    private StudentTableModel model;

    public JTableDemo() {
        setTitle("JTable Example — Common Features");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Model + Table
        model = new StudentTableModel();
        table = new JTable(model);

        // Add common features 👇
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true); // Sorting by column
        table.setRowHeight(25);
        table.setShowGrid(true);
        table.setGridColor(Color.LIGHT_GRAY);

        // Adjust column widths
        TableColumnModel colModel = table.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(50);
        colModel.getColumn(1).setPreferredWidth(120);
        colModel.getColumn(2).setPreferredWidth(60);
        colModel.getColumn(3).setPreferredWidth(60);

        // Add scroll pane
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Control Panel
        JPanel controlPanel = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton removeBtn = new JButton("Remove");
        JButton updateBtn = new JButton("Update");
        JButton showBtn = new JButton("Show Selected");

        controlPanel.add(addBtn);
        controlPanel.add(removeBtn);
        controlPanel.add(updateBtn);
        controlPanel.add(showBtn);

        add(controlPanel, BorderLayout.SOUTH);

        // === Button Actions ===
        addBtn.addActionListener(e -> {
            int nextId = model.getRowCount() + 1;
            model.addRow(new Object[]{nextId, "New Student " + nextId, 18 + nextId, "C"});
        });

        removeBtn.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected != -1) {
                model.removeRow(table.convertRowIndexToModel(selected));
            } else {
                JOptionPane.showMessageDialog(this, "Select a row to remove!");
            }
        });

        updateBtn.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected != -1) {
                int modelRow = table.convertRowIndexToModel(selected);
                model.setValueAt("Updated Name", modelRow, 1);
                model.setValueAt("B+", modelRow, 3);
            } else {
                JOptionPane.showMessageDialog(this, "Select a row to update!");
            }
        });

        showBtn.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected != -1) {
                Object[] row = model.getRow(table.convertRowIndexToModel(selected));
                JOptionPane.showMessageDialog(this,
                        "Selected Student:\nID: " + row[0] + "\nName: " + row[1] + "\nAge: " + row[2] + "\nGrade: " + row[3]);
            } else {
                JOptionPane.showMessageDialog(this, "No row selected!");
            }
        });

        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JTableDemo::new);
    }
}
