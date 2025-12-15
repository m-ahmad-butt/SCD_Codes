package org.example.table.shopping_cart;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class controller implements ActionListener {
    private View view;
    private DatabaseManager dbManager;

    public controller(View view){
        this.view = view;
        this.dbManager = new DatabaseManager();

        dbManager.initializeDatabase();
        loadItemsFromDatabase();
        loadCartFromDatabase();

        view.BtnLinker(this);

        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cleanup();
            }
        });
    }

    private void loadItemsFromDatabase() {
        List<Items> items = dbManager.getAllItems();
        view.iModel.items.clear();
        view.iModel.items.addAll(items);
        view.iModel.fireTableDataChanged();
    }

    private void loadCartFromDatabase() {
        List<CartItem> cartItems = dbManager.getCartItems();
        view.cModel.cartItems.clear();
        view.cModel.hashtable.clear();

        for (CartItem item : cartItems) {
            view.cModel.cartItems.add(item);
            view.cModel.hashtable.put(item.id, item.quantity);
        }

        view.cModel.fireTableDataChanged();
        updateTotalPrice();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == view.addBtn){
            // Validate selection
            if (view.selectedIndexItem == null || view.selectedIndexItem < 0) {
                JOptionPane.showMessageDialog(
                        view,
                        "Please select an item to add!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            int idx = view.selectedIndexItem;
            if (idx >= view.iModel.items.size()) return;

            Items selectedItem = view.iModel.items.get(idx);

            if (selectedItem.stock <= 0) {
                JOptionPane.showMessageDialog(
                        view,
                        "Out of stock!",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            boolean success = view.iModel.decrementStock(idx);
            if (!success) {
                return;
            }

            dbManager.updateStock(selectedItem.id, selectedItem.stock);

            view.cModel.addToCart(selectedItem);

            dbManager.addToCart(selectedItem.id);

            updateTotalPrice();
        }
        else if(e.getSource() == view.removeBtn){
            if (view.selectedIndexCart == null || view.selectedIndexCart < 0) {
                JOptionPane.showMessageDialog(
                        view,
                        "Please select an item to remove from cart!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            int cartIdx = view.selectedIndexCart;
            if (cartIdx >= view.cModel.cartItems.size()) return;

            Integer itemId = view.cModel.cartItems.get(cartIdx).id;

            view.cModel.removeFromCart(cartIdx);

            dbManager.removeFromCart(itemId);

            int itemsTableIndex = findItemIndexById(itemId);
            if (itemsTableIndex >= 0) {
                Items item = view.iModel.items.get(itemsTableIndex);
                view.iModel.incrementStock(itemsTableIndex);

                dbManager.updateStock(itemId, item.stock);
            }

            updateTotalPrice();
        }
        else if(e.getSource() == view.searchBtn){
            String searchTerm = view.getSearchText();

            if (searchTerm == null || searchTerm.trim().isEmpty()) {
                loadItemsFromDatabase();
                return;
            }

            List<Items> searchResults = dbManager.searchItems(searchTerm.trim());

            view.iModel.items.clear();
            view.iModel.items.addAll(searchResults);
            view.iModel.fireTableDataChanged();

            if (searchResults.isEmpty()) {
                JOptionPane.showMessageDialog(
                        view,
                        "No items found matching: " + searchTerm,
                        "Search Results",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }

    private int findItemIndexById(Integer id) {
        for (int i = 0; i < view.iModel.items.size(); i++) {
            if (view.iModel.items.get(i).id.equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private void updateTotalPrice(){
        Double result = 0.0;
        for(CartItem c: view.cModel.cartItems){
            if(view.cModel.hashtable.containsKey(c.id)){
                result += c.price * view.cModel.hashtable.get(c.id);
            }
        }
        view.price = result;
        view.totalPrice.setText("Total Price: " + String.format("%.2f", view.price));
    }

    public void cleanup() {
        if (dbManager != null) {
            dbManager.close();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            View view2 = new View();
            view2.setVisible(true);
            controller c = new controller(view2);
        });
    }
}