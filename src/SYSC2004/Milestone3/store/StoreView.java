package SYSC2004.Milestone3.store;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.*;


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
     * The JPanel that will hold the product's ImagePanel in inventoryProducts.
     */
    private JPanel inventory;

    /**
     * The JPanel that will hold the product's ImagePanel in cartProducts.
     */
    private JPanel cart;

    /**
     * An ArrayList of ImagePanel's to hold the information of each product in the inventory.
     */
    private ArrayList<ImagePanel> inventoryProducts;

    /**
     * An ArrayList of ImagePanel's to hold the information of each product in the cart.
     */
    private ArrayList<ImagePanel> cartProducts;

    /**
     * A JLabel representing the total price of the products in the cart.
     */
    private JLabel total;

    /**
     *  A constant boolean that determines which UI to use. GUI = true, use Graphical User Interface. GUI = false,
     *  use Console User Interface.
     */
    private static final boolean GUI = true;

    /**
     * Constructor for StoreView. Initialize the StoreView with specified values.
     *
     * @param manager A StoreManager object that represents the StoreViews StoreManager.
     * @param id    An int value representing the id of the user's StoreView instance.
     */
    private StoreView(StoreManager manager, int id) {
        this.manager = manager;
        this.id = id;

        int numProducts = this.manager.getNumProducts(-1);
        this.inventoryProducts = new ArrayList<>();
        this.cartProducts = new ArrayList<>();

        // Set the layout and color of the inventory and cart JPanels
        this.inventory = new JPanel(new GridLayout(numProducts/2 + 1, 2));
        this.inventory.setBackground(Color.GRAY);
        this.inventory.setForeground(Color.LIGHT_GRAY);

        this.cart = new JPanel(new GridLayout(numProducts/2 + 1, 2));
        this.cart.setBackground(Color.GRAY);
        this.cart.setForeground(Color.LIGHT_GRAY);

        // Create total label
        this.total = new JLabel(String.format("Your total is: $%.2f", 0.0));
    }

    /**
     * Generate a product object.
     * 
     * @param index    int, the index of the product id to return.
     * @param location int, specifies whether to access a cart or the inventory.
     * @return Product, the generated product.
     */
    private Product generateProduct(int index, int location) {
        int newId = manager.getID(index, location);
        String newName = manager.getName(newId, location);
        double newPrice = manager.getPrice(newId, location);
        String newImage = manager.getProductImage(newId, location);

        return new Product(newName, newId, newPrice, newImage);
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
            System.out.println("|--------------------The Candy Store---------------------|");
            System.out.println("| BROWSE ------------------------------------------------|\n");

            System.out.println(String.format("%-12s %-15s %-15s", "Stock", "Product Name", "Unit Price"));

            int numProducts = this.manager.getNumProducts(-1);
            if (numProducts == 0) {
                System.out.println("\nNo products available");
            }
            for (int i = 0; i < numProducts; i++) {
                int productID = this.manager.getID(i, -1);
                Product prod = generateProduct(i, -1);
                System.out.println(String.format("%-12d %-15s %-15.2f", this.manager.getStock(prod, -1),
                        this.manager.getName(productID, -1), this.manager.getPrice(productID, -1)));
            }
            System.out.print("\n");
            return false;


        } else if (command.equals("addtocart")) {
            System.out.println("|--------------------The Candy Store---------------------|");
            System.out.println("| ADD TO CART -------------------------------------------|\n");

            System.out.println(String.format("%-12s %-15s %-15s %-8s", "Stock", "Product Name", "Unit Price",
                    "Option"));

            int numProducts = this.manager.getNumProducts(-1);
            if (numProducts == 0) {
                System.out.println("\nNo products available");
            } else {
                for (int i = 0; i < numProducts; i++) {
                    Product prod = generateProduct(i, -1);
                    System.out.println(String.format("%-12d %-15s %-15.2f %-8s", this.manager.getStock(prod,
                            -1), this.manager.getName(prod.getID(), -1), this.manager.getPrice(prod.getID(),
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

                Product prod = generateProduct(productToRemove, -1);

                if (this.manager.getStock(prod, -1) >= quantityToRemove) {
                    this.manager.add(new Product(this.manager.getName(prod.getID(), -1), prod.getID(),
                            this.manager.getPrice(prod.getID(), -1)), quantityToRemove, this.id);
                    this.manager.remove(prod, quantityToRemove, -1);
                } else {
                    System.out.println("ERROR > INVALID PRODUCT OR PRODUCT QUANTITY TO REMOVE");
                }
            }
            return false;


        } else if (command.equals("removefromcart")) {
            System.out.println("|--------------------The Candy Store---------------------|");
            System.out.println("| REMOVE FROM CART --------------------------------------|\n");

            System.out.println(String.format("%-12s %-15s %-15s %-8s", "Stock", "Product Name", "Unit Price",
                    "Option"));

            int numProducts = this.manager.getNumProducts(this.id);
            if (numProducts == 0) {
                System.out.println("\nYour cart is empty");
            } else {
                for (int i = 0; i < numProducts; i++) {
                    int productID = this.manager.getID(i, this.id);
                    Product prod = generateProduct(i, this.id);
                    System.out.println(String.format("%-12d %-15s %-15.2f %-8s", this.manager.getStock(prod,
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
                Product prod = generateProduct(productToRemove, this.id);

                if (this.manager.getStock(prod, this.id) >= quantityToRemove) {
                    this.manager.add(new Product(this.manager.getName(productToRemoveId, this.id), productToRemoveId,
                            this.manager.getPrice(productToRemoveId, this.id)), quantityToRemove, -1);
                    this.manager.remove(prod, quantityToRemove, this.id);
                } else {
                    System.out.println("ERROR > INVALID PRODUCT OR PRODUCT QUANTITY TO REMOVE");
                }
            }

            return false;


        } else if (command.equals("cart")) {
            System.out.println("|--------------------The Candy Store---------------------|");
            System.out.println("| REVIEW CART -------------------------------------------|\n");

            System.out.println(String.format("%-12s %-15s %-15s", "Stock", "Product Name", "Unit Price"));

            int numProducts = this.manager.getNumProducts(this.id);
            if (numProducts == 0) {
                System.out.println("\nYour cart is empty");
            }
            for (int i = 0; i < numProducts; i++) {
                int productID = this.manager.getID(i, this.id);
                Product prod = generateProduct(i, this.id);
                System.out.println(String.format("%-12d %-15s %-15.2f", this.manager.getStock(prod, this.id),
                        this.manager.getName(productID, this.id), this.manager.getPrice(productID, this.id)));
            }
            System.out.print("\n");
            return false;


        } else if (command.equals("checkout")) {
            System.out.println("|--------------------The Candy Store---------------------|");
            System.out.println("| CHECKOUT ----------------------------------------------|\n");

            double totalPrice = 0.0;
            int currentProductId;
            for (int i = 0; i < this.manager.getNumProducts(this.id); i++) {
                currentProductId = this.manager.getID(i, this.id);
                Product prod = generateProduct(i, this.id);
                totalPrice += this.manager.getPrice(currentProductId, this.id) *
                        this.manager.getStock(prod, this.id);
            }

            System.out.println(String.format("Your Total Is: $%.2f", totalPrice));
            System.out.println("Thank you for shopping at The Course Store!\n");

            return true;


        } else if ((command.equals("quit")) && (id != 0)) {
            int productID;
            for (int i = 0; i < this.manager.getNumProducts(this.id); i++) {
                productID = this.manager.getID(i, id);
                Product prod = generateProduct(i, id);
                this.manager.add(new Product(this.manager.getName(productID, this.id), productID,
                        this.manager.getPrice(productID, this.id)), this.manager.getStock(prod,
                        this.id), -1);
                this.manager.remove(prod, this.manager.getStock(prod, this.id), this.id);
            }
            return true;


        } else if ((id == 0) && (command.equals("addtoinventory"))) {
            System.out.println("|--------------------The Candy Store---------------------|");
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
            System.out.println("|--------------------The Candy Store---------------------|");
            System.out.println("| REMOVE FROM INVENTORY ---------------------------------|\n");

            System.out.println(String.format("%-12s %-15s %-15s %-8s", "Stock", "Product Name", "Unit Price",
                    "Option"));

            int numProducts = this.manager.getNumProducts(-1);
            if (numProducts == 0) {
                System.out.println("\nNo products available");
            } else {
                for (int i = 0; i < numProducts; i++) {
                    int productID = this.manager.getID(i, -1);
                    Product prod = generateProduct(i, -1);
                    System.out.println(String.format("%-12d %-15s %-15.2f %-8s", this.manager.getStock(prod,
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

                Product prod = generateProduct(productToRemove, -1);

                if (!this.manager.remove(prod, quantityToRemove, -1)) {
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
     * Run the program using a console user interface.
     */
    private static void consoleUI() {
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

    /**
     * Update the total price of the products in the cart and display that price on the GUI.
     */
    private void updateTotalPrice() {
        double totalPrice = 0;
        int productID;
        for (int i = 0; i < manager.getNumProducts(id); i++) {
            productID = manager.getID(i, id);
            Product prod = generateProduct(i, id);
            totalPrice += manager.getPrice(productID, id) * manager.getStock(prod, id);
        }
        total.setText(String.format("Your total is %.2f", totalPrice));
    }

    /**
     * Make an add button to add products to the cart.
     *
     * @param prod     Product: the product to add.
     * @param index         int: the index of the product panel in cartProducts.
     * @param cartLabel     JLabel: the label of the quantity of the product being added.
     * @param inventoryLabel    JLabel: the label of the quantity of the product in the inventory.
     * @return  JButton: The add button.
     */
    private JButton makeAddButton(Product prod, int index, JLabel cartLabel, JLabel inventoryLabel) {
        JButton button = new JButton("Add to Cart");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (manager.getStock(prod, id) == -1) {
                    cart.add(cartProducts.get(index));
                }
                String name = manager.getName(prod.getID(), -1);
                double price = manager.getPrice(prod.getID(), -1);
                String productImage = manager.getProductImage(prod.getID(), -1);
                manager.add(new Product(name, prod.getID(), price, productImage), 1, id);
                manager.remove(prod, 1, -1);
                cartLabel.setText(String.format("Quantity: %d", manager.getStock(prod, id)));
                inventoryLabel.setText(String.format("Quantity: %d", manager.getStock(prod, -1)));
                if (manager.getStock(prod, -1) == -1) {
                    inventory.remove(inventoryProducts.get(index));
                }
                updateTotalPrice();
            }
        });
        return button;
    }

    /**
     * Make an remove button to remove products to the cart.
     *
     * @param prod     Product: the product to remove.
     * @param index         int: the index of the product panel in inventoryProducts.
     * @param cartLabel     JLabel: the label of the quantity of the product being removed.
     * @param inventoryLabel    JLabel: the label of the quantity of the product in the inventory.
     * @return  JButton: The remove button.
     */
    private JButton makeRemoveButton(Product prod, int index, JLabel cartLabel, JLabel inventoryLabel) {
        JButton button = new JButton("Remove from Cart");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (manager.getStock(prod, -1) == -1) {
                    inventory.add(inventoryProducts.get(index));
                }
                String name = manager.getName(prod.getID(), id);
                double price = manager.getPrice(prod.getID(), id);
                String productImage = manager.getProductImage(prod.getID(), id);
                manager.add(new Product(name, prod.getID(), price, productImage), 1, -1);
                manager.remove(prod, 1, id);
                cartLabel.setText(String.format("Quantity: %d", manager.getStock(prod, id)));
                inventoryLabel.setText(String.format("Quantity: %d", manager.getStock(prod, -1)));
                if (manager.getStock(prod, id) == -1) {
                    cart.remove(cartProducts.get(index));
                }
                updateTotalPrice();
            }
        });
        return button;
    }


    /**
     * Display the store's interface as a GUI.
     */
    private void displayGUI() {

        // Create frame
        JFrame frame = new JFrame("Store");
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.GRAY);
        frame.setForeground(Color.LIGHT_GRAY);
        frame.setPreferredSize(new Dimension(1000,800));

        // Create product panels
        int productID;
        int numProducts = this.manager.getNumProducts(-1);
        JLabel iName;
        JLabel cName;
        JLabel iPrice;
        JLabel cPrice;
        JLabel iLabel;
        JLabel cLabel;

        for (int i = 0; i < numProducts; i++) {

            // Create inventory product panels
            productID = this.manager.getID(i, -1);
            Product prod = generateProduct(i, -1);
            this.inventoryProducts.add(new ImagePanel(this.manager.getProductImage(productID, -1)));
            this.inventoryProducts.get(i).setLayout(new BoxLayout(this.inventoryProducts.get(i), BoxLayout.Y_AXIS));
            this.inventoryProducts.get(i).setBackground(Color.LIGHT_GRAY);
            this.inventoryProducts.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.inventoryProducts.get(i).setPreferredSize(new Dimension(100,100));

            // Create cart product panels
            this.cartProducts.add(new ImagePanel(this.manager.getProductImage(productID, -1)));
            this.cartProducts.get(i).setLayout(new BoxLayout(this.cartProducts.get(i), BoxLayout.Y_AXIS));
            this.cartProducts.get(i).setBackground(Color.LIGHT_GRAY);
            this.cartProducts.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.cartProducts.get(i).setPreferredSize(new Dimension(100,100));

            // Add product names
            iName = new JLabel(this.manager.getName(productID, -1));
            iName.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.inventoryProducts.get(i).add(iName);
            cName = new JLabel(this.manager.getName(productID, -1));
            cName.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.cartProducts.get(i).add(cName);

            // Add product prices
            iPrice = new JLabel(String.format("Price: $%.2f", this.manager.getPrice(productID, -1)));
            iPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.inventoryProducts.get(i).add(iPrice);
            cPrice = new JLabel(String.format("Price: $%.2f", this.manager.getPrice(productID, -1)));
            cPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.cartProducts.get(i).add(cPrice);

            // Add product quantity
            iLabel = new JLabel(String.format("Quantity: %d", this.manager.getStock(prod, -1)));
            iLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.inventoryProducts.get(i).add(iLabel);
            cLabel = new JLabel(String.format("Quantity: %d", this.manager.getStock(prod, -1)));
            cLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.cartProducts.get(i).add(cLabel);

            // Create a space between quantity and buttons
            this.inventoryProducts.get(i).add(Box.createRigidArea(new Dimension(0,5)));
            this.cartProducts.get(i).add(Box.createRigidArea(new Dimension(0,5)));

            // Add buttons
            this.inventoryProducts.get(i).add(this.makeAddButton(prod, i, cLabel, iLabel));
            this.cartProducts.get(i).add(this.makeRemoveButton(prod, i, cLabel, iLabel));

            // Add inventory products to inventory panel
            this.inventory.add(this.inventoryProducts.get(i));
        }

        // Create title
        frame.add(new JLabel(String.format("Welcome to The Candy Store! (ID: %d)", this.id)), BorderLayout.PAGE_START);

        // Create tabbed panes with titles for inventory and cart
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Inventory", this.inventory);
        tabs.add("Cart", this.cart);

        // Create bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
        bottomPanel.add(Box.createRigidArea(new Dimension(20,10)));
        bottomPanel.add(this.total);
        bottomPanel.add(Box.createRigidArea(new Dimension(100, 10)));
        JButton checkout = new JButton("Checkout");
        checkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                // Declare/initialize variables for the loop
                double totalPrice = 0;
                StringBuilder receiptBuilder = new StringBuilder();
                int productID;
                String name;
                double price;
                int stock;

                // Add receipt header
                receiptBuilder.append("Name         | Price        | Quantity     \n");

                // Loop through all products in the shopping cart
                for (int i = 0; i < manager.getNumProducts(id); i++) {
                    productID = manager.getID(i, id);
                    Product prod = generateProduct(i, id);
                    name = manager.getName(productID, id);
                    price = manager.getPrice(productID, id);
                    stock = manager.getStock(prod, id);
                    totalPrice += price * stock;
                    receiptBuilder.append(String.format("%10s | %10.2f | %10d\n", name, price, stock));
                }
                receiptBuilder.append(String.format("------------------------------------\nYour total is: $%.2f\n",
                        totalPrice));
                receiptBuilder.append("Thank you for shopping at The Candy Store!");
                int userChoice = JOptionPane.showConfirmDialog(frame, receiptBuilder.toString(), "Receipt", JOptionPane.OK_CANCEL_OPTION);

                if (userChoice == JOptionPane.OK_OPTION) {
                    frame.setVisible(false);
                    frame.dispose();
                }
            }

        });
        bottomPanel.add(checkout, BorderLayout.PAGE_END);


        // Finalize frame
        frame.add(tabs, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.PAGE_END);
        frame.pack();

        // Add window listener
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?")
                        == JOptionPane.OK_OPTION) {
                    // close it down!
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });

        // Make frame visible
        frame.setVisible(true);
    }

    /**
     * The main method.
     */
    public static void main(String[] args) {
        if (GUI) {
            StoreManager manager = new StoreManager();
            StoreView user = new StoreView(manager, manager.assignNewCartID());
            user.displayGUI();
        } else {
            consoleUI();
        }
    }
}