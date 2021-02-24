package SYSC2004.Milestone2;

import java.util.ArrayList;

/**
 * The inventory class will keep track of all the products in the store.
 *
 * @author Jeremy Trendoff - 101160306, Evan Smedley - 101148695
 *
 * @Primary_Class_Developer: Jeremy Trendoff - 101160306
 * @Contributor: Evan Smedley - 101148695
 *
 * @version 4.0
 * @since Feb 23, 2021
 */
public class Inventory {
    private final ArrayList<Product> productList;
    private final ArrayList<Integer> stockList;

    /**
     * Constructor for Inventory. Initialize the Inventory to be empty.
     */
    public Inventory() {
        productList = new ArrayList<>();
        stockList = new ArrayList<>();
    }

    /**
     * Constructor for Inventory. Initialize the Inventory from default values.
     *
     * @param productList An ArrayList<Product></Product> to represent a list of products.
     * @param stockList   An ArrayList<Integer></Integer> to represent each products quantity.
     */
    public Inventory(ArrayList<Product> productList, ArrayList<Integer> stockList) {
        this.productList = productList;
        this.stockList = stockList;
    }

    /**
     *  Check if a product is in the inventory.
     *
     * @param id    An int value representing the ID of the product.
     * @return      An int value representing the index of the product and its quantity in the inventories ArrayLists.
     */
    private int haveProduct(int id) {
       for (int i = 0; i < productList.size(); i++) {
           if (productList.get(i).getID() == id) {
               return i;
           }
       }
       return -1;
    }

    /**
     * Add a specified quantity of a product to the inventory.
     *
     * @param product   A Product object to represent the product to be added.
     * @param quantity  An int value to represent the quantity of product to be added.
     */
    public void addStock(Product product, int quantity) {
        int i = haveProduct(product.getID());

        if (i != -1) {
            stockList.set(i, stockList.get(i) + quantity);
        } else {
            productList.add(product);
            stockList.add(quantity);
        }
    }

    /**
     * Remove a given amount of stock of a product.
     *
     * @param id    An int value representing the ID of the product.
     * @param quantity  An int value to represent the quantity of product to be removed.
     * @return  True if the products were removed properly, otherwise false.
     */
    public boolean removeStock(int id, int quantity) {
        int i = haveProduct(id);     // The index of the product and its related quantity

        if ((i != -1) && (stockList.get(i) >= quantity)) {
            if (stockList.get(i) == quantity) {
                productList.remove(i);
                stockList.remove(i);
            } else {
                stockList.set(i, stockList.get(i) - quantity);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get the product ID for a given index in the inventories productList.
     *
     * @param index An int to represent the index of the product.
     * @return  An int value representing the product ID.
     */
    public int getProductID(int index) {
        return productList.get(index).getID();
    }

    /**
     * Get the name of a product of the given product ID.
     *
     * @param id Parameter, of type int, to represent the product ID.
     * @return Return null if method fails.
     */
    public String getProductName(int id) {
        int i = haveProduct(id);

        if (i != -1) {
            return productList.get(i).getName();
        } else {
            return null;
        }
    }

    /**
     * Get the price of a product from the given product ID.
     *
     * @param id Parameter, of type int, to represent the product ID.
     * @return Return -1.0 if method fails.
     */
    public double getProductPrice(int id) {
        int i = haveProduct(id);

        if (i != -1) {
            return productList.get(i).getPrice();
        } else {
            return -1.0;
        }
    }

    /**
     * Get the amount of stock for a give product ID.
     *
     * @param id    An int value representing the product ID.
     * @return  An int value representing the quantity of the product.
     */
    public int getStock(int id) {
        int i = haveProduct(id);

        if (i != -1) {
            return stockList.get(i);
        }
        return -1;
    }

    /**
     * Get the number of products in the inventory.
     *
     * @return  An int value representing the number of products in the inventory.
     */
    public int getNumProducts() {
        return productList.size();
    }
}
