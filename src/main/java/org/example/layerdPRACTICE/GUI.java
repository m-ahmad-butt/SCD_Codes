package org.example.layerdPRACTICE;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.example.layerdPRACTICE.model.Item;

public class GUI extends JFrame {
    
    public class ItemModel extends AbstractTableModel{
        String[] cols = {"Code","Quantity","Price"};
        ArrayList<Item> it = new ArrayList<>();

void addItem(Item i){
    it.add(i);
    fireTableDataChanged();
}
        @Override
        public int getRowCount(){
            return it.size();
        }
        @Override
        public int getColumnCount(){
            return cols.length;
        }
        @Override
        public Object getValueAt(int r,int c){
            Item itm = it.get(r);
            switch (c) {
                case 0:
                    return itm.getCode();
                case 1:
                    return itm.getQuantity();
                case 2:
                    return itm.getPrice();
                default:
                    return null;
            }
        }
    }
private service s;
private JTable itemTb;
private ItemModel itModel;
    public GUI(){
        s=new service();
           setTitle("SAIM GAY TABLE");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 1));

        itModel= new ItemModel();
        
        // Add 2 dummy data items to database
        ArrayList<Item> dummyItems = new ArrayList<>();
        dummyItems.add(new Item("DUMMY1", 100, new java.math.BigDecimal("25.99")));
        dummyItems.add(new Item("DUMMY2", 50, new java.math.BigDecimal("75.50")));
        s.addItem(dummyItems);
        
        // Load all items from database using service
        ArrayList<Item> loadedItems = s.getItems();
        for(Item item : loadedItems){
            itModel.addItem(item);
        }
        
        itemTb=new JTable(itModel);

        add(new JScrollPane(itemTb));
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setVisible(true);
    }

}
