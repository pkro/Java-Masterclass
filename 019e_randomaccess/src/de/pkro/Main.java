package de.pkro;

import java.io.IOException;
import java.util.*;
import java.util.ArrayList;

public class Main {
  private static Locations locations = new Locations();
  private static Map<String, String> vocabulary = new HashMap<>();
  private static final int START_LOCATION = 64;

  public static void main(String[] args) throws IOException {

    vocabulary.put("NORTH", "N");
    vocabulary.put("N", "N");
    vocabulary.put("SOUTH", "S");
    vocabulary.put("S", "S");
    vocabulary.put("WEST", "W");
    vocabulary.put("W", "W");
    vocabulary.put("EAST", "E");
    vocabulary.put("E", "E");
    vocabulary.put("UP", "U");
    vocabulary.put("U", "U");
    vocabulary.put("DOWN", "D");
    vocabulary.put("D", "D");
    vocabulary.put("QUIT", "Q");
    vocabulary.put("Q", "Q");

    command();

  }

  public static void command() throws IOException {
    int loc = START_LOCATION;
    String direction;
    Scanner scanner = new Scanner(System.in);

    while (true) {
      if (loc == 0) {
        break;
      }
      Map<String, Integer> exits = locations.get(loc).getExits();
      System.out.println(locations.get(loc).getDescription());


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
    locations.close();
  }
}
