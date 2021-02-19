package SYSC2004.Milestone2;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Authors: Evan Smedley - 101148695, Jeremy Trendoff - 101160306
 */
public class StoreView {

    /**
     *
     */
    private final StoreManager manager;

    /**
     *
     */
    private final int id;

    /**
     *
     * @param manager
     * @param id
     */
    private StoreView(StoreManager manager, int id) {
        this.manager = manager;
        this.id = id;
    }

    /**
     *
     * @return
     */
    private boolean displayUI() {
        System.out.println("\nType 'help' for a list of commands.");
        System.out.println("Enter a command...");

        Scanner in = new Scanner(System.in);
        String command = in.next();
        command = command.toLowerCase().trim();

        if (command.equals("help")) {
            System.out.println("'browse'              -> View all available products");
            System.out.println("'addtocart'           -> Add a product to your cart");
            System.out.println("'removefromcart'      -> Remove a product from your cart");
            System.out.println("'cart'                -> Review the contents of your cart");
            System.out.println("'checkout'            -> Purchase all items in your shopping cart and exit");
            System.out.println("'quit'                -> Return all items in your shopping cart and exit");

            // The following commands are only available to the administrator (StoreView instance with id = 0).
            // They are for adding and removing from the store inventory.
            if (id == 0) {
                System.out.println("'addtoinventory'      -> Add a product to the inventory");
                System.out.println("'removefrominventory' -> Remove a product from the inventory");
            }
            System.out.print("\n");
            return false;

        } else if (command.equals("browse")) {
            System.out.println("|--------------------The Course Store--------------------|");
            System.out.println("| BROWSE ------------------------------------------------|\n");

            System.out.println(String.format("%-12s %-15s %-15s", "Stock", "Product Name", "Unit Price"));

            int numProducts = manager.getNumProducts(-1);
            if (numProducts == 0) {
                System.out.println("\nNo products available");
            }
            for (int i = 0; i < numProducts; i++) {
                int productID = manager.getId(i, -1);
                System.out.println(String.format("%-12d %-15s %-15.2f", manager.getStock(productID, -1),
                        manager.getName(productID, -1), manager.getPrice(productID, -1)));
            }
            System.out.print("\n");
            return false;


        } else if (command.equals("addtocart")) {
            System.out.println("|--------------------The Course Store--------------------|");
            System.out.println("| ADD TO CART -------------------------------------------|\n");

            System.out.println(String.format("%-12s %-15s %-15s %-8s", "Stock", "Product Name", "Unit Price",
                    "Option"));

            int numProducts = manager.getNumProducts(-1);
            if (numProducts == 0) {
                System.out.println("\nNo products available");
            } else {
                for (int i = 0; i < numProducts; i++) {
                    int productID = manager.getId(i, -1);
                    System.out.println(String.format("%-12d %-15s %-15.2f %-8s", manager.getStock(productID, -1),
                            manager.getName(productID, -1), manager.getPrice(productID, -1), "(" + i + ")"));
                }
                System.out.print("\nProduct to Add to Cart >>> ");
                int productToRemove = in.nextInt();
                System.out.print("Quantity to Add to Cart >>> ");
                int quantityToRemove = in.nextInt();

                int productToRemoveId = manager.getId(productToRemove, -1);

                if (manager.getStock(productToRemoveId, -1) >= quantityToRemove) {
                    manager.add(new Product(manager.getName(productToRemoveId, -1), productToRemoveId,
                            manager.getPrice(productToRemoveId, -1)), quantityToRemove, id);
                    manager.remove(manager.getId(productToRemove, -1), quantityToRemove, -1);
                } else {
                    System.out.println("ERROR > INVALID PRODUCT OR PRODUCT QUANTITY TO REMOVE");
                }
            }
            return false;


        } else if (command.equals("removefromcart")) {
            System.out.println("|--------------------The Course Store--------------------|");
            System.out.println("| REMOVE FROM CART --------------------------------------|\n");

            System.out.println(String.format("%-12s %-15s %-15s %-8s", "Stock", "Product Name", "Unit Price",
                    "Option"));

            int numProducts = manager.getNumProducts(id);
            if (numProducts == 0) {
                System.out.println("\nYour cart is empty");
            } else {
                for (int i = 0; i < numProducts; i++) {
                    int productID = manager.getId(i, id);
                    System.out.println(String.format("%-12d %-15s %-15.2f %-8s", manager.getStock(productID, id),
                            manager.getName(productID, id), manager.getPrice(productID, id), "(" + i + ")"));
                }
                System.out.print("\nProduct to Remove from Cart >>> ");
                int productToRemove = in.nextInt();
                System.out.print("Quantity to Remove from Cart >>> ");
                int quantityToRemove = in.nextInt();

                int productToRemoveId = manager.getId(productToRemove, id);

                if (manager.getStock(productToRemoveId, id) >= quantityToRemove) {
                    manager.add(new Product(manager.getName(productToRemoveId, id), productToRemoveId,
                            manager.getPrice(productToRemoveId, id)), quantityToRemove, -1);
                    manager.remove(manager.getId(productToRemove, id), quantityToRemove, id);
                } else {
                    System.out.println("ERROR > INVALID PRODUCT OR PRODUCT QUANTITY TO REMOVE");
                }
            }

            return false;


        } else if (command.equals("cart")) {
            System.out.println("|--------------------The Course Store--------------------|");
            System.out.println("| REVIEW CART -------------------------------------------|\n");

            System.out.println(String.format("%-12s %-15s %-15s", "Stock", "Product Name", "Unit Price"));

            int numProducts = manager.getNumProducts(id);
            if (numProducts == 0) {
                System.out.println("\nYour cart is empty");
            }
            for (int i = 0; i < numProducts; i++) {
                int productID = manager.getId(i, id);
                System.out.println(String.format("%-12d %-15s %-15.2f", manager.getStock(productID, id),
                        manager.getName(productID, id), manager.getPrice(productID, id)));
            }
            System.out.print("\n");
            return false;


        } else if (command.equals("checkout")) {
            System.out.println("|--------------------The Course Store--------------------|");
            System.out.println("| CHECKOUT ----------------------------------------------|\n");

            double totalPrice = 0.0;
            int currentProductId;
            for (int i = 0; i < manager.getNumProducts(id); i++) {
                currentProductId = manager.getId(i, id);
                totalPrice += manager.getPrice(currentProductId, id) * manager.getStock(currentProductId, id);
            }

            System.out.println(String.format("Your Total Is: $%.2f", totalPrice));
            System.out.println("Thank you for shopping at The Course Store!\n");

            return true;


        } else if (command.equals("quit")) {
            return true;


        } else if ((id == 0) && (command.equals("addtoinventory"))) {
            System.out.println("|--------------------The Course Store--------------------|");
            System.out.println("| ADD TO INVENTORY --------------------------------------|\n");

            System.out.print("Product Name >>> ");
            String newProductName = in.next();
            System.out.print("Product ID >>> ");
            int newProductID = in.nextInt();
            System.out.print("Product Price >>> ");
            double newProductPrice = in.nextDouble();
            System.out.print("Quantity to Add >>> ");
            int quantity = in.nextInt();

            manager.add(new Product(newProductName, newProductID, newProductPrice), quantity, -1);

            return false;


        } else if ((id == 0) && (command.equals("removefrominventory"))) {
            System.out.println("|--------------------The Course Store--------------------|");
            System.out.println("| REMOVE FROM INVENTORY ---------------------------------|\n");

            System.out.println(String.format("%-12s %-15s %-15s %-8s", "Stock", "Product Name", "Unit Price",
                    "Option"));

            int numProducts = manager.getNumProducts(-1);
            if (numProducts == 0) {
                System.out.println("\nNo products available");
            } else {
                for (int i = 0; i < numProducts; i++) {
                    int productID = manager.getId(i, -1);
                    System.out.println(String.format("%-12d %-15s %-15.2f %-8s", manager.getStock(productID, -1),
                            manager.getName(productID, -1), manager.getPrice(productID, -1), "(" + i + ")"));
                }
                System.out.print("\nProduct to Remove >>> ");
                int productToRemove = in.nextInt();
                System.out.print("Quantity to Remove >>> ");
                int quantityToRemove = in.nextInt();

                if (!manager.remove(manager.getId(productToRemove, -1), quantityToRemove, -1)) {
                    System.out.println("ERROR > INVALID PRODUCT OR PRODUCT QUANTITY TO REMOVE");
                }
            }

            return false;


        } else {
            System.out.println("ERROR > INVALID COMMAND\nPLEASE TRY AGAIN");

            return false;
        }
    }


    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        StoreManager manager = new StoreManager();
        Scanner in = new Scanner(System.in);

        System.out.print("CHOOSE NUMBER OF USERS >>> ");
        int activeSV = in.nextInt();

        ArrayList<StoreView> users = new ArrayList<>();

        for (int i = 0; i < activeSV; i++) {
            users.add(new StoreView(manager, manager.assignNewCartID()));
        }

        String new_user;

        while (activeSV > 0) {
            System.out.print("NEW USER? (y/n) >>> ");
            new_user = in.next();
            if (new_user.equals("y") || new_user.equals("Y")) {
                users.add(new StoreView(manager, manager.assignNewCartID()));
                activeSV++;
            }
            System.out.print("CHOOSE YOUR STOREVIEW >>> ");
            int choice = in.nextInt();
            if (choice < users.size() && choice >= 0) {
                if (users.get(choice) != null) {
                    String chooseAnother = "";
                    while (!chooseAnother.equals("y") && !chooseAnother.equals("Y")) {
                        if (users.get(choice).displayUI()) {
                            users.set(choice, null);
                            activeSV--;
                            break;
                        }
                        System.out.print("GO TO ANOTHER STOREVIEW? (y/n) >>> ");
                        chooseAnother = in.next();
                    }
                } else {
                    System.out.println("MAIN > ERROR > BAD CHOICE\nTHAT STOREVIEW WAS DEACTIVATED");
                }
            } else {
                System.out.printf("MAIN > ERROR > BAD CHOICE\nPLEASE CHOOSE IN RANGE [%d, %d]\n\n", 0,
                        users.size() - 1);
            }
        }
        System.out.println("\nALL STOREVIEWS DEACTIVATED");
    }
}
