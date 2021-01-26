package SYSC2004;

import java.util.ArrayList;

/**
 * Authors: Jeremy Trendoff - 101160306, Evan Smedley - 101148695
 *
 * Date of Completion: Jan 25, 2021
 *
 * Class Description: The inventory class will keep track of all the products in the store.
 */

public class Inventory {
    private ArrayList<Product> stockList; // The list of all products in the store

    /* Default Constructor. Initializes stockList to be empty. */
    public Inventory() {
        stockList = new ArrayList<>(); // new empty ArrayList
    }

    /* Constructor to initialize an inventory based on another ArrayList */
    public Inventory(ArrayList<Product> stockList) {
        this.stockList = stockList;
    }

    /* Get the amount of stock in the inventory for a given product ID */
    public int getStock(int id) {
        int stock = 0; // The amount of stock

        for (Product p : stockList) {
            if (id == p.getId()) {
                stock++;
            }
        }

        return stock;
    }

    /* Add a given product to the inventory */
    public void addStock(Product product) {
        stockList.add(product);
    }

    /* Add a specified amount of stock of a given product to the inventory */
    public void addStock(Product product, int quantity) {
        for (int i = 0; i < quantity; i++) {
            stockList.add(product);
        }
    }

    /* Remove a given product from the inventory  */
    public boolean removeStock(int id) {
        for (Product p : stockList) {
            if (id == p.getId()) {
                stockList.remove(p);
                return true;
            }
        }
        return false;
    }

    /* Remove a specified amount of stock of a given product from the inventory */
    public boolean removeStock(int id, int quantity) {
        int count = 0; // The amount of products removed

        for (Product p : stockList) {
            if (id == p.getId()) {
                if (count < quantity) {
                    stockList.remove(p);
                    count++;
                } else {
                    return true;
                }
            }
        }

        return false;
    }

    /* Get the name of a product of the given product ID */
    public String getProductName(int id) {
        try {
            for (Product p : stockList) {
                if (id == p.getId()) {
                    return p.getName();
                }
            }
        } catch (Exception e) {
            return "ERROR";
        }

        return null;
    }

    /* Get the price of a product from the given product ID */
    public double getProductPrice(int id) {
        try {
            for (Product p : stockList) {
                if (id == p.getId()) {
                    return p.getPrice();
                }
            }
        } catch (Exception e) {
            return -1.0;
        }

        return -2.0;
    }

}
