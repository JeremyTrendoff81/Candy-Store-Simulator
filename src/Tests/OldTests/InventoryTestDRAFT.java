package Tests.OldTests;

import SYSC2004.OldCode.InventoryDRAFT;
import SYSC2004.CurrentCode.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Authors: Jeremy Trendoff - 101160306, Evan Smedley - 101148695
 *
 * Primary Class Developer: Jeremy Trendoff - 101160306
 * Contributor: Evan Smedley - 101148695
 *
 * Date of Completion: Jan 27, 2021
 *
 * Class Description: A JUnit 5 test class to test the methods of the Inventory class.
 */

public class InventoryTestDRAFT {
    private Product p1 = new Product("Product 1", 1, 1.00);         // Test Product
    private Product p2 = new Product("Product 2", 2, 1.50);         // Test Product
    private Product p3 = new Product("Product 3", 3, 2.00);         // Test Product

    private ArrayList<Product> arr = new ArrayList<>();                           // Test inventory stock ArrayList
    private InventoryDRAFT i;                                                          // Test Inventory Object


    @Test
    void getStock() {
        /* Add products to the test inventory */
        arr.add(p1);
        arr.add(p2);
        arr.add(p3);

        i = new InventoryDRAFT(arr);     // Initialize Inventory

        /* Check results of getStock() against the expected value */
        assertEquals(i.getStock(1), 1);
        assertEquals(i.getStock(2), 1);
        assertEquals(i.getStock(3), 1);
    }

    @Test
    void addStock() {
        /* Add products to the test inventory */
        arr.add(p1);
        arr.add(p2);
        arr.add(p3);

        i = new InventoryDRAFT(arr);     // Initialize Inventory

        Product p4 = new Product("New Product", 4, 2.50);   // Test product

        /* Expected Resulting ArrayList */
        ArrayList<Product> test = new ArrayList<>();
        test.add(p1);
        test.add(p2);
        test.add(p3);
        test.add(p4);

        /* Test addStock() */
        i.addStock(p4);

        assertEquals(i.getStockList(), test);
    }

    @Test
    void addStockMultiple() {
        /* Add products to the test inventory */
        arr.add(p1);
        arr.add(p2);
        arr.add(p3);

        i = new InventoryDRAFT(arr);     // Initialize Inventory

        Product p4 = new Product("New Product", 4, 2.50);      // Test Product

        /* Expected Resulting ArrayList */
        ArrayList<Product> test = new ArrayList<>();
        test.add(p1);
        test.add(p2);
        test.add(p3);
        test.add(p4);
        test.add(p4);
        test.add(p4);

        /* Test addStock() */
        i.addStock(p4, 3);

        assertEquals(i.getStockList(), test);
    }

    @Test
    void removeStock() {
        /* Add products to the test inventory */
        arr.add(p1);
        arr.add(p2);
        arr.add(p3);

        i = new InventoryDRAFT(arr);     // Initialize Inventory

        /* Expected Resulting ArrayList */
        ArrayList<Product> test = new ArrayList<>();
        test.add(p1);
        test.add(p2);

        /* Test removeStock() */
        boolean deleteResult1 = i.removeStock(p3.getId());
        boolean deleteResult2 = i.removeStock(0);

        assertEquals(i.getStockList(), test);
        assertTrue(deleteResult1);
        assertFalse(deleteResult2);
    }

    @Test
    void removeStockMultiple() {
        /* Add products to the test inventory */
        arr.add(p1);
        arr.add(p2);
        arr.add(p3);
        arr.add(p3);

        i = new InventoryDRAFT(arr);     // Initialize Inventory

        /* Expected Resulting ArrayList */
        ArrayList<Product> test = new ArrayList<>();
        test.add(p1);
        test.add(p2);

        /* Test removeStock() */
        boolean deleteResult1 = i.removeStock(p3.getId(), 2);

        assertEquals(i.getStockList(), test);
        assertTrue(deleteResult1);

        boolean deleteResult2 = i.removeStock(0, 3);
        assertFalse(deleteResult2);

        boolean deleteResult3 = i.removeStock(p2.getId(), 2);
        test.remove(p2);

        assertEquals(i.getStockList(), test);
        assertFalse(deleteResult3);
    }

    @Test
    void getProductName() {
        /* Add products to the test inventory */
        arr.add(p1);
        arr.add(p2);
        arr.add(p3);

        i = new InventoryDRAFT(arr);     // Initialize Inventory

        /* Test getProductName() */
        assertEquals(i.getProductName(1), p1.getName());
        assertEquals(i.getProductName(2), p2.getName());
        assertEquals(i.getProductName(3), p3.getName());
        assertNull(i.getProductName(4));
    }

    @Test
    void getProductPrice() {
        /* Add products to the test inventory */
        arr.add(p1);
        arr.add(p2);
        arr.add(p3);

        i = new InventoryDRAFT(arr);     // Initialize Inventory


        /* Test getProductPrice() */
        assertEquals(i.getProductPrice(1), p1.getPrice());
        assertEquals(i.getProductPrice(2), p2.getPrice());
        assertEquals(i.getProductPrice(3), p3.getPrice());
        assertEquals(i.getProductPrice(4), -2.00);
    }

    @Test
    void getProductInfo() {
        Product[] p1 = new Product[10];     // 10 "P1" test products.
        Product[] p2 = new Product[20];     // 20 "P2" test products.

        /* Initialize "P1" products and add to inventory */
        for (int i = 0; i < p1.length; i++) {
            p1[i] = new Product("P1", 1, 1.00);
            arr.add(p1[i]);
        }

        /* Initialize "P2" products and add to inventory */
        for (int i = 0; i < p2.length; i++) {
            p2[i] = new Product("P2", 2, 2.00);
            arr.add(p2[i]);
        }

        i = new InventoryDRAFT(arr);    // Initialize the inventory.

        /* Test the getProductInfo() method */
        ArrayList<Object> p1Info = i.getProductInfo(1);
        ArrayList<Object> p2Info = i.getProductInfo(2);
        ArrayList<Object> shouldBeNull = i.getProductInfo(3);

        assertNull(shouldBeNull);

        assertEquals("P1", p1Info.get(0));
        assertEquals(1, p1Info.get(1));
        assertEquals(1.00, p1Info.get(2));
        assertEquals(p1.length, p1Info.get(3));

        assertEquals("P2", p2Info.get(0));
        assertEquals(2, p2Info.get(1));
        assertEquals(2.00, p2Info.get(2));
        assertEquals(p2.length, p2Info.get(3));
    }
}