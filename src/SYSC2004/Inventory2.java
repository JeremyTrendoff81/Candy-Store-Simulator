package SYSC2004;

import java.util.ArrayList;

/**
 * Authors: Jeremy Trendoff - 101160306, Evan Smedley - 101148695
 *
 * Primary Class Developer: Jeremy Trendoff - 101160306
 * Contributor: Evan Smedley - 101148695
 *
 * Date of Completion: Feb 7, 2021
 *
 * Class Description: The inventory class will keep track of all the products in the store.
 */

public class Inventory2 {
    private final ArrayList<Product> productList;      // The list of all the products in the store
    private final ArrayList<Integer> stockList;        // The list of the number of stock of each product in the store

    /* Default Constructor. Initializes productList and stockList to be empty. */
    public Inventory2() {
        productList = new ArrayList<>();
        stockList = new ArrayList<>();
    }


    /* Check if the product exists in productList. Return the index of the product or -1 if it does not exist. */
    private int haveProduct(int id) {
        int productIndex = 0;
        for (Product p : productList) {           // Check if there is a product with the same id in productList
            if (p.getId() == id) {
                return productIndex;              // Return the index of the product in productList
            }
            productIndex += 1;
        }
        return -1;                                // If product is not in productList return -1
    }


    /* Get the amount of stock in the inventory for a given product ID. Return -1 if method fails. */
    public int getStock(int id) {
        int productIndex = haveProduct(id);
        if (productIndex == -1) {
            return productIndex;                  // Return -1 if there is not a product with the same id in productList
        } else {
            return stockList.get(productIndex);   // Rather than iterating through stockList, use random access (faster)
        }
    }


    /* Add a specified amount of stock of a given product to the inventory. */
    public void addStock(Product product, int quantity) {
        int productIndex = haveProduct(product.getId());
        if (productIndex == -1) {
            productList.add(product);       // If the product does not exist in productList, add it to the end
            stockList.add(quantity);        // Add the corresponding quantity of the product to stockList
        } else {
            stockList.set(productIndex, stockList.get(productIndex) + quantity);
        }                                   // If the product exists in productList, update the quantity in stockList
    }


    /* Remove an amount of stock of a given product. Return true if the products were removed properly. */
    public boolean removeStock(int id, int quantity) {
        int productIndex = haveProduct(id);
        if ((productIndex >= 0) && (quantity < 1) && (stockList.get(productIndex) <= quantity)) {
            if (stockList.get(productIndex) == quantity) {
                productList.remove(productIndex);
                stockList.remove(productIndex);
            } else {
                stockList.set(productIndex, stockList.get(productIndex) - quantity);
            }
            return true;
        } else {
            return false;
        }
    }


    /* Get the name of a product of the given product ID. Return null if method fails. */
    public String getProductName(int id) {
        for (Product p : productList) {
            if (p.getId() == id) {
                return p.getName();                 // Return the name of the product
            }
        }
        return null;                                // If the product does not exist in productList, return null
    }


    /* Get the price of a product from the given product ID. Return -1.0 if method fails. */
    public double getProductPrice(int id) {
        for (Product p : productList) {
            if (p.getId() == id) {
                return p.getPrice();                // Return the price of the product
            }
        }
        return -1.0;                                // If the product does not exist in productList, return -1.0
    }
}
