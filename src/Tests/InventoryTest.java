package Tests;

import SYSC2004.Inventory;
import SYSC2004.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Integer> amount = new ArrayList<>();

    Inventory inventory;

    @Test
    void getStock() {
        products.add(new Product("P1", 1, 1.00));
        amount.add(15);



        inventory = new Inventory(products, amount);

        assertEquals(15, inventory.getStock(1));
        assertEquals(-1, inventory.getStock(100));
    }

    @Test
    void addStock() {
    }

    @Test
    void removeStock() {
    }

    @Test
    void getProductName() {
    }

    @Test
    void getProductPrice() {
    }
}