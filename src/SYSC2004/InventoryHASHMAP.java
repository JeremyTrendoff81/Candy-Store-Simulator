package SYSC2004;

import java.util.HashMap;

/**
 * Authors: Jeremy Trendoff - 101160306, Evan Smedley - 101148695
 *
 * Primary Class Developer: Jeremy Trendoff - 101160306
 * Contributor: Evan Smedley - 101148695
 *
 * Date of Completion: Feb 12, 2021
 *
 * Class Description: The inventory class will keep track of all the products in the store.
 */

public class InventoryHASHMAP {
    private final HashMap<Product, Integer> stockMap;       // A HashMap to hold the types of Products and their quantities.

    /**
     * Initialize the inventory of the store.
     *
     * @param stockMap Represents the contents of the inventory.
     */
    public InventoryHASHMAP(HashMap<Product, Integer> stockMap) {
        this.stockMap = stockMap;
    }

    /**
     * Check if a Product is in the inventory (i.e. is a key in the stockMap HashMap).
     *
     * @param id Represents the id of the Product to be checked.
     * @return Return the Product if it is in the inventory. Otherwise, return null.
     */
    private Product haveProduct(int id) {
       for (Product p : stockMap.keySet()) {
           if (p.getId() == id) {
               return p;
           }
       }

       return null;
    }

    /**
     * Get the amount of stock for a give Product ID.
     *
     * @param id Represents the id of the Product to be checked.
     * @return Return the amount of stock. Return -1 if the method fails.
     */
    public int getStock(int id) {
        Product p = haveProduct(id);

        if (p != null) {
            return stockMap.get(p);
        }

        return -1;
    }

    /**
     * Add a specified amount of stock of a given product to the inventory.
     *
     * @param product Parameter, of type Product, to represent the given product.
     * @param quantity Parameter, of type int, to represent the quantity of stock to add, must be >= 0.
     */
    public void addStock(Product product, int quantity) {
        Product p = haveProduct(product.getId());

        if (p != null) {
           stockMap.replace(p, stockMap.get(p) + quantity);
        } else {
            stockMap.put(product, quantity);
        }
    }

    /**
     * Remove an amount of stock of a given product ID.
     *
     * @param id Parameter, of type int, to represent the product ID.
     * @param quantity Parameter, of type int, to represent the quantity of stock to remove, must be >= 0.
     * @return Return true if the products were removed properly.
     */
    public boolean removeStock(int id, int quantity) {
        Product p = haveProduct(id);

        if (p != null) {
            if (stockMap.get(p) >= quantity) {
                if (stockMap.get(p) == quantity) {
                    stockMap.remove(p);
                } else {
                    stockMap.replace(p, stockMap.get(p) - quantity);
                }
                return true;
            }
        }

        return false;
    }

    /**
     * Get the name of a product of the given product ID.
     *
     * @param id Parameter, of type int, to represent the product ID.
     * @return Return null if method fails.
     */
    public String getProductName(int id) {
        Product p = haveProduct(id);

        if (p != null) {
            return p.getName();
        }

        return null;
    }

    /**
     * Get the price of a product from the given product ID.
     *
     * @param id Parameter, of type int, to represent the product ID.
     * @return Return -1.0 if method fails.
     */
    public double getProductPrice(int id) {
        Product p = haveProduct(id);

        if (p != null) {
            return p.getPrice();
        }

        return -1.0;
    }
}
