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
        /* Initialize new Products and the Inventory */
        products.add(new Product("P1", 1, 1.00));
        amount.add(15);

        products.add(new Product("P2", 2, 2.00));
        amount.add(7);

        inventory = new Inventory(products, amount);

        /* Test getStock() */
        assertEquals(15, inventory.getStock(1));
        assertEquals(7, inventory.getStock(2));
        assertEquals(-1, inventory.getStock(100));
    }

    @Test
    void addStock() {
        /* Initialize new Products and the Inventory */
        products.add(new Product("P1", 1, 1.00));
        amount.add(15);

        products.add(new Product("P2", 2, 2.00));
        amount.add(7);

        inventory = new Inventory(products, amount);

        /* Test addStock() */
        inventory.addStock(new Product("P1", 1, 1.00), 10);
        inventory.addStock(new Product("P2", 2, 2.00), 1);
        inventory.addStock(new Product("P3", 3, 3.00), 5);

        assertEquals(25, inventory.getStock(1));
        assertEquals(8, inventory.getStock(2));
        assertEquals(5, inventory.getStock(3));
    }

    @Test
    void removeStock() {
        /* Initialize new Products and the Inventory */
        products.add(new Product("P1", 1, 1.00));
        amount.add(15);

        products.add(new Product("P2", 2, 2.00));
        amount.add(7);

        inventory = new Inventory(products, amount);

        /* Test removeStock() */
        assertTrue(inventory.removeStock(1, 6));
        assertEquals(9, inventory.getStock(1));

        assertFalse(inventory.removeStock(2, 8));
        assertEquals(7, inventory.getStock(2));

        assertFalse(inventory.removeStock(8, 10));
    }

    @Test
    void getProductName() {
        /* Initialize new Products and the Inventory */
        products.add(new Product("P1", 1, 1.00));
        amount.add(15);

        products.add(new Product("P2", 2, 2.00));
        amount.add(7);

        inventory = new Inventory(products, amount);

        /* Test getProductName */
        assertEquals("P1", inventory.getProductName(1));
        assertEquals("P2", inventory.getProductName(2));
        assertNull(inventory.getProductName(5));
    }

    @Test
    void getProductPrice() {
        /* Initialize new Products and the Inventory */
        products.add(new Product("P1", 1, 1.00));
        amount.add(15);

        products.add(new Product("P2", 2, 2.00));
        amount.add(7);

        inventory = new Inventory(products, amount);

        /* Test getProductPrice() */
        assertEquals(1.00, inventory.getProductPrice(1));
        assertEquals(2.00, inventory.getProductPrice(2));
        assertEquals(-1, inventory.getProductPrice(5));
    }
}