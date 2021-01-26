package SYSC2004;

import java.util.ArrayList;

/**
 * Authors: Jeremy Trendoff - 101160306, Evan Smedley - 101148695
 *
 * Date of Completion: Jan 26, 2021
 *
 * Class Description: The StoreManager manages the Inventory, ShoppingCarts and provides
 * information to the StoreView class.
 */

public class StoreManager {
    private Inventory inventory;  // The inventory object that the StoreManager manages

    /* Default constructor. Creates a new Inventory object to manage upon creation. */
    public StoreManager() {
        inventory = new Inventory(); // New empty Inventory object
    }

    /* Constructor to create a StoreManager object and initialize the Inventory object with products. */
    public StoreManager(ArrayList <Product> stockList) {
        inventory = new Inventory(stockList);
    }

    /* Check how much stock of a given Product is in the inventory. */
    public int checkStock(Product product) {
        return inventory.getStock(product.getId());
    }

    /*  */
    public double processTransaction(ArrayList <int[]> purchases) {
        double totalPrice = 0.0;
        double tempPrice;
        for (int[] item : purchases) {
            tempPrice = inventory.getProductPrice(item[0]);
            if (tempPrice < 0) {
                return tempPrice;
            } else {
                totalPrice += tempPrice * item[1];
                inventory.removeStock(item[0], item[1]);
            }
        }
        return totalPrice;
    }
}
