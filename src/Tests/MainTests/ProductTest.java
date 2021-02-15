package Tests.MainTests;

import SYSC2004.CurrentCode.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Authors: Jeremy Trendoff - 101160306, Evan Smedley - 101148695
 *
 * Date of Completion: Jan 27, 2021
 *
 * Class Description: A Junit 5 test class to test the Product class.
 */

public class ProductTest {
    Product p1 = new Product("1", 1, 1.00);
    Product p2 = new Product("2", 2, 2.00);
    Product p3 = new Product("3", 3, 3.00);

    @Test
    void getName() {
        assertEquals("1", p1.getName());
        assertEquals("2", p2.getName());
        assertEquals("3", p3.getName());
    }

    @Test
    void getId() {
        assertEquals(1, p1.getId());
        assertEquals(2, p2.getId());
        assertEquals(3, p3.getId());
    }

    @Test
    void getPrice() {
        assertEquals(1.00, p1.getPrice());
        assertEquals(2.00, p2.getPrice());
        assertEquals(3.00, p3.getPrice());
    }
}