package SYSC2004.Milestone1_HashMap;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Authors: Jeremy Trendoff - 101160306, Evan Smedley - 101148695
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
     */
    private final ShoppingCart cart;

    /**
     *
     * @param manager
     * @param id
     */
    public StoreView(StoreManager manager, int id) {
        this.manager = manager;
        this.id = id;
        this.cart = new ShoppingCart();
    }

    /**
     *
     * @return
     *//*
    private boolean displayGUI() {
        System.out.println("Type 'help' for a list of commands.");
        System.out.println("Enter a command...");

        Scanner in = new Scanner(System.in);
        String command = in.next();
        command = command.toLowerCase().trim();

        if (command.equals("help")) {
            System.out.println("");
        } else if (command.equals("browse")) {

        } else if (command.equals("addtocart")) {

        } else if (command.equals("removefromcart")) {

        } else if (command.equals("")) {

        } else if (command.equals("")) {

        } else if (command.equals("")) {

        } else if (command.equals("")) {

        } else if (command.equals("")) {

        } else if (command.equals("")) {

        }
    }
*/
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        StoreManager manager = new StoreManager(new HashMap<Product, Integer>());
        Scanner in = new Scanner(System.in);

        System.out.println("CHOOSE NUMBER OF USERS >>> ");
        int activeSV = in.nextInt();

        ArrayList<StoreView> users = new ArrayList<>();

        for (int i = 0; i < activeSV; i++) {
            users.add(new StoreView(manager, manager.assignNewCartID()));
        }

        String new_user;

        while (activeSV > 0) {
            System.out.println("NEW USER? (y/n) >>> ");
            new_user = in.next();
            if (new_user.equals("y") || new_user.equals("Y")) {
                users.add(new StoreView(manager, manager.assignNewCartID()));
                new_user = "";
            }
            System.out.println("CHOOSE YOUR STOREVIEW >>> ");
            int choice = in.nextInt();
            if (choice < users.size() && choice >= 0) {
                if (users.get(choice) != null) {
                    String chooseAnother = "";
                    while (!chooseAnother.equals("y") && !chooseAnother.equals("Y")) {
                        if (true) {
                            users.set(choice, null);
                            activeSV--;
                            break;
                        }
                        System.out.println("GO TO ANOTHER STOREVIEW? (y/n) >>> ");
                        chooseAnother = in.next();
                    }
                } else {
                    System.out.println("MAIN > ERROR > BAD CHOICE\nTHAT STOREVIEW WAS DEACTIVATED");
                }
            } else {
                System.out.printf("MAIN > ERROR > BAD CHOICE\nPLEASE CHOOSE IN RANGE [%d, %d]%n%n", 0,
                        users.size() - 1);
            }
        }
        System.out.println("ALL STOREVIEWS DEACTIVATED;");
    }
}
