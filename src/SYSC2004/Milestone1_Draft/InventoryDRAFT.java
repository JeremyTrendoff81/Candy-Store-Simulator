package SYSC2004.Milestone1_Draft;

import SYSC2004.Milestone1_HashMap.Product;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Authors: Jeremy Trendoff - 101160306, Evan Smedley - 101148695
 *
 * Primary Class Developer: Jeremy Trendoff - 101160306
 * Contributor: Evan Smedley - 101148695
 *
 * Date of Completion: Jan 25, 2021
 *
 * Class Description: The inventory class will keep track of all the products in the store.
 */

public class InventoryDRAFT {
    private final ArrayList<Product> stockList; // The list of all products in the store

    /* Default Constructor. Initializes stockList to be empty. */
    public InventoryDRAFT() {
        stockList = new ArrayList<>(); // new empty ArrayList
    }

    /* Constructor to initialize an inventory based on another ArrayList */
    public InventoryDRAFT(ArrayList<Product> stockList) {
        this.stockList = stockList;
    }

    /* Get the amount of stock in the inventory for a given product ID. Return -1 if method fails. */
    public int getStock(int id) {
        int stock = 0; // The amount of stock

        try {
            for (Product p : stockList) {
                if (id == p.getId()) {
                    stock++;
                }
            }

            return stock;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    /* Add a given product to the inventory */
    public void addStock(Product product) {
        try {
            stockList.add(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Add a specified amount of stock of a given product to the inventory */
    public void addStock(Product product, int quantity) {
        try {
            for (int i = 0; i < quantity; i++) {
                stockList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Remove a given product from the inventory. Return true if the product was removed. */
    public boolean removeStock(int id) {
        try {
            for (Product p : stockList) {
                if (id == p.getId()) {
                    stockList.remove(p);
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /* Remove a specified amount of stock of a given product from the inventory. Return if the products were removed properly. */
    public boolean removeStock(int id, int quantity) {
        ArrayList<Integer> indexes = new ArrayList<>();    // An ArrayList to hold the index's of the products to be removed.
        int count = 0;                                      // The amount of products removed

        try {
            for (Product p : stockList) {
                if (id == p.getId()) {
                    if (count < quantity) {
                        indexes.add(stockList.indexOf(p));
                        count++;
                    }
                }
            }

            indexes.sort(Collections.reverseOrder());

            for (int i : indexes) {
                stockList.remove(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count == quantity;
    }

    /* Get the name of a product of the given product ID. Return null if method fails. */
    public String getProductName(int id) {
        try {
            for (Product p : stockList) {
                if (id == p.getId()) {
                    return p.getName();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /* Get the price of a product from the given product ID. Return -2.0 if method fails. */
    public double getProductPrice(int id) {
        try {
            for (Product p : stockList) {
                if (id == p.getId()) {
                    return p.getPrice();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -2.0;
    }

    /* Get all information for a given Product ID. Return information using an ArrayList formatted [Name, ID, Price, Amount]. Return null if method fails. */
    public ArrayList<Object> getProductInfo(int id) {
        ArrayList<Object> info = new ArrayList<>();     // ArrayList to hold all the product information

        try {
           String name = getProductName(id);       // The name of the product. If null, the product is not in the inventory.

           if (name != null) {
               info.add(name);
               info.add(id);
               info.add(getProductPrice(id));
               info.add(getStock(id));

               return info;
           }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /* Prints out the current inventory */
    public void printInventory() {
        for (Product p : stockList) {
            System.out.println("Name: " + p.getName());
            System.out.println("Id: " + p.getId());
            System.out.println("Price: " + p.getPrice() + "\n");
        }
    }

    /* Get the current inventory */
    public ArrayList<Product> getStockList() {
        return stockList;
    }

}
