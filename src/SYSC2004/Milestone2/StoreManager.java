package SYSC2004.Milestone2;

import java.util.ArrayList;

/**
 * The StoreManager manages the Inventory, ShoppingCarts and provides
 * information to the StoreView class.
 *
 * @author Jeremy Trendoff - 101160306, Evan Smedley - 101148695
 * @version 2.0
 * @since Feb 12, 2021
 */
public class StoreManager {

    /**
     * The inventory object that the StoreManager manages.
     */
    private Inventory inventory;

    /**
     * The users individual ShoppingCart objects.
     */
    private ArrayList<ShoppingCart> carts;

    /**
     * The number of StoreView instances related to this StoreManager.
     */
    private static int numStoreView = 0;

    /**
     * Initialize the StoreManager.
     */
    public StoreManager() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("SYSC2004", 101, 100.00));
        products.add(new Product("SYSC4906", 102, 55.00));
        products.add(new Product("SYSC2006", 103, 45.00));
        products.add(new Product("MUSI1001", 104, 35.00));
        products.add(new Product("CRCJ1000", 105, 0.01));
        products.add(new Product("ELEC4705", 106, 25.00));
        products.add(new Product("SYSC4907", 107, 145.00));

        ArrayList<Integer> stock = new ArrayList<>();
        stock.add(76);
        stock.add(1);
        stock.add(32);
        stock.add(3);
        stock.add(12);
        stock.add(132);
        stock.add(322);

        this.inventory = new Inventory(products, stock);
        this.carts = new ArrayList<>();
    }

    /**
     * Provides a new cart id for a StoreView instance and creates a corresponding ShoppingCart
     *
     * @return int, number of StoreView instances (used as an id for a storeview)
     */
    public int assignNewCartID() {
        this.carts.add(new ShoppingCart());
        return numStoreView++;
    }

    /**
     * Get the id of the product at index 'index', from either a ShoppingCart or the Inventory
     *
     * @param index int, the index of the product id to return
     * @param location int, specifies whether to access a cart or the inventory
     * @return int, the id of the product
     */
    public int getID(int index, int location) {
        if (location == -1) {
            return this.inventory.getProductID(index);
        } else if ((location >= 0) && (location < carts.size())) {
            return this.carts.get(location).getProductID(index);
        } else {
            return -1;
        }
    }

    /**
     * Get the name of the product with the id 'id' from either a ShoppingCart or the Inventory
     *
     * @param id int, the id of the product whose name is being accessed
     * @param location int, specifies whether to access a cart or the inventory
     * @return String, the name of the product
     */
    public String getName(int id, int location) {
        if (location == -1) {
            return this.inventory.getProductName(id);
        } else if ((location >= 0) && (location < carts.size())) {
            return this.carts.get(location).getProductName(id);
        } else {
            return null;
        }
    }

    /**
     * Get the price of the product with the id 'id' from either a ShoppingCart or the Inventory
     *
     * @param id int, the id of the product whose price is being accessed
     * @param location int, specifies whether to access a cart or the inventory
     * @return double, the price of the product
     */
    public double getPrice(int id, int location) {
        if (location == -1) {
            return this.inventory.getProductPrice(id);
        } else if ((location >= 0) && (location < carts.size())) {
            return this.carts.get(location).getProductPrice(id);
        } else {
            return -1.0;
        }
    }

    /**
     * Get the stock of the product with the id 'id' from either a ShoppingCart or the Inventory
     *
     * @param id int, the id of the product whose stock is being accessed
     * @param location int, specifies whether to access a cart or the inventory
     * @return int, the stock of the product
     */
    public int getStock(int id, int location) {
        if (location == -1) {
            return this.inventory.getStock(id);
        } else if ((location >= 0) && (location < carts.size())) {
            return this.carts.get(location).getStock(id);
        } else {
            return -1;
        }
    }

    /**
     * Get the number of different products in either the Inventory or a ShoppingCart
     *
     * @param location int, specifies whether to access a cart or the inventory
     * @return int, the number of different products
     */
    public int getNumProducts(int location) {
        if (location == -1) {
            return this.inventory.getNumProducts();
        } else if ((location >= 0) && (location < carts.size())) {
            return this.carts.get(location).getNumProducts();
        } else {
            return -1;
        }
    }

    /**
     * Add a product to either the Inventory or a ShoppingCart
     *
     * @param product Product, the product to be added
     * @param quantity int, the quantity of the product to be added
     * @param location int, specifies whether to access a cart or the inventory
     */
    public void add(Product product, int quantity, int location) {
        if (location == -1) {
            this.inventory.addStock(product, quantity);
        } else if ((location >= 0) && (location < carts.size())) {
            this.carts.get(location).addStock(product, quantity);
        } else {
            System.out.println("Invalid Cart/Inventory to access");
        }
    }

    /**
     * Remove a product from either the Inventory or a ShoppingCart
     *
     * @param id int, the id of the product to be removed
     * @param quantity int, the quantity of the product to be removed
     * @param location int, specifies whether to access a cart or the inventory
     * @return boolean, true for a successful removal, false otherwise
     */
    public boolean remove(int id, int quantity, int location) {
        if (location == -1) {
            return this.inventory.removeStock(id, quantity);
        } else if ((location >= 0) && (location < carts.size())) {
            return this.carts.get(location).removeStock(id, quantity);
        } else {
            return false;
        }
    }


}
