package SYSC2004;

import java.util.ArrayList;

/**
 * Authors: Jeremy Trendoff - 101160306, Evan Smedley - 101148695
 *
 * Primary Class Developer: Evan Smedley - 101148695
 * Contributor: Jeremy Trendoff - 101160306
 *
 * Date of Completion: Jan 26, 2021
 *
 * Class Description: The StoreManager manages the Inventory, ShoppingCarts and provides
 * information to the StoreView class.
 */

public class StoreManager {
    private Inventory inventory;  // The inventory object that the StoreManager manages

    /**
     * Default constructor. Creates a new Inventory object to manage upon creation.
     */
    public StoreManager() {
        inventory = new Inventory(); // New empty Inventory object
    }

    /**
     * Constructor to create a StoreManager object and initialize the Inventory object with products.
     *
     * @param productList Parameter, of type ArrayList<Product></Product>, to represent the initial products to be added to the inventory.
     * @param stockList Parameter, of type ArrayList<Integer></Integer>, to represent the amount of stock for each initial product.
     */
    public StoreManager(ArrayList<Product> productList, ArrayList<Integer> stockList) {
        inventory = new Inventory(productList, stockList);
    }

    /**
     * Check how much stock of a given Product is in the inventory.
     *
     * @param product Parameter, of type Product, to represent the product to be checked.
     * @return Return the amount of stock for the given product. Return -1 if method fails.
     */
    public int checkStock(Product product) {
        return inventory.getStock(product.getId());
    }

    /**
     * Use an ArrayList of purchases to find the total price for all the purchases and remove products purchased from
     * the inventory.
     *
     * @param purchases Parameter, of type int[][], to represent the transactions to be processed.
     * @return Return the total price for the transactions. Returns a negative double and doesn't remove any of the purchases from inventory if one of the
     * products is not available in the inventory.
     */
    public double processTransaction(int[][] purchases) {
        int itemStock;
        for (int[] purchase : purchases) {
            itemStock = inventory.getStock(purchase[0]);
            if ((itemStock < 0) || (itemStock < purchase[1])) {
                return -1.0;
            }
        }
        double totalPrice = 0.0;
        for (int[] purchase : purchases) {
            totalPrice += (inventory.getProductPrice(purchase[0]) * purchase[1]);
            inventory.removeStock(purchase[0], purchase[1]);
        }
        return totalPrice;
    }
}
