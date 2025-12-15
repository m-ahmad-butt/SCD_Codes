package org.example.table.shopping_cart;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

class Items {
    // id, name, price, expiry date, stock
    public Integer id;
    public String name;
    public Double price;
    public Date expiry;
    public Integer stock;
}

class CartItem{
    // id, name, price, quantity
    public Integer id;
    public String name;
    public Double price;
    public Integer quantity;
}

class ItemModel extends AbstractTableModel{
    public List<Items> items = new ArrayList<>();
    String[] columnNames = {"ID","Name","Price","Expiry","Stock"};

    @Override
    public int getRowCount() {
        return items.size();
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
        Items temp = items.get(rowIndex);
        switch (columnIndex){
            case 0:
                return temp.id;
            case 1:
                return temp.name;
            case 2:
                return temp.price;
            case 3:
                return temp.expiry;
            case 4:
                return temp.stock;
        }
        return null;
    }

    public boolean decrementStock(int index){
        Items item = items.get(index);

        if (item.stock <= 0) {
            return false;   // no stock left
        }

        item.stock--;
        fireTableRowsUpdated(index, index);
        return true;
    }

    public void incrementStock(int index){
        items.get(index).stock++;
        fireTableRowsUpdated(index, index);
    }
}


class CartModel extends AbstractTableModel{
    public List<CartItem> cartItems = new ArrayList<>();
    public Hashtable<Integer, Integer> hashtable = new Hashtable<>(); // id, quantity
    String[] columnNames = {"ID","Name","Price","Quantity"};

    @Override
    public int getRowCount() {
        return cartItems.size();
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
        CartItem temp = cartItems.get(rowIndex);
        switch (columnIndex){
            case 0:
                return temp.id;
            case 1:
                return temp.name;
            case 2:
                return temp.price;
            case 3:
                return hashtable.get(temp.id);
        }
        return null;
    }

    public void addToCart(Items i){
        if(hashtable.containsKey(i.id)){
            int count = hashtable.get(i.id);
            count++;
            hashtable.put(i.id, count);

            for (int idx = 0; idx < cartItems.size(); idx++) {
                if (cartItems.get(idx).id.equals(i.id)) {
                    fireTableRowsUpdated(idx, idx);
                    break;
                }
            }
        }
        else{
            CartItem temp = new CartItem();
            temp.id = i.id;
            temp.name = i.name;
            temp.price = i.price;
            temp.quantity = 1;
            cartItems.add(temp);
            hashtable.put(i.id, 1);

            int newRowIndex = cartItems.size() - 1;
            fireTableRowsInserted(newRowIndex, newRowIndex);
        }
    }

    public void removeFromCart(Integer index) {
        if (index == null) return;
        if (index < 0 || index >= cartItems.size()) return;

        CartItem item = cartItems.get(index);
        Integer itemId = item.id;

        Integer qty = hashtable.get(itemId);
        if (qty == null || qty <= 0) {
            hashtable.remove(itemId);
            cartItems.remove((int)index);
            fireTableRowsDeleted(index, index);
            return;
        }

        if (qty == 1) {
            hashtable.remove(itemId);
            cartItems.remove((int)index);
            fireTableRowsDeleted(index, index);
        } else {
            // Decrement quantity
            hashtable.put(itemId, qty - 1);
            fireTableRowsUpdated(index, index);
        }
    }
}

public class View extends JFrame {
    public Integer selectedIndexItem;
    public Integer selectedIndexCart;
    private JTextField upperTF;
    JButton searchBtn;
    JButton addBtn;
    JButton removeBtn;
    JTable itemTable;
    JTable cartTable;
    ItemModel iModel;
    CartModel cModel;
    public JLabel totalPrice;
    public Double price = 0.0d;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public View() {
        // main panel
        JPanel mainPnl = new JPanel(new BorderLayout());
        this.setContentPane(mainPnl);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 400);

        JPanel lower = new JPanel(new FlowLayout());
        JPanel middle = new JPanel();
        middle.setLayout(new BoxLayout(middle, BoxLayout.X_AXIS));

        // upper panel
        JPanel upper = new JPanel(new FlowLayout());
        JLabel upperL = new JLabel("Search Item");
        upperTF = new JTextField("");
        upperTF.setColumns(10);
        searchBtn = new JButton("Search");
        upper.add(upperL);
        upper.add(upperTF);
        upper.add(searchBtn);

        // lower panel
        addBtn = new JButton("Add");
        removeBtn = new JButton("Remove");
        totalPrice = new JLabel("Total Price " + price);
        lower.add(addBtn);
        lower.add(removeBtn);
        lower.add(totalPrice);

        // middle panel
        this.cModel = new CartModel();
        cartTable = new JTable(cModel);

        this.iModel = new ItemModel();
        itemTable = new JTable(iModel);

        middle.add(new JScrollPane(itemTable));
        middle.add(new JScrollPane(cartTable));

        itemTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedIndexItem = itemTable.getSelectedRow();
            }
        });

        cartTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedIndexCart = cartTable.getSelectedRow();
            }
        });

        mainPnl.add(upper, BorderLayout.NORTH);
        mainPnl.add(middle, BorderLayout.CENTER);
        mainPnl.add(lower, BorderLayout.SOUTH);
    }

    public void BtnLinker(ActionListener e) {
        searchBtn.addActionListener(e);
        addBtn.addActionListener(e);
        removeBtn.addActionListener(e);
    }

    public String getSearchText() {
        return upperTF.getText();
    }
}