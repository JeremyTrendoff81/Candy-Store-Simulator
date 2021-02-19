package Tests.OldTests;

import SYSC2004.Milestone1_Draft.InventoryARRAYLIST;
import SYSC2004.Milestone1_HashMap.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InventoryARRAYLISTTest {
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Integer> amount = new ArrayList<>();

    InventoryARRAYLIST inventoryARRAYLIST;

    @Test
    void getStock() {
        /* Initialize new Products and the Inventory */
        products.add(new Product("P1", 1, 1.00));
        amount.add(15);

        products.add(new Product("P2", 2, 2.00));
        amount.add(7);

        inventoryARRAYLIST = new InventoryARRAYLIST(products, amount);

        /* Test getStock() */
        assertEquals(15, inventoryARRAYLIST.getStock(1));
        assertEquals(7, inventoryARRAYLIST.getStock(2));
        assertEquals(-1, inventoryARRAYLIST.getStock(100));
    }

    @Test
    void addStock() {
        /* Initialize new Products and the Inventory */
        products.add(new Product("P1", 1, 1.00));
        amount.add(15);

        products.add(new Product("P2", 2, 2.00));
        amount.add(7);

        inventoryARRAYLIST = new InventoryARRAYLIST(products, amount);

        /* Test addStock() */
        inventoryARRAYLIST.addStock(new Product("P1", 1, 1.00), 10);
        inventoryARRAYLIST.addStock(new Product("P2", 2, 2.00), 1);
        inventoryARRAYLIST.addStock(new Product("P3", 3, 3.00), 5);

        assertEquals(25, inventoryARRAYLIST.getStock(1));
        assertEquals(8, inventoryARRAYLIST.getStock(2));
        assertEquals(5, inventoryARRAYLIST.getStock(3));
    }

    @Test
    void removeStock() {
        /* Initialize new Products and the Inventory */
        products.add(new Product("P1", 1, 1.00));
        amount.add(15);

        products.add(new Product("P2", 2, 2.00));
        amount.add(7);

        inventoryARRAYLIST = new InventoryARRAYLIST(products, amount);

        /* Test removeStock() */
        assertTrue(inventoryARRAYLIST.removeStock(1, 6));
        assertEquals(9, inventoryARRAYLIST.getStock(1));

        assertFalse(inventoryARRAYLIST.removeStock(2, 8));
        assertEquals(7, inventoryARRAYLIST.getStock(2));

        assertFalse(inventoryARRAYLIST.removeStock(8, 10));
    }

    @Test
    void getProductName() {
        /* Initialize new Products and the Inventory */
        products.add(new Product("P1", 1, 1.00));
        amount.add(15);

        products.add(new Product("P2", 2, 2.00));
        amount.add(7);

        inventoryARRAYLIST = new InventoryARRAYLIST(products, amount);

        /* Test getProductName */
        assertEquals("P1", inventoryARRAYLIST.getProductName(1));
        assertEquals("P2", inventoryARRAYLIST.getProductName(2));
        assertNull(inventoryARRAYLIST.getProductName(5));
    }

    @Test
    void getProductPrice() {
        /* Initialize new Products and the Inventory */
        products.add(new Product("P1", 1, 1.00));
        amount.add(15);

        products.add(new Product("P2", 2, 2.00));
        amount.add(7);

        inventoryARRAYLIST = new InventoryARRAYLIST(products, amount);

        /* Test getProductPrice() */
        assertEquals(1.00, inventoryARRAYLIST.getProductPrice(1));
        assertEquals(2.00, inventoryARRAYLIST.getProductPrice(2));
        assertEquals(-1, inventoryARRAYLIST.getProductPrice(5));
    }
}