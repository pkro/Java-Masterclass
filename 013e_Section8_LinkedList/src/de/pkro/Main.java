package de.pkro;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {

    public static void main(String[] args) {
        /*ArrayList<Integer> intList = new ArrayList<>();

        intList.add(1);
        intList.add(2);
        intList.add(3);

        System.out.println(intList.toString());

        intList.add(1,5); // Slow with ArrayLists

        LinkedList<Integer> intLinkedList = new LinkedList<>();
        intLinkedList.add(1);
        intLinkedList.add(2);
        intLinkedList.add(3);
*/

        LinkedList<String> placesToVisit = new LinkedList<>();
        addInOrder(placesToVisit, "Sydney");
        addInOrder(placesToVisit, "Brisbane");
        //addInOrder(placesToVisit, "Brisbane");
        addInOrder(placesToVisit, "Perth");
        printList(placesToVisit);
        visit(placesToVisit);
    }

    private static void printList(LinkedList<String> placesToVisit) {
        Iterator<String> i = placesToVisit.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }

    private static boolean addInOrder(LinkedList<String> linkedList, String city) {
        ListIterator<String> stringListIterator = linkedList.listIterator();

        while(stringListIterator.hasNext()) {
            int comparison = stringListIterator.next().compareTo(city);
            if(comparison==0) {
                // equal, don't add
                System.out.println(city + " is already included");
                return false;
            } else if(comparison > 0) {
                // new city should appear before the current one
                stringListIterator.previous(); // go back
                stringListIterator.add(city);
                return true;
            }
        }
        stringListIterator.add(city);
        return true;
    }

    public static void visit (LinkedList<String> cities) {
        ListIterator<String> iter = cities.listIterator();
        if(cities.getFirst().isEmpty()) {
            System.out.println("No entries in list");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Now visiting " + iter.next());

        boolean quit = false;
        boolean goingForward = true;
        while(!quit) {
            System.out.println("0 - quit\n1 - next city\n2 - previous city");

            int action = scanner.nextInt();
            scanner.nextLine();
            switch(action) {
                case 0:
                    System.out.println("vacation over");
                    quit = true;
                    break;
                case 1:
                    if(!goingForward) {
                        if(iter.hasNext()) {
                            iter.next();
                        }
                    }
                    goingForward = true;
                    if(iter.hasNext()) {
                        System.out.println("Visiting " + iter.next());
                    }
                    else {
                        System.out.println("No next city");
                    }
                    break;
                case 2:
                    if(goingForward) {
                        if(iter.hasPrevious()) {
                            iter.previous();
                        }
                    }
                    goingForward = false;
                    if(iter.hasPrevious()) {
                        System.out.println("Visiting " + iter.previous());
                    }
                    else {
                        System.out.println("No previous city");
                    }
                    break;

            }

        }

    }


}
