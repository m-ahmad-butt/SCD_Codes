package org.example.layerdArchitecture.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private final List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        // merge by code: if item exists, increase quantity
        for (Item it : items) {
            if (it.equals(item)) {
                it.setQuantity(it.getQuantity() + item.getQuantity());
                return;
            }
        }
        items.add(item);
    }

    public boolean removeItemByCode(String code) {
        return items.removeIf(i -> i.getCode().equals(code));
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Item i : items) {
            total = total.add(i.total());
        }
        return total;
    }

    public int size() {
        return items.size();
    }
}
