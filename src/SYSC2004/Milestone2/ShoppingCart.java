package SYSC2004.Milestone2;

/**
 * The ShoppingCart class will maintain the products the user chooses to purchase.
 *
 * @author Jeremy Trendoff - 101160306, Evan Smedley - 101148695
 *
 * @Primary_Class_Developer: Jeremy Trendoff - 101160306
 * @Contributor: Evan Smedley - 101148695
 *
 * @version 1.0
 * @since Feb 23, 2021
 */

public class ShoppingCart extends Inventory {

    /**
     *
     */
    public ShoppingCart() {
        super();
    }

    @Override
    public void addStock(Product product, int quantity) {
        super.addStock(product, quantity);
    }

    @Override
    public boolean removeStock(int id, int quantity) {
        return super.removeStock(id, quantity);
    }

    @Override
    public int getProductID(int index) {
        return super.getProductID(index);
    }

    @Override
    public String getProductName(int id) {
        return super.getProductName(id);
    }

    @Override
    public double getProductPrice(int id) {
        return super.getProductPrice(id);
    }

    @Override
    public int getStock(int id) {
        return super.getStock(id);
    }

    @Override
    public int getNumProducts() {
        return super.getNumProducts();
    }
}
