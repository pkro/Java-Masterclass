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

    try (DataInputStream locationsStream = new DataInputStream(new BufferedInputStream(new FileInputStream(LOCATIONS_BIN)))) {
      boolean EOF = false;
      while (!EOF) {
        try {
        int loc = locationsStream.readInt();
        String description = locationsStream.readUTF();

        Map<String, Integer> exits = new LinkedHashMap<>();
        int numExits = locationsStream.readInt();
        for(int i=0; i<numExits; i++) {
          exits.put(locationsStream.readUTF(), locationsStream.readInt());
        }
        Location location = new Location(loc, description, exits);
        locations.put(loc, location);
        } catch (EOFException e) {
          EOF = true;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Locations() {}

  public static void main(String[] args) throws IOException {
    try(DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(LOCATIONS_BIN)))) {
      for(Location location: locations.values()) {
        locFile.writeInt(location.getLocationID());
        locFile.writeUTF(location.getDescription());
        locFile.writeInt(location.getExits().size()-1); // save number of exits to read from stream later
        for(String direction: location.getExits().keySet()) {
          if(!direction.equalsIgnoreCase("Q")) {
            locFile.writeUTF(direction);
            locFile.writeInt(location.getExits().get(direction));
          }
        }
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
