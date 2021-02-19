package Tests.Milestone1Tests;

/*
class StoreManagerTest {

    @Test
    void checkStock() {
        HashMap<Product, Integer> test = new HashMap<>();
        test.put(new Product("P1", 1, 1.00), 10);

        StoreManager smh = new StoreManager(test);

        assertEquals(10, smh.checkStock(new Product("P1", 1, 1.00)));
        assertEquals(-1, smh.checkStock(new Product("", 2, 2.00)));
    }

    @Test
    void processTransaction() {
        HashMap<Product, Integer> test = new HashMap<>();
        Product p1 = new Product("hi", 1, 40.00);
        Product p2 = new Product("hello", 2, 45.00);
        test.put(p1, 2);
        test.put(p2, 4);

        StoreManager manager = new StoreManager(test);

        int[][] order1 = {{1, 2},{2, 5}};
        int[][] order2 = {{2, 1},{2,1}};
        int[][] order3 = {{2, 3},{1, 1}};

        boolean print = false;

        assertEquals(manager.processTransaction(order1), -1.0);
        if (print) {
            System.out.println(manager.checkStock(new Product("hi", 1, 40.00)));
            System.out.println(manager.checkStock(new Product("hello", 2, 45.00)));
        }

        test.replace(p1, 2);
        test.replace(p2, 4);

        assertEquals(manager.processTransaction(order2), 90.00);
        if (print) {
            System.out.println(manager.checkStock(new Product("hi", 1, 40.00)));
            System.out.println(manager.checkStock(new Product("hello", 2, 45.00)));
        }

        test.replace(p1, 2);
        test.replace(p2, 4);

        assertEquals(manager.processTransaction(order3), 175.00);
        if (print) {
            System.out.println(manager.checkStock(new Product("hi", 1, 40.00)));
            System.out.println(manager.checkStock(new Product("hello", 2, 45.00)));
        }
    }
}
 */