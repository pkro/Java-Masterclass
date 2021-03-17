package de.pkro;

import java.util.*;
import java.util.ArrayList;
public class Main {
    private static Map<Integer, Location> locations = new HashMap<Integer, Location>();
    private static Map<String, String> vocabulary = new HashMap<>();

    public static void main(String[] args) {
        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java"));
        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building"));
        locations.put(2, new Location(2, "You are at the top of a hill"));
        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring"));
        locations.put(4, new Location(4, "You are in a valley beside a stream"));
        locations.put(5, new Location(5, "You are in the forest"));

        locations.get(1).addExit("W", 2);
        locations.get(1).addExit("E", 3);
        locations.get(1).addExit("S", 4);
        locations.get(1).addExit("N", 5);

        locations.get(2).addExit("N", 5);

        locations.get(3).addExit("W", 1);

        locations.get(4).addExit("N", 1);
        locations.get(4).addExit("W", 2);

        locations.get(5).addExit("S", 1);
        locations.get(5).addExit("W", 2);

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

            if(exits.containsKey(direction)) {
                loc = exits.get(direction);
            }
            else {
                System.out.println("You cannot go in that direction");
            }
        }
    }
}
