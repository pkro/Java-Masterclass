package de.pkro;

import java.util.*;
import java.util.ArrayList;

public class Main {
    private static Map<Integer, Location> locations = new HashMap<Integer, Location>();
    private static Map<String, String> vocabulary = new HashMap<>();
    private static Map<String, Integer> tmpExit;

    public static void main(String[] args) {
        tmpExit = new HashMap<>();
        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java", tmpExit));

        tmpExit = new HashMap<>();
        tmpExit.put("W", 2);
        tmpExit.put("E", 3);
        tmpExit.put("S", 4);
        tmpExit.put("N", 5);
//        tmpExit.put("B", 99); // null pointer
        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building", tmpExit));

        tmpExit = new HashMap<>();
        tmpExit.put("N", 5);
        locations.put(2, new Location(2, "You are at the top of a hill", tmpExit));

        tmpExit = new HashMap<>();
        tmpExit.put("W", 1);
        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring", tmpExit));

        tmpExit = new HashMap<>();
        tmpExit.put("N", 1);
        tmpExit.put("W", 2);
        locations.put(4, new Location(4, "You are in a valley beside a stream", tmpExit));

        tmpExit = new HashMap<>();
        tmpExit.put("S", 1);
        tmpExit.put("W", 2);
        locations.put(5, new Location(5, "You are in the forest", tmpExit));

        vocabulary.put("NORTH", "N");
        vocabulary.put("N", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("S", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("W", "W");
        vocabulary.put("EAST", "E");
        vocabulary.put("E", "E");
        vocabulary.put("QUIT", "Q");
        vocabulary.put("Q", "Q");


        command();
    }

    public static void command() {
        int loc = 1;
        String direction;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Map<String, Integer> exits = locations.get(loc).getExits();
            System.out.println(locations.get(loc).getDescription());

            if (loc == 0) {
                break;
            }
            System.out.print("Available exits are ");
            for (String exit : exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.println();

            direction = scanner.nextLine().toUpperCase();
            ArrayList<String> tokenized;
            tokenized = new ArrayList<String>(Arrays.asList(direction.split(" ")));
            for (String token : tokenized) {
                if (vocabulary.containsKey(token)) {
                    direction = vocabulary.get(token);
                    break;
                }
            }

            if (exits.containsKey(direction)) {
                loc = exits.get(direction);
            } else {
                System.out.println("You cannot go in that direction");
            }
        }
    }
}
