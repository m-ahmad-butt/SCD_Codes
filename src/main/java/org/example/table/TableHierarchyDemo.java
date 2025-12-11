package org.example.table;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// 1- default table
// 2- custom table

// ======== CONTACT CLASS (simple POJO) ==========
class Contact {
    String name;
    String phone;
    String email;

    Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}

// ======== CONTACTSMODEL (Custom Model) ==========
class ContactsModel extends AbstractTableModel {
    private final String[] columns = {"Name", "Phone", "Email"};
    private final List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact c) {
        contacts.add(c);
        fireTableDataChanged(); // notify JTable
    }

    public Contact getContact(int index) {
        return contacts.get(index);
    }

    @Override
    public int getRowCount() {
        return contacts.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Contact c = contacts.get(row);
        return switch (col) {
            case 0 -> c.name;
            case 1 -> c.phone;
            case 2 -> c.email;
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}

// ======== MAIN GUI (JTable + Listeners + Both Models) ==========
public class TableHierarchyDemo extends JFrame {
    private JTable customTable;
    private JTable defaultTable;
    private ContactsModel contactsModel;

    public TableHierarchyDemo() {
        setTitle("JTable Model Hierarchy Demo");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        // ---------- 1. USING DEFAULTTABLEMODEL (built-in) ----------
        String[] cols = {"ID", "Name"};
        Object[][] data = {
                {"1", "Ahmad"},
                {"2", "Ali"},
                {"3", "Sara"}
        };
        DefaultTableModel defaultModel = new DefaultTableModel(data, cols);
        defaultTable = new JTable(defaultModel);
        add(new JScrollPane(defaultTable));

        // ---------- 2. USING CUSTOM CONTACTSMODEL (our specific model) ----------
        contactsModel = new ContactsModel();
        customTable = new JTable(contactsModel);

        // Add some contacts manually
        contactsModel.addContact(new Contact("Usman", "0300-1234567", "usman@gmail.com"));
        contactsModel.addContact(new Contact("Ayesha", "0333-9998888", "ayesha@yahoo.com"));
        contactsModel.addContact(new Contact("Hamza", "0321-4567890", "hamza@outlook.com"));

        add(new JScrollPane(customTable));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TableHierarchyDemo().setVisible(true));
    }
}
