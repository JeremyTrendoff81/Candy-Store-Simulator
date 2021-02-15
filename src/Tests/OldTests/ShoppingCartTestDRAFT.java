package Tests.OldTests;

import SYSC2004.CurrentCode.Product;
import SYSC2004.OldCode.ShoppingCartDRAFT;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Authors: Jeremy Trendoff - 101160306, Evan Smedley - 101148695
 *
 * Primary Class Developer: Jeremy Trendoff - 101160306
 * Contributor: Evan Smedley - 101148695
 *
 * Date of Completion: Feb 04, 2021
 *
 * Class Description: A JUnit 5 test class to test the methods of the ShoppingCart class.
 */

class ShoppingCartTestDRAFT {
    private ShoppingCartDRAFT sc;                // The ShoppingCart Object
    private ArrayList<Product> cart = new ArrayList<>();        // The test cart

    @Test
    void addToCartSingle() {
        ArrayList<Product> test = new ArrayList<>();
        Product[] products = new Product[15];   // 15 Test Products

        /* Initialize test products and add them to the cart. */
        for (int i = 0; i < products.length; i++) {
            products[i] = new Product("Name", 1, 1.00);
            cart.add(products[i]);
            test.add(products[i]);
        }

        sc = new ShoppingCartDRAFT(cart);    // Initialize ShoppingCart.

        /* Confirm Initialization. */
        assertNotNull(products);
        assertNotNull(sc);
        assertFalse(cart.isEmpty());

        /* Add New Products. */
        Product p4 = new Product("New", 2, 2.00);
        test.add(p4);
        sc.addToCart(p4);

        /* Test addToCart() */
        assertEquals(test, sc.getShoppingCart());
    }

    @Test
    void getShoppingCart() {
        ArrayList<Product> test = new ArrayList<>();
        Product[] products = new Product[15];   // 15 Test Products

        /* Initialize test products and add them to the cart. */
        for (int i = 0; i < products.length; i++) {
            products[i] = new Product("Name", 1, 1.00);
            cart.add(products[i]);
            test.add(products[i]);
        }

        sc = new ShoppingCartDRAFT(cart);    // Initialize ShoppingCart.

        /* Test getShoppingCart() */
        assertEquals(test, sc.getShoppingCart());
    }

    @Test
    void addToCartMultiple() {
        Product[] products = new Product[15];   // 15 Test Products
        ArrayList<Product> test = new ArrayList<>();

        /* Initialize test products and add them to the cart. */
        for (int i = 0; i < products.length; i++) {
            products[i] = new Product("Name", 1, 1.00);
            cart.add(products[i]);
            test.add(products[i]);
        }

        sc = new ShoppingCartDRAFT(cart);    // Initialize ShoppingCart.

        /* Confirm Initialization. */
        assertNotNull(products);
        assertNotNull(sc);
        assertFalse(cart.isEmpty());

        /* Add more to the cart. */
        Product p = new Product("Name", 1, 1.00);
        for (int i = 0; i < 10; i++) {
            test.add(p);
        }

        sc.addToCart(p, 10);

        /* Test addToCart */
        assertEquals(test, sc.getShoppingCart());
    }

    @Test
    void removeFromCartSingle() {
        ArrayList<Product> test = new ArrayList<>();
        Product[] products = new Product[15];   // 15 Test Products

        /* Initialize test products and add them to the cart. */
        for (int i = 0; i < products.length; i++) {
            products[i] = new Product("Name", 1, 1.00);
            cart.add(products[i]);
            test.add(products[i]);
        }

        sc = new ShoppingCartDRAFT(cart);    // Initialize ShoppingCart.

        /* Confirm Initialization. */
        assertNotNull(products);
        assertNotNull(sc);
        assertFalse(cart.isEmpty());

        /* Remove from cart with getProduct() */
        test.remove(sc.getProduct(1));
        boolean r = sc.removeFromCart(1);

        /* Test */
        assertTrue(r);
        assertEquals(test, sc.getShoppingCart());

        /* Test that non-existent products return false */
        r = sc.removeFromCart(19);
        assertFalse(r);
        assertEquals(test, sc.getShoppingCart());
    }

    @Test
    void removeFromCartMultiple() {
        Product p = new Product("Name 1", 1, 1.00);
        Product p1 = new Product("Name 2", 1, 1.00);
        Product p2 = new Product("Name 3", 1, 1.00);
        Product p3 = new Product("Name 4", 1, 1.00);

        ArrayList<Product> test = new ArrayList<>();
        test.add(p);

        cart.add(p);
        cart.add(p1);
        cart.add(p2);
        cart.add(p3);

        sc = new ShoppingCartDRAFT(cart);

        boolean r = sc.removeFromCart(1, 3);

        assertTrue(r);
        assertEquals(p3.getId(), sc.getProduct(1).getId());

        r = sc.removeFromCart(2, 1);
        assertFalse(r);
        assertEquals(p3.getId(), sc.getProduct(1).getId());
    }

    @Test
    void getProduct() {
        Product p1 = new Product("P1", 1, 1.00);    // Test Product 1.
        Product p2 = new Product("P2", 2, 2.00);    // Test Product 2.
        Product p3 = new Product("P3", 3, 3.00);    // Test Product 3.

        cart.add(p1);
        cart.add(p2);
        cart.add(p3);

        sc = new ShoppingCartDRAFT(cart);

        /* Test that getProduct() returns the correct product. */
        assertEquals(p1, sc.getProduct(1));
        assertEquals(p2, sc.getProduct(2));
        assertEquals(p3, sc.getProduct(3));

        /* Test that the product information returned is the same. */
        assertEquals(p1.getName(), sc.getProduct(1).getName());
        assertEquals(p1.getId(), sc.getProduct(1).getId());
        assertEquals(p1.getPrice(), sc.getProduct(1).getPrice());

        assertEquals(p2.getName(), sc.getProduct(2).getName());
        assertEquals(p2.getId(), sc.getProduct(2).getId());
        assertEquals(p2.getPrice(), sc.getProduct(2).getPrice());

        assertEquals(p3.getName(), sc.getProduct(3).getName());
        assertEquals(p3.getId(), sc.getProduct(3).getId());
        assertEquals(p3.getPrice(), sc.getProduct(3).getPrice());
    }
}