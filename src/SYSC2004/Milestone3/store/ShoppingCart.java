package SYSC2004.Milestone3.store;

/**
 * The ShoppingCart class will maintain the products the user chooses to purchase.
 *
 * @author Jeremy Trendoff - 101160306, Evan Smedley - 101148695
 * @version 1.0
 * @since Feb 23, 2021
 */
public class ShoppingCart extends Inventory {

    /**
     * The ID of the shopping cart.
     */
    private final int cartID;

    /**
     * Default constructor for Shopping Cart
     */
    public ShoppingCart(int cartID) {
        super();
        this.cartID = cartID;
    }

    /**
     * Get the cart's ID.
     *
     * @return int, the cart id.
     */
    public int getCartID() {
        return cartID;
    }
}