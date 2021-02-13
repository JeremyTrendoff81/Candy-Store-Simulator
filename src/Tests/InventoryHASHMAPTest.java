package Tests;

import SYSC2004.InventoryHASHMAP;
import SYSC2004.Product;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class InventoryHASHMAPTest {

    @Test
    void getStock() {
        HashMap<Product, Integer> quantities = new HashMap<>();

        quantities.put(new Product("P1", 1, 1.00), 10);
        quantities.put(new Product("P2", 2, 2.00), 20);

        InventoryHASHMAP ihm = new InventoryHASHMAP(quantities);

        assertEquals(10, ihm.getStock(1));
        assertEquals(20, ihm.getStock(2));
        assertEquals(-1, ihm.getStock(5));
    }

    @Test
    void addStock() {
        HashMap<Product, Integer> quantities = new HashMap<>();

        quantities.put(new Product("P1", 1, 1.00), 10);
        quantities.put(new Product("P2", 2, 2.00), 20);

        InventoryHASHMAP ihm = new InventoryHASHMAP(quantities);

        ihm.addStock(new Product("P1", 1, 1.00), 10);
        assertEquals(20, ihm.getStock(1));

        ihm.addStock(new Product("P2", 2, 2.00), 10);
        assertEquals(30, ihm.getStock(2));

        Product newP = new Product("Test", 5, 5.00);
        assertEquals(-1, ihm.getStock(5));

        ihm.addStock(newP, 5);
        assertTrue(quantities.containsKey(newP));
        assertEquals(5, ihm.getStock(5));
    }

    @Test
    void removeStock() {
        HashMap<Product, Integer> quantities = new HashMap<>();

        quantities.put(new Product("P1", 1, 1.00), 10);
        quantities.put(new Product("P2", 2, 2.00), 20);

        InventoryHASHMAP ihm = new InventoryHASHMAP(quantities);

        boolean test1 = ihm.removeStock(1, 5);
        assertTrue(test1);
        assertEquals(5, ihm.getStock(1));

        boolean test2 = ihm.removeStock(1, 5);
        assertTrue(test2);
        assertEquals(-1, ihm.getStock(1));

        boolean test3 = ihm.removeStock(2, 25);
        assertFalse(test3);
        assertEquals(20, ihm.getStock(2));

        boolean test4 = ihm.removeStock(5, 10);
        assertFalse(test4);
    }

    @Test
    void getProductPrice() {
        HashMap<Product, Integer> quantities = new HashMap<>();

        quantities.put(new Product("P1", 1, 1.00), 10);
        quantities.put(new Product("P2", 2, 2.00), 20);

        InventoryHASHMAP ihm = new InventoryHASHMAP(quantities);

        assertEquals(1.00, ihm.getProductPrice(1));
        assertEquals(2.00, ihm.getProductPrice(2));
        assertEquals(-1.00, ihm.getProductPrice(5));
    }

    @Test
    void getProductName() {
        HashMap<Product, Integer> quantities = new HashMap<>();

        quantities.put(new Product("P1", 1, 1.00), 10);
        quantities.put(new Product("P2", 2, 2.00), 20);

        InventoryHASHMAP ihm = new InventoryHASHMAP(quantities);

        assertEquals("P1", ihm.getProductName(1));
        assertEquals("P2", ihm.getProductName(2));
        assertNull(ihm.getProductName(5));
    }
}