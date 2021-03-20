package SYSC2004.Milestone3.Tests;

import SYSC2004.Milestone3.Store.Inventory;
import SYSC2004.Milestone3.Store.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    private static Inventory inventory;

    @BeforeAll
    public static void init() {
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Integer> stock = new ArrayList<>();

        products.add(new Product("test1", 1, 1.00));
        stock.add(10);

        products.add(new Product("test2", 2, 2.00));
        stock.add(20);

        products.add(new Product("test3", 3, 3.00));
        stock.add(30);

        products.add(new Product("test4", 4, 4.00));
        stock.add(40);

        inventory = new Inventory(products, stock);
    }

    @Test
    public void testAddStock() {
        inventory.addStock(new Product("test1", 1, 1.00), 5);
        assertEquals(4, inventory.getNumProducts(),
                "addStock did not add to the existing stock of a product, it added a whole new product");
        assertEquals(15, inventory.getStock(1),
                "addStock did not add the correct quantity of stock of the product");
        inventory.removeStock(1, 5); // Undo what was done in this test so it doesn't affect the other tests

        inventory.addStock(new Product("test5", 5, 5.00), 5);
        assertEquals(5, inventory.getNumProducts(),"addStock did not create a new product");
        assertEquals(5, inventory.getStock(5),
                "addStock did not add the correct quantity of stock of the product");
        inventory.removeStock(5, 5); // Undo what was done in this test so it doesn't affect the other tests
    }

    @Test
    public void testRemoveStock() {
        boolean success = inventory.removeStock(5, 5);
        assertEquals(4, inventory.getNumProducts(), "removeStock was called with a product that is " +
                "not in inventory, and did not handle it properly");
        assertFalse(success, "removeStock did not return the proper value for an unsuccessful remove request");

        success = inventory.removeStock(1, 11);
        assertEquals(10, inventory.getStock(1),"removeStock removed stock even though there was " +
                "not enough to remove");
        assertFalse(success, "removeStock did not return the proper value for an unsuccessful remove request");

        success = inventory.removeStock(1,5);
        assertEquals(5, inventory.getStock(1),
                "removeStock did not remove the correct amount of stock");
        assertTrue(success,"removeStock did not return the proper value for a successful remove request");
        inventory.addStock(new Product("test1", 1, 1.00), 5);

        success = inventory.removeStock(1, 10);
        assertEquals(3, inventory.getNumProducts(),
                "removeStock did not remove a product entirely when it had 0 stock");
        assertTrue(success,"removeStock did not return the proper value for a successful remove request");
        init(); // This is necessary, this test changesthe order of the product list which messes up testGetProductID
    }

    @Test
    public void testGetProductID() {
        assertEquals(2, inventory.getProductID(1),"getProductID does not get the correct id");

        assertEquals(-1, inventory.getProductID(8),
                "getProductID does not handle an out of range index parameter correctly");

        assertEquals(-1, inventory.getProductID(-5),
                "getProductID does not handle an invalid index parameter (negative) correctly");
    }

    @Test
    public void testGetProductName() {
        assertEquals("test2", inventory.getProductName(2),
                "getProductName does not get the correct name");

        assertNull(inventory.getProductName(8), "getProductName does not handle an id parameter that " +
                "does not exist in product list correctly");
    }

    @Test
    public void testGetProductPrice() {
        assertEquals(2.0, inventory.getProductPrice(2),
                "getProductPrice does not get the correct price");

        assertEquals(-1.0, inventory.getProductPrice(8), "getProductPrice does not handle an id " +
                "parameter that does not exist in product list correctly");
    }

    @Test
    public void testGetStock() {
        assertEquals(20, inventory.getStock(2),
                "getStock does not get the correct stock");

        assertEquals(-1, inventory.getStock(8), "getStock does not handle an id " +
                "parameter that does not exist in product list correctly");
    }

    @Test
    public void testGetNumProducts() {
        assertEquals(4, inventory.getNumProducts(),
                "getNumProducts does not get the correct number of products");
    }
}