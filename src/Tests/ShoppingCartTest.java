package Tests;

import SYSC2004.Product;
import SYSC2004.ShoppingCart;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {
    HashMap<Product, Integer> test = new HashMap<>();
    ShoppingCart sc;

    @Test
    void haveProduct() {
        Product p1 = new Product("1", 1, 1.00);
        Product p2 = new Product("2", 2, 2.00);

        test.put(p1, 1);
        test.put(p2, 1);

        sc = new ShoppingCart(test);

        assertEquals(p1, sc.haveProduct(1));
        assertEquals(p2, sc.haveProduct(2));
        assertNull(sc.haveProduct(5));
    }

    @Test
    void addToCart() {
        Product p1 = new Product("1", 1, 1.00);
        Product p2 = new Product("2", 2, 2.00);

        test.put(p1, 1);
        test.put(p2, 1);

        sc = new ShoppingCart(test);

        sc.addToCart(p1, 10);
        sc.addToCart(p2, 12);
        sc.addToCart(new Product("3", 3, 3.00), 5);

        assertEquals(11, sc.getShoppingCart().get(sc.haveProduct(1)));
        assertEquals(13, sc.getShoppingCart().get(sc.haveProduct(2)));
        assertEquals(5, sc.getShoppingCart().get(sc.haveProduct(3)));
        assertTrue(sc.getShoppingCart().containsKey(sc.haveProduct(3)));
    }

    @Test
    void removeFromCart() {
        Product p1 = new Product("1", 1, 1.00);
        Product p2 = new Product("2", 2, 2.00);

        test.put(p1, 10);
        test.put(p2, 10);

        sc = new ShoppingCart(test);

        boolean test1 = sc.removeFromCart(1, 5);
        assertTrue(test1);
        assertEquals(5, sc.getShoppingCart().get(sc.haveProduct(1)));

        boolean test2 = sc.removeFromCart(1, 5);
        assertTrue(test2);
        assertNull(sc.haveProduct(1));

        boolean test3 = sc.removeFromCart(2, 15);
        assertFalse(test3);
        assertEquals(10, sc.getShoppingCart().get(sc.haveProduct(2)));

        boolean test4 = sc.removeFromCart(5, 10);
        assertFalse(test4);
        assertNull(sc.haveProduct(5));
    }
}