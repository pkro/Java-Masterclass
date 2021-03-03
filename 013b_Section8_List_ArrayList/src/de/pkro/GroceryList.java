package de.pkro;

import java.util.ArrayList;
import java.util.Arrays;

public class GroceryList {
    private ArrayList<String> groceryList = new ArrayList<String>();
    public void addGroceryItem(String item) {
        groceryList.add(item);
    }

    public void printGroceryList() {
        System.out.println("You have: " + groceryList.size() + " items in your grocery list");
        for(int i=0; i<groceryList.size(); i++) {
            System.out.println((i+1) + ". " + groceryList.get(i));
        }
    }

    public void modifyGroceryItem(String oldItem, String newItem) {
        modifyGroceryItem(findItem(oldItem), newItem);
    }

    public ArrayList<String> getGroceryList() {
        return groceryList;
    }

    public void modifyGroceryItem(int position, String newItem) {
        String prev = groceryList.get(position);
        if(position >= 0) {
            groceryList.set(position, newItem);
            System.out.println("Replaced " + prev + " with " + newItem);
        }
    }

    public void removeGroceryItem(int position) {
        System.out.println("Removed " + groceryList.get(position));
        groceryList.remove(position);
    }

    /**
     * Removes an item by name
     * @param item
     */
    public void removeGroceryItem(String item) {
        System.out.println("Removed " + item);
        groceryList.remove(item);
    }

    private int findItem(String searchItem) {
        return  groceryList.indexOf(searchItem);
    }

    public boolean onFile(String searchItem) {
        return groceryList.contains(searchItem);
    }
}
