package Tests;

import SYSC2004.Inventory;
import SYSC2004.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Integer> amount = new ArrayList<>();

    Inventory inventory;

    @Test
    void getStock() {
        products.add(new Product("P1", 1, 1.00));
        amount.add(15);

        products.add(new Product("P2", 2, 2.00));
        amount.add(7);

        inventory = new Inventory(products, amount);

        assertEquals(15, inventory.getStock(1));
        assertEquals(7, inventory.getStock(2));
        assertEquals(-1, inventory.getStock(100));
    }

    @Test
    void addStock() {
    }

    @Test
    void removeStock() {
        products.add(new Product("P1", 1, 1.00));
        amount.add(15);

        products.add(new Product("P2", 2, 2.00));
        amount.add(7);

        inventory = new Inventory(products, amount);

        assertTrue(inventory.removeStock(1, 6));
        assertFalse(inventory.removeStock(2, 8));
    }

    @Test
    void getProductName() {
    }

    @Test
    void getProductPrice() {
    }
}