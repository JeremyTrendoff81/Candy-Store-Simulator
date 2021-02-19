package SYSC2004.Milestone2;

import java.util.ArrayList;

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
public class Inventory {
    private final ArrayList<Product> productList;
    private final ArrayList<Integer> stockList;

    /**
     * Initialize the Inventory to be empty.
     */
    public Inventory() {
        productList = new ArrayList<>();
        stockList = new ArrayList<>();
    }

    /**
     * Initialize the Inventory from default values.
     *
     * @param productList
     * @param stockList
     */
    public Inventory(ArrayList<Product> productList, ArrayList<Integer> stockList) {
        this.productList = productList;
        this.stockList = stockList;
    }

    /**
     *
     * @param id
     * @return
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
     *
     * @param product
     * @param quantity
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
     *
     * @param id
     * @param quantity
     * @return
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
     *
     * @param index
     * @return
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
     *
     * @param id
     * @return
     */
    public int getStock(int id) {
        int i = haveProduct(id);

        if (i != -1) {
            return stockList.get(i);
        }
        return -1;
    }

    /**
     *
     * @return
     */
    public int getNumProducts() {
        return productList.size();
    }
}
