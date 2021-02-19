package SYSC2004.Milestone1_HashMap;

import java.util.HashMap;

/**
 * Authors: Jeremy Trendoff - 101160306, Evan Smedley - 101148695
 *
 * Primary Class Developer: Jeremy Trendoff - 101160306
 * Contributor: Evan Smedley - 101148695
 *
 * Date of Completion: Feb 12, 2021
 *
 * Class Description: The ShoppingCart class will maintain the products the user chooses to purchase.
 */

public class ShoppingCart {
    private final HashMap<Product, Integer> shoppingCart;     // A HashMap to hold the Products in the ShoppingCart.

    /**
     * Initialize the ShoppingCart as empty.
     */
    public ShoppingCart() {
        shoppingCart = new HashMap<>();
    }

    /**
     * Get The current ShoppingCart.
     *
     * @return Return the shoppingCart HashMap.
     */
    public HashMap<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

    /**
     * Check if a Product is in the shoppingCart (i.e. is a key in the shoppingCart HashMap).
     *
     * @param id Represents the id of the Product to be checked.
     * @return Return the Product if it is in the cart. Otherwise, return null.
     */
    public Product haveProduct(int id) {
        for (Product p : shoppingCart.keySet()) {
            if (p.getId() == id) {
                return p;
            }
        }

        return null;
    }

    /**
     * Add a specified amount of stock of a given product to the cart.
     *
     * @param product Parameter, of type Product, to represent the given product.
     * @param quantity Parameter, of type int, to represent the quantity of stock to add, must be >= 0.
     */
    public void addToCart(Product product, int quantity) {
        Product p = haveProduct(product.getId());

        if (p != null) {
            shoppingCart.replace(p, shoppingCart.get(p) + quantity);
        } else {
            shoppingCart.put(product, quantity);
        }
    }

    /**
     * Remove an amount of stock of a given product ID.
     *
     * @param id Parameter, of type int, to represent the product ID.
     * @param quantity Parameter, of type int, to represent the quantity of stock to remove, must be >= 0.
     * @return Return true if the products were removed properly.
     */
    public boolean removeFromCart(int id, int quantity) {
        Product p = haveProduct(id);

        if (p != null) {
            if (shoppingCart.get(p) >= quantity) {
                if (shoppingCart.get(p) == quantity) {
                    shoppingCart.remove(p);
                } else {
                    shoppingCart.replace(p, shoppingCart.get(p) - quantity);
                }
                return true;
            }
        }

        return false;
    }
}
