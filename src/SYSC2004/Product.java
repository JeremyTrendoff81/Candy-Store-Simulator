package SYSC2004;

/**
 * Authors: Jeremy Trendoff - 101160306, Evan Smedley - 101148695
 *
 * Primary Class Developer: Jeremy Trendoff - 101160306
 * Contributor: Evan Smedley - 101148695
 *
 * Date of Completion: Jan 25, 2021
 *
 * Class Description: The product class will store information about the items being sold by the store.
 */

public class Product {
    private final String name;  // The name of the product
    private final int id;    // The id of the product
    private final double price; // The price of the product

    /**
     * Constructor to initialize a product.
     *
     * @param name Parameter, of type String, to represent the name of the product.
     * @param id Parameter, of type int, to represent the id of the product.
     * @param price Parameter, of type double, to represent the price of the product.
     */
    public Product(String name, int id, double price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    /**
     * Get the name of the product
     *
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the id of the product.
     *
     * @return Return the id of the product.
     */
    public int getId() {
        return id;
    }

    /**
     * Get the price of the product.
     *
     * @return Return the price of the product.
     */
    public double getPrice() {
        return price;
    }
}
