package SYSC2004;

/**
 * Authors: Jeremy Trendoff - 101160306, Evan Smedley - 101148695
 *
 * Date of Completion: Jan 25, 2021
 *
 * Class Description: The product class will store information about the items being sold by the store.
 */

public class Product {
    private final String name;  // The name of the product
    private final String id;    // The id of the product
    private final double price; // The price of the product

    /* Constructor to initialize a product. */
    public Product(String name, String id, double price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    /* Get the name of the product */
    public String getName() {
        return name;
    }

    /* Get the id of the product */
    public String getId() {
        return id;
    }

    /* Get the price of the product */
    public double getPrice() {
        return price;
    }
}
