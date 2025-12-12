package org.example.table.todoList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//model class
class Todo {
    private String name;
    private String status;

    public Todo(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
}

//custom model table
class TodoModel extends AbstractTableModel {
    private final String[] columns = {"Name", "Status"};
    private List<Todo> todos = new ArrayList<>();

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public int getRowCount() {
        return todos.size();
    }

    @Override
    public Object getValueAt(int r, int c) {
        Todo l = todos.get(r);

        switch (c) {
            case 0:
                return l.getName();
            case 1:
                return l.getStatus();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int c) {
        return columns[c];
    }

    public void addTodo(Todo t) {
        todos.add(t);
        fireTableDataChanged();
    }

    public void remove(int i) {
        todos.remove(i);
    }

    public void set(String a, String b, int i) {
        todos.set(i, new Todo(a, b));
    }
}

public class todosUI extends JFrame {
    private JTable todoTb;
    private TodoModel todoMd;

    //ui
    public todosUI() {
        JLabel lblName = new JLabel("Todo items: ");
        JTextField fieldName = new JTextField();
        JButton btnAdd = new JButton("Add");
        JButton btnEdit = new JButton("Edit");
        JButton btnDel = new JButton("Delete");

        todoMd = new TodoModel();
        todoTb = new JTable(todoMd);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        //panels
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel upperPanel = new JPanel(new FlowLayout());
        upperPanel.add(lblName);
        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.add(fieldName, BorderLayout.CENTER);
        JPanel btns = new JPanel(new FlowLayout());
        btns.add(btnAdd);
        btns.add(btnEdit);
        btns.add(btnDel);
        middlePanel.add(btns, BorderLayout.EAST);

        JPanel lowerPanel = new JPanel(new BorderLayout());
        lowerPanel.add(new JScrollPane(todoTb), BorderLayout.CENTER);

        mainPanel.add(upperPanel);
        mainPanel.add(middlePanel);
        mainPanel.add(lowerPanel);
        add(mainPanel);

        btnAdd.addActionListener(e -> {
            String name = fieldName.getText();
            Todo t = new Todo(name, "X");
            todoMd.addTodo(t);
            fieldName.setText("");
        });

        btnEdit.addActionListener(e -> {
            int r = todoTb.getSelectedRow();
            if (r >= 0) {
                String currVal1 = (String) todoTb.getValueAt(r, 0);
                String currVal2 = (String) todoTb.getValueAt(r, 1);
                String newName1 = JOptionPane.showInputDialog(this, "Edit Name:", currVal1);
                String newName2 = JOptionPane.showInputDialog(this, "Edit Status:", currVal2);
                todoMd.set(newName1, newName2, r);
                todoMd.fireTableRowsUpdated(r, r);
            }
        });

        btnDel.addActionListener(e -> {
            int r = todoTb.getSelectedRow();
            if (r >= 0) {
                todoMd.remove(r);
                todoMd.fireTableRowsDeleted(r, r);
            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new todosUI().setVisible(true));
    }

}
