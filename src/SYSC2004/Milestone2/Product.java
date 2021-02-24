package SYSC2004.Milestone2;

/**
 * The product class will store information about the items being sold by the store.
 *
 * @author Jeremy Trendoff - 101160306, Evan Smedley - 101148695
 * @version 2.0
 * @since Feb 23, 2021
 */

public class Product {

    /**
     * The name of the product
     */
    private final String name;

    /**
     * The id of the product
     */
    private final int id;

    /**
     * The price of the product
     */
    private final double price;

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
     * @return The name of the product as a String.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the id of the product.
     *
     * @return Return the id of the product as an int.
     */
    public int getID() {
        return id;
    }

    /**
     * Get the price of the product.
     *
     * @return Return the price of the product as a double.
     */
    public double getPrice() {
        return price;
    }
}
