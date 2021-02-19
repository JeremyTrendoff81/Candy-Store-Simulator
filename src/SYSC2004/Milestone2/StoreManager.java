package SYSC2004.Milestone2;

import java.util.ArrayList;

/**
 * Authors: Jeremy Trendoff - 101160306, Evan Smedley - 101148695
 *
 * Primary Class Developer: Evan Smedley - 101148695
 * Contributor: Jeremy Trendoff - 101160306
 *
 * Date of Completion: Feb 12, 2021
 *
 * Class Description: The StoreManager manages the Inventory, ShoppingCarts and provides
 * information to the StoreView class.
 */

public class StoreManager {

    /**
     *
     */
    private final Inventory inventory = new Inventory();     // The inventory object that the StoreManager manages

    /**
     *
     */
    private final ArrayList<ShoppingCart> carts = new ArrayList<ShoppingCart>();

    /**
     *
     */
    private int numStoreView = -1;

    /**
     *
     * @return
     */
    public int assignNewCartID() {
        numStoreView += 1;
        carts.add(new ShoppingCart());
        return numStoreView;
    }

    /**
     *
     * @param index
     * @param location
     * @return
     */
    public int getId(int index, int location) {
        if (location == -1) {
            return inventory.getProductID(index);
        } else if ((location >= 0) && (location < carts.size())) {
            return carts.get(location).getProductID(index);
        } else {
            return -1;
        }
    }

    /**
     *
     * @param id
     * @param location
     * @return
     */
    public String getName(int id, int location) {
        if (location == -1) {
            return inventory.getProductName(id);
        } else if ((location >= 0) && (location < carts.size())) {
            return carts.get(location).getProductName(id);
        } else {
            return null;
        }
    }

    /**
     *
     * @param id
     * @param location
     * @return
     */
    public double getPrice(int id, int location) {
        if (location == -1) {
            return inventory.getProductPrice(id);
        } else if ((location >= 0) && (location < carts.size())) {
            return carts.get(location).getProductPrice(id);
        } else {
            return -1.0;
        }
    }

    /**
     *
     * @param id
     * @param location
     * @return
     */
    public int getStock(int id, int location) {
        if (location == -1) {
            return inventory.getStock(id);
        } else if ((location >= 0) && (location < carts.size())) {
            return carts.get(location).getStock(id);
        } else {
            return -1;
        }
    }

    public int getNumProducts(int location) {
        if (location == -1) {
            return inventory.getNumProducts();
        } else if ((location >= 0) && (location < carts.size())) {
            return carts.get(location).getNumProducts();
        } else {
            return -1;
        }
    }

    /**
     *
     * @param product
     * @param quantity
     * @param location
     */
    public void add(Product product, int quantity, int location) {
        if (location == -1) {
            inventory.addStock(product, quantity);
        } else if ((location >= 0) && (location < carts.size())) {
            carts.get(location).addStock(product, quantity);
        } else {
            System.out.println("Invalid Cart/Inventory to access");
        }
    }

    /**
     *
     * @param id
     * @param quantity
     * @param location
     * @return
     */
    public boolean remove(int id, int quantity, int location) {
        if (location == -1) {
            return inventory.removeStock(id, quantity);
        } else if ((location >= 0) && (location < carts.size())) {
            return carts.get(location).removeStock(id, quantity);
        } else {
            return false;
        }
    }


}
