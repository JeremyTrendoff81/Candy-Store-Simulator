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

    /*
    Use an ArrayList of purchases to find the total price for all the purchases and remove products purchased from
    the inventory. Returns a negative double and doesn't remove any of the purchases from inventory if one of the
    products is not available in the inventory.
     */
    public double processTransaction(ArrayList <int[]> purchases) {
        double totalPrice = 0.0;
        double tempPrice;
        for (int[] item : purchases) {
            tempPrice = inventory.getProductPrice(item[0]);
            if (tempPrice < 0) {
                return tempPrice;
            } else {
                totalPrice += tempPrice * item[1];
            }
        }
        for (int[] item : purchases) {
            inventory.removeStock(item[0], item[1]);
        }
        return totalPrice;
    }
}
