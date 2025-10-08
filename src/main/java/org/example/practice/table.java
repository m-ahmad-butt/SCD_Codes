package org.example.practice;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import org.example.table.TableHierarchyDemo;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

class Attendance {
    boolean attend;
    String name;
    String rollNum;

    public Attendance(String r, String n, boolean a) {
        attend = a;
        name = n;
        rollNum = r;
    }

    public void toggleAtt() {
        attend = !attend;
    }
}

class AttendanceModel extends AbstractTableModel {
    private final String[] col = { "Roll No. ,", "Name", "Attendance" };
    private final ArrayList<Attendance> data = new ArrayList<>();

    public void addData(Attendance a) {
        data.add(a);
    }

    public Attendance getAttendance(int index) {
        return data.get(index);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return col.length;
    }

    @Override
    public String getColumnName(int index) {
        return col[index];
    }

    @Override
    public Object getValueAt(int r, int c) {
        Attendance a = data.get(r);

        switch (c) {
            case 0:
                return a.rollNum;
            case 1:
                return a.name;
            case 2:
                return a.attend;
            default:
                return null;
        }
    }
}

public class table extends JFrame {
    private JTable tab;
    private AttendanceModel att;

    public table() {
        setTitle("JTable Model Hierarchy Demo");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p = new JPanel(new GridLayout(1, 1));
        att = new AttendanceModel();
        att.addData(new Attendance("23L-3059", "Muhammad Ahmad Butt", false)); // absent -> 0
        att.addData(new Attendance("23L-3105", "Abd Ur Rehman", false)); // absent -> 0
        att.addData(new Attendance("23L-3071", "Saim Arif", false)); // absent -> 0

        tab = new JTable(att);

        tab.add(new JButton("Mark"));
            tab.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tab.getSelectedRow();
                    if (selectedRow != -1) {
                        Attendance at = att.getAttendance(selectedRow);
                        //logic
at.toggleAtt();
att.fireTableDataChanged();
                    }
                }
            }
        });

// tab.addMouseListener(new MouseAdapter() {
//     @Override
//     public void mouseClicked(MouseEvent e){
//         int r = tab.rowAtPoint(e.getPoint());
//         int c = tab.columnAtPoint(e.getPoint());

//         if(r!=-1 && c==2){
//             Attendance a = att.getAttendance(r);
//             a.toggleAtt();
//         att.fireTableDataChanged();
//         }
//     }
// });

                
        add(new JScrollPane(tab));

    }

     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new table().setVisible(true));
    }
}
