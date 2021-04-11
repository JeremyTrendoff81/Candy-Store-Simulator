package SYSC2004.Milestone3.store;

/**
 * The ProductStockContainer interface provides a set of methods the Inventory class, and subsequent child classes,
 * will implement.
 *
 * @author Jeremy Trendoff - 101160306, Evan Smedley - 101148695
 * @since April 11th, 2021
 * @version 1.0
 */
public interface ProductStockContainer {
    /**
     * Get the amount of stock for a give product.
     *
     * @param product Product, the product whose stock is being accessed
     * @return  An int value representing the quantity of the product.
     */
    public abstract int getProductQuantity(Product product);

    /**
     * Add a specified quantity of a product to the inventory.
     *
     * @param product   A Product object to represent the product to be added.
     * @param quantity  An int value to represent the quantity of product to be added (must be positive).
     */
    public abstract void addProductQuantity(Product product, int quantity);

    /**
     * Remove a given amount of stock of a product.
     *
     * @param product Product, the product whose stock is being accessed
     * @param quantity  An int value to represent the quantity of product to be removed (must be positive).
     * @return  True if the products were removed properly, otherwise false.
     */
    public abstract boolean removeProductQuantity(Product product, int quantity);

    /**
     * Get the number of products.
     *
     * @return  An int value representing the number of products in the inventory.
     */
    public abstract int getNumOfProducts();
}
