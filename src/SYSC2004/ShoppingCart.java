package SYSC2004;

import java.util.ArrayList;

/**
 * Authors: Jeremy Trendoff - 101160306, Evan Smedley - 101148695
 *
 * Primary Class Developer: Jeremy Trendoff - 101160306
 * Contributor: Evan Smedley - 101148695
 *
 * Date of Completion: Feb 04, 2021
 *
 * Class Description: The ShoppingCart class will maintain the products the user chooses to purchase.
 */

public class ShoppingCart {
    private ArrayList<Product> shoppingCart;    // An ArrayList to hold the Shopping Cart Items.

    /* Default Constructor. Initialize shoppingCart as a new ArrayList. */
    public ShoppingCart() {
        shoppingCart = new ArrayList<>();
    }

    /* Overloaded Constructor. Initialize shoppingCart with an existing ArrayList */
    public ShoppingCart(ArrayList<Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    /* Add a product to the shoppingCart. */
    public void addToCart(Product product) {
        try {
            shoppingCart.add(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Get the shoppingCart. */
    public ArrayList<Product> getShoppingCart() {
        return shoppingCart;
    }

    /* Add a specified amount of product to the shoppingCart */
    public void addToCart(Product product, int quantity) {
        try {
            for (int i = 0; i < quantity; i++) {
                shoppingCart.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Remove a product from the shoppingCart */
    public boolean removeFromCart(Product product) {
        try {
            if (shoppingCart.contains(product)) {
                shoppingCart.remove(product);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /* Remove a specified amount of product from the shoppingCart */
    public boolean removeFromCart(Product product, int quantity) {
        int count = 0;     // The amount of product removed.

        try {
            for (int i = 0; i < quantity; i++) {
                if (shoppingCart.contains(product)) {
                    shoppingCart.remove(product);
                    count++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count == quantity;
    }

    /* Get a product from the shoppingCart */
    public Product getProduct(int id) {
        try {
            for (Product p : shoppingCart) {
                if (p.getId() == id) {
                    return p;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /* Print the contents of the shoppingCart. */
    public void printShoppingCart() {
        try {
            for (Product p : shoppingCart) {
                System.out.print("Name: " + p.getName() + " ID: " + p.getId() + " Price: " + p.getPrice() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
