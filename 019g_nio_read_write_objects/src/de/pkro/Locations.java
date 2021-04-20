package de.pkro;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Locations implements Map<Integer, Location> {
  private static final String LOCATIONS_FILE = "locations_big.txt";
  private static final String DIRECTIONS_FILE = "directions_big.txt";
  private static final String LOCATIONS_BIN = "locations_big.dat";

  private static final String DELIMITER = ",";
  private static Map<Integer, Location> locations = new LinkedHashMap<>();

  static {
    Path locPath = Paths.get(LOCATIONS_BIN);
    try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(locPath) ))) {
      boolean EOF = false;
      while(!EOF) {
        try {
          Location location = (Location) locFile.readObject();
          locations.put(location.getLocationID(), location);
        } catch (EOFException e) {
          EOF = true;
        }
      }
    } catch (InvalidClassException  e) {
      e.printStackTrace();
    } catch(IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public Locations() {}

  public static void main(String[] args) throws IOException {
    Path locPath = Paths.get(LOCATIONS_BIN);
    // only difference is Files.newOutputStream instead of Files.OutputStream
    try(ObjectOutputStream locFile = new ObjectOutputStream(Files.newOutputStream(locPath))) {
      for(Location location: locations.values()) {
        locFile.writeObject(location);
      }
    } catch(IOException e) {
      e.printStackTrace();
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
