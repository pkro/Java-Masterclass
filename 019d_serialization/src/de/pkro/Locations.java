package de.pkro;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location> {
  private static Map<Integer, Location> locations = new LinkedHashMap<>();
  private static final String LOCATIONS_FILE = "locations_big.txt";
  private static final String LOCATIONS_BIN = "locations.dat";
  private static final String DIRECTIONS_FILE = "directions_big.txt";
  private static final String DIRECTIONS_BIN = "directions.dat";
  private static final String DELIMITER = ",";

  static {
    try (ObjectInputStream locationsStream =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(LOCATIONS_BIN)))) {
      boolean EOF = false;
      while (!EOF) {
        try {
          Location location = (Location) locationsStream.readObject();
          locations.put(location.getLocationID(), location);
        } catch (EOFException e) {
          EOF = true;
        }
      }

    } catch (InvalidClassException e) {
      System.out.println("Invalid class exception " + e.getMessage());
      e.printStackTrace();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public Locations() {}

  public static void main(String[] args) throws IOException {
    try (ObjectOutputStream locFile =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(LOCATIONS_BIN)))) {
      for (Location location : locations.values()) {
        locFile.writeObject(location);
      }
    }
  }

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
