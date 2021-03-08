package SYSC2004.Milestone2;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The StoreView class will display the User Interface of the store.
 *
 * @author Evan Smedley - 101148695, Jeremy Trendoff - 101160306
 * @version 1.0
 * @since Feb 23, 2021
 */

public class StoreView {

    /**
     * The store's StoreManager.
     */
    private StoreManager manager;

    /**
     * The id of the user's StoreView instance.
     */
    private final int id;

    /**
     * Constructor for StoreView. Initialize the StoreView with specified values.
     *
     * @param manager A StoreManager object that represents the StoreViews StoreManager.
     * @param id    An int value representing the id of the user's StoreView instance.
     */
    private StoreView(StoreManager manager, int id) {
        this.manager = manager;
        this.id = id;
    }

    /**
     * Displays the User Interface.
     *
     * @return  True if the user decides to quit or checkout. False otherwise.
     */
    private boolean displayUI() {
        if (this.id == 0) {
            System.out.println("\n|--------------------Administrator--------------------|");
        }

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

            // The following commands are only available to the administrator (StoreView instance with id = 0).
            // They are for adding and removing from the store inventory.
            if (this.id == 0) {
                System.out.println("'addtoinventory'      -> Add a product to the inventory");
                System.out.println("'removefrominventory' -> Remove a product from the inventory");
                System.out.println("'closestore'          -> Close the store");
            } else {
                System.out.println("'quit'                -> Return all items in your shopping cart and exit");
            }
            System.out.print("\n");
            return false;

        } else if (command.equals("browse")) {
            System.out.println("|--------------------The Course Store--------------------|");
            System.out.println("| BROWSE ------------------------------------------------|\n");

            System.out.println(String.format("%-12s %-15s %-15s", "Stock", "Product Name", "Unit Price"));

            int numProducts = this.manager.getNumProducts(-1);
            if (numProducts == 0) {
                System.out.println("\nNo products available");
            }
            for (int i = 0; i < numProducts; i++) {
                int productID = this.manager.getID(i, -1);
                System.out.println(String.format("%-12d %-15s %-15.2f", this.manager.getStock(productID, -1),
                        this.manager.getName(productID, -1), this.manager.getPrice(productID, -1)));
            }
            System.out.print("\n");
            return false;


        } else if (command.equals("addtocart")) {
            System.out.println("|--------------------The Course Store--------------------|");
            System.out.println("| ADD TO CART -------------------------------------------|\n");

            System.out.println(String.format("%-12s %-15s %-15s %-8s", "Stock", "Product Name", "Unit Price",
                    "Option"));

            int numProducts = this.manager.getNumProducts(-1);
            if (numProducts == 0) {
                System.out.println("\nNo products available");
            } else {
                for (int i = 0; i < numProducts; i++) {
                    int productID = this.manager.getID(i, -1);
                    System.out.println(String.format("%-12d %-15s %-15.2f %-8s", this.manager.getStock(productID,
                            -1), this.manager.getName(productID, -1), this.manager.getPrice(productID,
                            -1), "(" + i + ")"));
                }

                int productToRemove = -1;
                while (productToRemove == -1) {
                    try {
                        System.out.print("\nProduct to Add to Cart >>> ");
                        productToRemove = in.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("ERROR > INVALID PRODUCT");
                        in.next();
                    }
                }


                int quantityToRemove = -1;
                while (quantityToRemove == -1) {
                    try {
                        System.out.print("\nQuantity to Add to Cart >>> ");
                        quantityToRemove = in.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("ERROR > INVALID PRODUCT QUANTITY TO REMOVE");
                        in.next();
                    }
                }

                int productToRemoveId = this.manager.getID(productToRemove, -1);

                if (this.manager.getStock(productToRemoveId, -1) >= quantityToRemove) {
                    this.manager.add(new Product(this.manager.getName(productToRemoveId, -1), productToRemoveId,
                            this.manager.getPrice(productToRemoveId, -1)), quantityToRemove, this.id);
                    this.manager.remove(this.manager.getID(productToRemove, -1), quantityToRemove, -1);
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

            int numProducts = this.manager.getNumProducts(this.id);
            if (numProducts == 0) {
                System.out.println("\nYour cart is empty");
            } else {
                for (int i = 0; i < numProducts; i++) {
                    int productID = this.manager.getID(i, this.id);
                    System.out.println(String.format("%-12d %-15s %-15.2f %-8s", this.manager.getStock(productID,
                            this.id), this.manager.getName(productID, this.id), this.manager.getPrice(productID,
                            this.id), "(" + i + ")"));
                }

                int productToRemove = -1;
                while (productToRemove == -1) {
                    try {
                        System.out.print("\nProduct to Remove from Cart >>> ");
                        productToRemove = in.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("ERROR > INPUT MISMATCH EXCEPTION > INVALID PRODUCT");
                        in.next();
                    }
                }

                int quantityToRemove = -1;
                while (quantityToRemove == -1) {
                    try {
                        System.out.print("Quantity to Remove from Cart >>> ");
                        quantityToRemove = in.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("ERROR > INPUT MISMATCH EXCEPTION > INVALID QUANTITY TO REMOVE");
                        in.next();
                    }
                }

                int productToRemoveId = this.manager.getID(productToRemove, this.id);

                if (this.manager.getStock(productToRemoveId, this.id) >= quantityToRemove) {
                    this.manager.add(new Product(this.manager.getName(productToRemoveId, this.id), productToRemoveId,
                            this.manager.getPrice(productToRemoveId, this.id)), quantityToRemove, -1);
                    this.manager.remove(this.manager.getID(productToRemove, this.id), quantityToRemove, this.id);
                } else {
                    System.out.println("ERROR > INVALID PRODUCT OR PRODUCT QUANTITY TO REMOVE");
                }
            }

            return false;


        } else if (command.equals("cart")) {
            System.out.println("|--------------------The Course Store--------------------|");
            System.out.println("| REVIEW CART -------------------------------------------|\n");

            System.out.println(String.format("%-12s %-15s %-15s", "Stock", "Product Name", "Unit Price"));

            int numProducts = this.manager.getNumProducts(this.id);
            if (numProducts == 0) {
                System.out.println("\nYour cart is empty");
            }
            for (int i = 0; i < numProducts; i++) {
                int productID = this.manager.getID(i, this.id);
                System.out.println(String.format("%-12d %-15s %-15.2f", this.manager.getStock(productID, this.id),
                        this.manager.getName(productID, this.id), this.manager.getPrice(productID, this.id)));
            }
            System.out.print("\n");
            return false;


        } else if (command.equals("checkout")) {
            System.out.println("|--------------------The Course Store--------------------|");
            System.out.println("| CHECKOUT ----------------------------------------------|\n");

            double totalPrice = 0.0;
            int currentProductId;
            for (int i = 0; i < this.manager.getNumProducts(this.id); i++) {
                currentProductId = this.manager.getID(i, this.id);
                totalPrice += this.manager.getPrice(currentProductId, this.id) *
                        this.manager.getStock(currentProductId, this.id);
            }

            System.out.println(String.format("Your Total Is: $%.2f", totalPrice));
            System.out.println("Thank you for shopping at The Course Store!\n");

            return true;


        } else if ((command.equals("quit")) && (id != 0)) {
            int productID;
            for (int i = 0; i < this.manager.getNumProducts(this.id); i++) {
                productID = this.manager.getID(i, id);
                this.manager.add(new Product(this.manager.getName(productID, this.id), productID,
                        this.manager.getPrice(productID, this.id)), this.manager.getStock(productID,
                        this.id), -1);
                this.manager.remove(productID, this.manager.getStock(productID, this.id), this.id);
            }
            return true;


        } else if ((id == 0) && (command.equals("addtoinventory"))) {
            System.out.println("|--------------------The Course Store--------------------|");
            System.out.println("| ADD TO INVENTORY --------------------------------------|\n");

            String newProductName = null;
            while (newProductName == null) {
                try {
                    System.out.print("Product Name >>> ");
                    newProductName = in.next();
                } catch (InputMismatchException e) {
                    System.out.println("ERROR > INPUT MISMATCH EXCEPTION > INVALID PRODUCT NAME");
                    in.next();
                }
            }

            int newProductID = -1;
            while (newProductID == -1) {
                try {
                    System.out.print("Product ID >>> ");
                    newProductID = in.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("ERROR > INPUT MISMATCH EXCEPTION > INVALID PRODUCT ID");
                    in.next();
                }
            }

            double newProductPrice = -1.00;
            while (newProductPrice == -1.00) {
                try {
                    System.out.print("Product Price >>> ");
                    newProductPrice = in.nextDouble();
                } catch (InputMismatchException e) {
                    System.out.println("ERROR > INPUT MISMATCH EXCEPTION > INVALID PRODUCT PRICE");
                    in.next();
                }
            }

            int quantity = -1;
            while (quantity == -1) {
                try {
                    System.out.print("Quantity to Add >>> ");
                    quantity = in.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("ERROR > INPUT MISMATCH EXCEPTION > INVALID QUANTITY TO ADD");
                    in.next();
                }
            }

            this.manager.add(new Product(newProductName, newProductID, newProductPrice), quantity, -1);

            return false;


        } else if ((id == 0) && (command.equals("removefrominventory"))) {
            System.out.println("|--------------------The Course Store--------------------|");
            System.out.println("| REMOVE FROM INVENTORY ---------------------------------|\n");

            System.out.println(String.format("%-12s %-15s %-15s %-8s", "Stock", "Product Name", "Unit Price",
                    "Option"));

            int numProducts = this.manager.getNumProducts(-1);
            if (numProducts == 0) {
                System.out.println("\nNo products available");
            } else {
                for (int i = 0; i < numProducts; i++) {
                    int productID = this.manager.getID(i, -1);
                    System.out.println(String.format("%-12d %-15s %-15.2f %-8s", this.manager.getStock(productID,
                            -1), this.manager.getName(productID, -1), this.manager.getPrice(productID,
                            -1), "(" + i + ")"));
                }

                int productToRemove = -1;
                while (productToRemove == -1) {
                    try {
                        System.out.print("\nProduct to Remove >>> ");
                        productToRemove = in.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("ERROR > INPUT MISMATCH EXCEPTION > INVALID PRODUCT");
                        in.next();
                    }
                }

                int quantityToRemove = -1;
                while (quantityToRemove == -1) {
                    try {
                        System.out.print("Quantity to Remove >>> ");
                        quantityToRemove = in.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("ERROR > INPUT MISMATCH EXCEPTION > INVALID QUANTITY TO REMOVE");
                        in.next();
                    }
                }

                if (!this.manager.remove(this.manager.getID(productToRemove, -1), quantityToRemove,
                        -1)) {
                    System.out.println("ERROR > INVALID PRODUCT OR PRODUCT QUANTITY TO REMOVE");
                }
            }

            return false;


        } else if ((this.id == 0) && command.equals("closestore")) {
            System.out.println("THE STORE IS NOW CLOSED!");
            System.exit(0);
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

        boolean isFirstRun = true;

        int activeSV = -1;
        while (activeSV == -1) {
            try {
                System.out.print("CHOOSE NUMBER OF USERS >>> ");
                activeSV = in.nextInt() + 1;
            } catch (InputMismatchException e) {
                System.out.println("ERROR > INPUT MISMATCH EXCEPTION > INVALID NUMBER OF USERS");
                in.next();
            }
        }

        ArrayList<StoreView> users = new ArrayList<>();

        for (int i = 0; i < activeSV; i++) {
            users.add(new StoreView(manager, manager.assignNewCartID()));
        }

        String new_user;

        int choice = 0;

        while (activeSV > 0) {
            if (!isFirstRun) {
                System.out.print("NEW USER? (y/n) >>> ");
                new_user = in.next();
                if (new_user.equals("y") || new_user.equals("Y")) {
                    users.add(new StoreView(manager, manager.assignNewCartID()));
                    activeSV++;
                } else {
                    System.out.println("NO NEW USER ADDED");
                }
            }
            if (users.size() > 1) {
                System.out.print("CHOOSE YOUR STOREVIEW ");

                System.out.print("(");
                System.out.print("Administrator: [0] || Active Users: ");

                for (StoreView sv : users) {
                    if (sv != null && users.indexOf(sv) != 0) {
                        System.out.print("[" + users.indexOf(sv) + "]");
                    }
                }
                System.out.print(")");
                System.out.print(" >>> ");

                choice = -1;
                while (choice == -1) {
                    try {
                        choice = in.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("ERROR > INPUT MISMATCH EXCEPTION > INVALID STOREVIEW");
                        in.next();
                        System.out.print("\nTRY AGAIN >>> ");
                    }
                }
            }
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

            isFirstRun = false;
        }
        System.out.println("\nALL STOREVIEWS DEACTIVATED");
    }
}
