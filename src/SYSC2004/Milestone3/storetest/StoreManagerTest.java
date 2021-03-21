package SYSC2004.Milestone3.storetest;

import SYSC2004.Milestone3.store.Product;
import SYSC2004.Milestone3.store.StoreManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * J-unit test for StoreManager
 * @author Evan Smedley - 101148695
 */
class StoreManagerTest {

    /**
     * StoreManager attribute that all of the tests use
     */
    private static StoreManager sm;

    /**
     * Initializes a StoreManager object for testing.
     */
    @BeforeAll
    public static void init() {
        sm = new StoreManager();
        sm.assignNewCartID();
        sm.assignNewCartID();
        sm.add(new Product("TestProd", 1, 1.00), 5, 0);
    }

    /**
     * Tests StoreManager's constructor.
     */
    @Test
    public void testConstructor() {
        assertEquals(7, sm.getNumProducts(-1),
                "The constructor does not add the correct number of products to the inventory, " +
                        "constructor failed");

        assertEquals("ELEC4705", sm.getName(106, -1),
                "The constructor does not add the correct name for a product, constructor failed");

        assertEquals(101, sm.getID(0, -1),
                "The constructor does not add the correct id for a product, constructor failed");

        assertEquals(45.00, sm.getPrice(103, -1),
                "The constructor does not add the correct price for a product, constructor failed");

        assertEquals(322, sm.getStock(107, -1),
                "The constructor does not add the correct amount of stock for a product, constructor failed");
    }

    /**
     * Tests StoreManager's assignNewCartID() method.
     */
    @Test
    public void testAssignNewCartID() {
        int id = sm.assignNewCartID();
        assertEquals(2, id,
                "assignNewCartID is not generating the correct first cart id, assignNewCartID failed");

        id = sm.assignNewCartID();
        assertEquals(3, id, "assignNewCartID is not generating the correct second cart id, " +
                "assignNewCartID failed");

        id = sm.assignNewCartID();
        assertEquals(4, id, "assignNewCartID is not generating the correct third cart id, " +
                "assignNewCartID failed");
    }

    /**
     * Tests StoreManager's getID() method.
     */
    @Test
    public void testGetID() {
        int id = sm.getID(5, -1);
        assertEquals(106, id, "getID is not getting the correct product id from inventory, " +
                "getID failed");

        id = sm.getID(10, -1);
        assertEquals(-1, id, "getID is not handling the fact that the index provided is too large, " +
                "getID failed");

        id = sm.getID(0, 1);
        assertEquals(-1, id, "getID is not handling the empty shopping cart correctly, " +
                "getID failed");

        id = sm.getID(-5, 1);
        assertEquals(-1, id, "getID is not handling a negative index into a shopping cart correctly,"+
                " getID failed");

        id = sm.getID(0, 0);
        assertEquals(1, id, "getID is not accessing shopping carts correctly, getID failed");
    }

    /**
     * Tests StoreManager's getName() method.
     */
    @Test
    public void testGetName() {
        String name = sm.getName(2, 0);
        assertNull(name, "getName is not handling that there is no item with that id in the cart properly, " +
                "getName failed");

        name = sm.getName(9, -1);
        assertNull(name, "getName is not handling that there is no item with that id in the inventory, " +
                "getName failed");

        name = sm.getName(1,0);
        assertEquals("TestProd", name, "getName is not getting the correct name from the cart, " +
                "getName failed");

        name = sm.getName(101, -1);
        assertEquals("SYSC2004", name, "getName is not getting the correct name from the inventory, "+
                "getName failed");

        name = sm.getName(101, -2);
        assertNull(name, "getName does not handle when there is an invalid location, getName failed");

        name = sm.getName(1, 1);
        assertNull(name, "getName does not handle accessing an empty cart correctly, getName failed");
    }

    /**
     * Tests StoreManager's getPrice() method.
     */
    @Test
    public void testGetPrice() {
        double price = sm.getPrice(2, 0);
        assertEquals(-1.0, price, 0.01,
                "getPrice is not handling that there is no item with that id in the cart properly, " +
                        "getPrice failed");

        price = sm.getPrice(9, -1);
        assertEquals(-1.0, price, 0.01,
                "getPrice is not handling that there is no item with that id in the inventory, " +
                        "getPrice failed");

        price = sm.getPrice(1, 0);
        assertEquals(1.00, price, 0.01,
                "getPrice is not getting the correct name from the cart, getPrice failed");

        price = sm.getPrice(101, -1);
        assertEquals(100.00, price, 0.01,
                "getPrice is not getting the correct name from the inventory, getPrice failed");

        price = sm.getPrice(101, -2);
        assertEquals(-1.0, price, 0.01,
                "getPrice does not handle when there is an invalid location, getPrice failed");

        price = sm.getPrice(1, 1);
        assertEquals(-1.0, price, 0.01,
                "getPrice does not handle accessing an empty cart correctly, getPrice failed");
    }

    /**
     * Tests StoreManager's getStock() method.
     */
    @Test
    public void testGetStock() {
        int stock = sm.getStock(2, 0);
        assertEquals(-1, stock,
                "getStock is not handling that there is no item with that id in the cart properly, " +
                        "getStock failed");

        stock = sm.getStock(9, -1);
        assertEquals(-1, stock,
                "getStock is not handling that there is no item with that id in the inventory, " +
                        "getStock failed");

        stock = sm.getStock(1, 0);
        assertEquals(5, stock, "getStock is not getting the correct name from the cart, " +
                "getStock failed");

        stock = sm.getStock(101, -1);
        assertEquals(76, stock, "getStock is not getting the correct name from the inventory, " +
                "getStock failed");

        stock = sm.getStock(101,  -2);
        assertEquals(-1, stock, "getStock does not handle when there is an invalid location, " +
                "getStock failed");

        stock = sm.getStock(1, 1);
        assertEquals(-1, stock, "getStock does not handle accessing an empty cart correctly, " +
                "getStock failed");
    }

    /**
     * Tests StoreManager's getNumProducts() method.
     */
    @Test
    public void testGetNumProducts() {
        int numProducts = sm.getNumProducts(7);
        assertEquals(-1, numProducts,
                "getNumProducts is not handling when an invalid location is given as a parameter, " +
                        "getNumProducts failed");

        numProducts = sm.getNumProducts(-1);
        assertEquals(7, numProducts,
                "getNumProducts is not getting the correct number of products from the inventory, " +
                        "getNumProducts failed");

        numProducts = sm.getNumProducts(0);
        assertEquals(1, numProducts,
                "getNumProducts is not getting the correct number of products from the shopping cart, " +
                        "getNumProducts failed");

        numProducts = sm.getNumProducts(1);
        assertEquals(0, numProducts,
                "getNumProducts is not getting the correct number of products from the empty shopping cart, " +
                        "getNumProducts failed");
    }

    /**
     * Tests StoreManager's add() method.
     */
    @Test
    public void testAdd() {
        sm.add(new Product("test", 25, 15.00), 50, -1);
        assertEquals("test", sm.getName(25, -1),
                "add does not add the name to the inventory correctly, add failed");
        assertEquals(15.00, sm.getPrice(25 ,-1),
                "add does not add price to the inventory correctly, add failed");
        assertEquals(25, sm.getID(7, -1),
                "add does not add the id to the inventory correctly, add failed");
        assertEquals(50, sm.getStock(25, -1),
                "add does not add the correct amount of stock to inventory, add failed");
        sm.remove(25, 50, -1);  // Undo what was done in this test so it does not affect other tests

        sm.add(new Product("test", 25, 15.00), 50, 1);
        assertEquals("test", sm.getName(25, 1),
                "add does not add the name to the shopping cart correctly, add failed");
        assertEquals(15.00, sm.getPrice(25 ,1),
                "add does not add price to the shopping cart correctly, add failed");
        assertEquals(25, sm.getID(0, 1),
                "add does not add the id to the shopping cart correctly, add failed");
        assertEquals(50, sm.getStock(25, 1),
                "add does not add the correct amount of stock to the shopping cart, add failed");
        sm.remove(25, 50, 1);  // Undo what was done in this test so it does not affect other tests

        sm.add(new Product("SYSC2004", 101, 100.00), 2, -1);
        assertEquals(78, sm.getStock(101, -1),
                "add does add to the existing stock of a product correctly, add failed");
        sm.remove(101, 2, -1);
    }

    /**
     * Tests StoreManager's remove() method.
     */
    @Test
    public void testRemove() {
        boolean success = sm.remove(103, 2, -1);
        assertTrue(success,
                "remove does not remove any of the product from the inventory successfully, remove failed");
        assertEquals(30, sm.getStock(103, -1),
                "remove does not remove the correct amount of stock of the product, remove failed");

        int numProductsBefore = sm.getNumProducts(0);
        success = sm.remove(1, 5, 0);
        int numProductsAfter = sm.getNumProducts(0);
        assertTrue(success,
                "remove does not remove any of the product from the shopping cart successfully, remove failed");
        assertEquals(1, numProductsBefore - numProductsAfter,
                "remove does not remove the product from the cart when it has 0 stock, remove failed");

        success = sm.remove(60, 10, -1);
        assertFalse(success,
                "remove says that it successfully removed a number of stock of a product from inventory " +
                        "but the product did not exist in inventory, remove failed");

        success = sm.remove(101, 400, -1);
        assertFalse(success, "remove says that it successfully removed a number of stock of a product " +
                "from inventory but there wasn't enough stock of the product to remove, remove failed");

        success = sm.remove(101,2,-5);
        assertFalse(success, "remove does not handle the case where an invalid location is provided, " +
                "remove failed");
    }
}