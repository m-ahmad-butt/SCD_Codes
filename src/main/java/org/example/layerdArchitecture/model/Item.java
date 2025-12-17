package org.example.layerdArchitecture.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {
    private final String code;
    private int quantity;
    private BigDecimal price;

    public Item(String code, int quantity, BigDecimal price) {
        if (code == null || code.isEmpty()) throw new IllegalArgumentException("code required");
        if (quantity < 0) throw new IllegalArgumentException("quantity must be >= 0");
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("price must be >= 0");
        this.code = code;
        this.quantity = quantity;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("quantity must be >= 0");
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("price must be >= 0");
        this.price = price;
    }

    public BigDecimal total() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(code, item.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Item{" +
                "code='" + code + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
