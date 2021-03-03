package de.pkro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static GroceryList groceryList = new GroceryList();

    public static void main(String[] args) {
        /*GroceryList g = new GroceryList();
        g.addGroceryItem("Wurst");
        g.addGroceryItem("Bier");
        g.addGroceryItem("Coke");
        g.printGroceryList();
        g.modifyGroceryItem(2, "Whisky");
        g.removeGroceryItem(1);
        g.removeGroceryItem("Wurst");
        g.printGroceryList();*/

        /*ArrayList<String> groceryList = new ArrayList<String>();
        groceryList.add("Wurst");
        groceryList.add("Bier");
        groceryList.add("Coke");
        System.out.println(groceryList.toString()); // [Wurst, Bier, Coke]
        groceryList.set(2, "Whisky"); // [Wurst, Bier, Whisky]
        groceryList.remove(1); // [Wurst, Whisky]
        groceryList.remove("Wurst"); // [Whisky]
        System.out.println(groceryList.toString());*/

        boolean quit = false;
        int choice = 0;
        printInstructions();
        while (!quit) {
            choice = -1;
            System.out.println("Enter your choice: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Please enter only numbers.");
            }
            scanner.nextLine();


            switch (choice) {
                case 0:
                    printInstructions();
                    break;
                case 1:
                    groceryList.printGroceryList();
                    break;
                case 2:
                    addItem();
                    break;
                case 3:
                    modifyItem();
                    break;
                case 4:
                    removeItem();
                    break;
                case 5:
                    searchForItem();
                    break;
                case 6:
                    processArraylist();
                case 6:
                    quit = true;
            }
        }


    }

    private static void processArraylist() {
        ArrayList<String> newArrayList = new ArrayLiss()<String>;
        newArrayList.addAll(groceryList.getGroceryList());
        ArrayList<String> nextArray = newArrayList<String>(groceryList.getGroceryList());
        String[] myArray = new String[groceryList.getGroceryList().size()];
        myArray = groceryList.getGroceryList().toArray(myArray);
    }

    private static void searchForItem() {
        System.out.println("Enter item name");
        String item = scanner.nextLine();
        boolean isOnList = groceryList.onFile(item);
        System.out.println("Found " + (isOnList ? item : "nothing"));
    }

    private static void removeItem() {
        System.out.println("Enter number or item name to remove: ");
        if (scanner.hasNextInt()) {
            groceryList.removeGroceryItem(scanner.nextInt() - 1);
            scanner.nextLine();
        } else if (scanner.hasNextLine()) {
            groceryList.removeGroceryItem(scanner.nextLine());
        }
    }

    private static void modifyItem() {
        System.out.println("Replace item name or number: ");
        int idx = -1;
        String oldItem = null;
        if (scanner.hasNextInt()) {
            idx = scanner.nextInt();
            scanner.nextLine();
        } else if (scanner.hasNextLine()) {
            oldItem = scanner.nextLine();
        }

        System.out.println("Replace with: ");
        if (idx > -1) {
            groceryList.modifyGroceryItem(idx - 1, scanner.nextLine());
        } else {
            groceryList.modifyGroceryItem(oldItem, scanner.nextLine());

        }

    }

    private static void addItem() {
        System.out.println("Please enter an item to add:");
        groceryList.addGroceryItem(scanner.nextLine());
    }

    private static void printInstructions() {
        System.out.println("Press:");
        System.out.println("\t0 - this help");
        System.out.println("\t1 - print list");
        System.out.println("\t2 - add item");
        System.out.println("\t3 - modify item");
        System.out.println("\t4 - remove");
        System.out.println("\t5 - search");
        System.out.println("\t6 - quit");


    }
}
