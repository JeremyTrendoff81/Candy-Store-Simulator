package Tests.OldTests;

import SYSC2004.Milestone1_HashMap.Product;
import SYSC2004.Milestone1_Draft.StoreManagerDRAFT;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StoreManagerDRAFTTest {

    @org.junit.jupiter.api.Test
    void checkStock() {
        ArrayList<Product> productList = new ArrayList<>();
        ArrayList<Integer> stockList = new ArrayList<>();

        productList.add(new Product("hi", 1, 40.00));
        stockList.add(6);

        productList.add(new Product("hello", 2, 45.00));
        stockList.add(4);

        StoreManagerDRAFT manager = new StoreManagerDRAFT(productList, stockList);

        assertEquals(6, manager.checkStock(new Product("hi", 1, 40.00)));
        assertEquals(-1, manager.checkStock(new Product("askdf",3,50.00)));
        assertEquals(4, manager.checkStock(new Product("hello",2, 45.00)));
    }

    @org.junit.jupiter.api.Test
    void processTransaction() {
        ArrayList<Product> productList = new ArrayList<>();
        ArrayList<Integer> stockList = new ArrayList<>();

        productList.add(new Product("hi",1, 40.00));
        stockList.add(2);

        productList.add(new Product("hello", 2, 45.00));
        stockList.add(4);

        StoreManagerDRAFT manager = new StoreManagerDRAFT(productList, stockList);

        int[][] order1 = {{1, 2},{2, 5}};
        int[][] order2 = {{2, 1},{2,1}};
        int[][] order3 = {{2, 3},{1, 1}};

        boolean print = false;

        assertEquals(manager.processTransaction(order1), -1.0);
        if (print) {
            System.out.println(manager.checkStock(new Product("hi", 1, 40.00)));
            System.out.println(manager.checkStock(new Product("hello", 2, 45.00)));
        }

        stockList.set(0, 2);
        stockList.set(1, 4);

        assertEquals(manager.processTransaction(order2), 90.00);
        if (print) {
            System.out.println(manager.checkStock(new Product("hi", 1, 40.00)));
            System.out.println(manager.checkStock(new Product("hello", 2, 45.00)));
        }

        stockList.set(0, 2);
        stockList.set(1, 4);

        assertEquals(manager.processTransaction(order3), 175.00);
        if (print) {
            System.out.println(manager.checkStock(new Product("hi", 1, 40.00)));
            System.out.println(manager.checkStock(new Product("hello", 2, 45.00)));
        }
    }
}