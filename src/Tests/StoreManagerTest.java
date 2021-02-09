package Tests;

import SYSC2004.Product;
import SYSC2004.StoreManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StoreManagerTest {

    @org.junit.jupiter.api.Test
    void checkStock() {
        ArrayList<Product> productList = new ArrayList<>();
        ArrayList<Integer> stockList = new ArrayList<>();

        productList.add(new Product("hi", 1, 40.00));
        stockList.add(6);

        productList.add(new Product("hello", 2, 45.00));
        stockList.add(4);

        StoreManager manager = new StoreManager(productList, stockList);

        assertEquals(6, manager.checkStock(new Product("hi", 1, 40.00)));
        assertEquals(-1, manager.checkStock(new Product("askdf",3,50.00)));
        assertEquals(4, manager.checkStock(new Product("hello",2, 45.00)));
    }

    @org.junit.jupiter.api.Test
    void processTransaction() {

    }
}