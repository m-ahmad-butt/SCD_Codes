package org.example.testing;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ItemCartTest {

    @Test
    public void testAddItemAndTotal() {
        Cart cart = new Cart();
        Item a = new Item("A", 2, new BigDecimal("10.50"));
        Item b = new Item("B", 1, new BigDecimal("5.00"));
        cart.addItem(a);
        cart.addItem(b);

        assertEquals(2, cart.size());
        assertEquals(new BigDecimal("26.00"), cart.getTotal());
    }

    @Test
    public void testMergeQuantityWhenAddingSameCode() {
        Cart cart = new Cart();
        Item a1 = new Item("A", 1, new BigDecimal("2.00"));
        Item a2 = new Item("A", 3, new BigDecimal("2.00"));
        cart.addItem(a1);
        cart.addItem(a2);

        assertEquals(1, cart.size());
        assertEquals(new BigDecimal("8.00"), cart.getTotal());
        assertEquals(4, cart.getItems().get(0).getQuantity());
    }

    @Test
    public void testRemoveItem() {
        Cart cart = new Cart();
        Item a = new Item("A", 1, new BigDecimal("1.00"));
        cart.addItem(a);
        assertTrue(cart.removeItemByCode("A"));
        assertFalse(cart.removeItemByCode("A"));
        assertEquals(0, cart.size());
    }

    @Test
    public void testItemValidation() {
        assertThrows(IllegalArgumentException.class, () -> new Item(null, 1, new BigDecimal("1.00")));
        assertThrows(IllegalArgumentException.class, () -> new Item("X", -1, new BigDecimal("1.00")));
        assertThrows(IllegalArgumentException.class, () -> new Item("X", 1, new BigDecimal("-1.00")));
    }
}
