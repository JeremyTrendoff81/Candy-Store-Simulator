package SYSC2004.Milestone1_Draft;

import SYSC2004.Milestone1_HashMap.Product;

import java.util.ArrayList;
import java.util.Collections;

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

public class ShoppingCartDRAFT {
    private final ArrayList<Product> shoppingCart;    // An ArrayList to hold the Shopping Cart Items.

    /* Default Constructor. Initialize shoppingCart as a new ArrayList. */
    public ShoppingCartDRAFT() {
        shoppingCart = new ArrayList<>();
    }

    /* Overloaded Constructor. Initialize shoppingCart with an existing ArrayList */
    public ShoppingCartDRAFT(ArrayList<Product> shoppingCart) {
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
    public boolean removeFromCart(int id) {
        try {
           for (Product p : shoppingCart) {
               if (id == p.getId()) {
                   shoppingCart.remove(p);
                   return true;
               }
           }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /* Remove a specified amount of product from the shoppingCart */
    public boolean removeFromCart(int id, int quantity) {
        int count = 0;     // The amount of product removed.
        ArrayList<Integer> indexes = new ArrayList<>();
                                                    // An ArrayList to hold the index's of the products to be removed.
        try {
            for (Product p : shoppingCart) {
                if (id == p.getId()) {
                    if (count < quantity) {
                        indexes.add(shoppingCart.indexOf(p));
                        count++;
                    }
                }
            }

            indexes.sort(Collections.reverseOrder());

            for (int i : indexes) {
                shoppingCart.remove(i);
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
