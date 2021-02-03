package Tests;

import SYSC2004.Product;
import SYSC2004.StoreManager;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Authors: Jeremy Trendoff - 101160306, Evan Smedley - 101148695
 *
 * Primary Class Developer: Evan Smedley - 101148695
 * Contributor: Jeremy Trendoff - 101160306
 *
 * Date of Completion: Feb 3, 2021
 *
 * Class Description: A JUnit 5 test class to test the methods of the StoreManager class.
 */

public class StoreManagerTest {
    private StoreManager sm;                                // The StoreManager object to be used in each test.
    private ArrayList<Product> arr = new ArrayList<>();     // An ArrayList to set the current inventory for each test.

    @Test
    void checkStock() {
        Product[] cars = new Product[15];           // 15 "Cars" to be added to the inventory
        Product[] candies = new Product[25];        // 25 "Candies" to be added to the inventory
        Product[] hats = new Product[50];           // 50 "Hats" to be added to the inventory

        /* Initialize all "Car" products and add them to the inventory. */
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Product("Car", 1, 1.50);
            arr.add(cars[i]);
        }

        /* Initialize all "Candy" products and add them to the inventory. */
        for (int i = 0; i < candies.length; i++) {
            candies[i] = new Product("Candy", 2, 2.00);
            arr.add(candies[i]);
        }

        /* Initialize all "Hat" products and add them to the inventory. */
        for (int i = 0; i < hats.length; i++) {
            hats[i] = new Product("Hat", 3, 2.50);
            arr.add(hats[i]);
        }

        /* Check that each ArrayList of Products has been initialized. */
        assertNotEquals(null, cars);
        assertNotEquals(null, candies);
        assertNotEquals(null, hats);

        sm = new StoreManager(arr);     // Initialize the StoreManager.

        assertNotEquals(null, sm);  // Check that the StoreManager is Initialized

        /* Check that the amount of stock in the inventory for each Product, given by checkStock(), is correct. */
        assertEquals(cars.length, sm.checkStock(new Product("Car", 1, 1.50)));
        assertEquals(candies.length, sm.checkStock(new Product("Candy", 2, 2.00)));
        assertEquals(hats.length, sm.checkStock(new Product("Hats", 3, 2.50)));
    }

    @Test
    void processTransaction() {
    }
}