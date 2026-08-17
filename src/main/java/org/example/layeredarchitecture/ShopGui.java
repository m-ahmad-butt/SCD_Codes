package org.example.layeredarchitecture;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.example.layeredarchitecture.model.Item;

//gui -> ShopService -> dao and these all will use model classes in form of arraylist or hashtables
public class ShopGui extends JFrame {
    private ShopService s;
    private JTable itemTb;
    private ItemModel itModel;

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

    public ShopGui(){
        s=new ShopService();
        setTitle("Shop Items");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 1));

        itModel= new ItemModel();
        
        // Add 2 dummy data items to database
        ArrayList<Item> dummyItems = new ArrayList<>();
        dummyItems.add(new Item("DUMMY1", 100, new java.math.BigDecimal("25.99")));
        dummyItems.add(new Item("DUMMY2", 50, new java.math.BigDecimal("75.50")));

        //adding items using ShopService
        s.addItem(dummyItems);
        
        // Load all items from database using ShopService
        ArrayList<Item> loadedItems = s.getItems();
        for(Item item : loadedItems){
            itModel.addItem(item);
        }
        
        itemTb=new JTable(itModel);

        add(new JScrollPane(itemTb));
    }

    public static void main(String[] args) {
        ShopGui gui = new ShopGui();
        gui.setVisible(true);
    }

}
