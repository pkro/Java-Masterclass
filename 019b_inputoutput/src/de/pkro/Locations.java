package de.pkro;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location> {
  private static Map<Integer, Location> locations = new HashMap<>();
  private static final String LOCACTIONS_FILE = "locations_big.txt";
  private static final String DIRECTIONS_FILE = "directions_big.txt";
  private static final String DELIMITER = ",";
  /*public static void main(String[] args) throws IOException {
    try (FileWriter locFile = new FileWriter(LOCACTIONS_FILE);
        FileWriter dirFile = new FileWriter(DIRECTIONS_FILE)) {
      for (Location location : locations.values()) {
        locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
        for (Entry<String, Integer> exit : location.getExits().entrySet()) {
          dirFile.write(
              location.getLocationID() + "," + exit.getKey() + "," + exit.getValue() + "\n");
        }
      }
    }


    FileWriter locFile = null;
    try {
      locFile = new FileWriter("locations.txt");
      for (Location location : locations.values()) {
        locFile.write(
            location.getLocationID()
                + ","
                + location.getDescription()
                +"\n");
      }
    } finally {
      if (locFile != null) {
        locFile.close();
      }
    }
  }*/
  // challenge: rewrite with try with ressource, mulitple streams
  static {
    Scanner scanner = null;
    try {
      scanner = new Scanner(new FileReader(LOCACTIONS_FILE));
      scanner.useDelimiter(DELIMITER);
      while (scanner.hasNextLine()) {
        int loc = scanner.nextInt();
        scanner.skip(DELIMITER);
        String description = scanner.nextLine();
        Map<String, Integer> tmpExit = new HashMap<>();
        locations.put(loc, new Location(loc, description, tmpExit));
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      if (scanner != null) {
        scanner.close(); // also closes FileReader input stream
      }
    }

    try {
      scanner = new Scanner(new BufferedReader(new FileReader(DIRECTIONS_FILE)));
      scanner.useDelimiter(DELIMITER);
      Map<String, Integer> tmpExit = new HashMap<>();
      while (scanner.hasNextLine()) {
        int loc = scanner.nextInt();
        scanner.skip(scanner.delimiter());
        String direction = scanner.next();
        scanner.skip(scanner.delimiter());
        String dest = scanner.nextLine();
        int destination = Integer.parseInt(dest);
        Location location = locations.get(loc);
        location.addExit(direction, destination);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      assert scanner != null;
      scanner.close();
    }
    // only needed initially
    /*
    Map<String, Integer> tmpExit;
    tmpExit = new HashMap<>();
    locations.put(
        0, new Location(0, "You are sitting in front of a computer learning Java", tmpExit));

    tmpExit = new HashMap<>();
    tmpExit.put("W", 2);
    tmpExit.put("E", 3);
    tmpExit.put("S", 4);
    tmpExit.put("N", 5);
    locations.put(
        1,
        new Location(
            1, "You are standing at the end of a road before a small brick building", tmpExit));

    tmpExit = new HashMap<>();
    tmpExit.put("N", 5);
    locations.put(2, new Location(2, "You are at the top of a hill", tmpExit));

    tmpExit = new HashMap<>();
    tmpExit.put("W", 1);
    locations.put(
        3, new Location(3, "You are inside a building, a well house for a small spring", tmpExit));

    tmpExit = new HashMap<>();
    tmpExit.put("N", 1);
    tmpExit.put("W", 2);
    locations.put(4, new Location(4, "You are in a valley beside a stream", tmpExit));

    tmpExit = new HashMap<>();
    tmpExit.put("S", 1);
    tmpExit.put("W", 2);
    locations.put(5, new Location(5, "You are in the forest", tmpExit));
    */

  }

  public Locations() {}

  public void addLocation(int idx, Location location) {
    locations.put(idx, location);
  }

  @Override
  public int size() {
    return locations.size();
  }

  @Override
  public boolean isEmpty() {
    return locations.isEmpty();
  }

  @Override
  public boolean containsKey(Object key) {
    return locations.containsKey(key);
  }

  @Override
  public boolean containsValue(Object value) {
    return locations.containsValue(value);
  }

  @Override
  public Location get(Object key) {
    return locations.get(key);
  }

  @Override
  public Location put(Integer key, Location value) {
    return locations.put(key, value);
  }

  @Override
  public Location remove(Object key) {
    return locations.remove(key);
  }

  @Override
  public void putAll(Map<? extends Integer, ? extends Location> m) {
    locations.putAll(m);
  }

  @Override
  public void clear() {
    locations.clear();
  }

  @Override
  public Set<Integer> keySet() {
    return locations.keySet();
  }

  @Override
  public Collection<Location> values() {
    return locations.values();
  }

  @Override
  public Set<Entry<Integer, Location>> entrySet() {
    return locations.entrySet();
  }
}
