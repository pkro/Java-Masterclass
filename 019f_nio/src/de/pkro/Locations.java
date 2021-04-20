package de.pkro;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Locations implements Map<Integer, Location> {
  private static final String LOCATIONS_FILE = "locations_big.txt";
  private static final String DIRECTIONS_FILE = "directions_big.txt";
  private static final String DELIMITER = ",";
  private static Map<Integer, Location> locations = new LinkedHashMap<>();

  static {
    Path locPath = FileSystems.getDefault().getPath(LOCATIONS_FILE);
    Path dirPath = FileSystems.getDefault().getPath(DIRECTIONS_FILE);
    try (Scanner scanner = new Scanner(Files.newBufferedReader(locPath))) {
      scanner.useDelimiter(DELIMITER);
      while (scanner.hasNextLine()) {
        int loc = scanner.nextInt();
        scanner.skip(scanner.delimiter());
        String description = scanner.nextLine();
        locations.put(loc, new Location(loc, description, null));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    try (BufferedReader dirFile = Files.newBufferedReader(dirPath)) {
      String input;
      while ((input = dirFile.readLine() )!= null) {
        String[] chunks = input.split(DELIMITER);
        int loc = Integer.parseInt(chunks[0]);
        String direction = chunks[1];
        int destination = Integer.parseInt(chunks[2]);
        locations.get(loc).addExit(direction, destination);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Locations() {}

  public static void main(String[] args) throws IOException {
    /*Path locPath = FileSystems.getDefault().getPath(LOCATIONS_FILE);
    Path dirPath = FileSystems.getDefault().getPath(DIRECTIONS_FILE);
    try (BufferedWriter locFile = Files.newBufferedWriter(locPath);
        BufferedWriter dirFile = Files.newBufferedWriter(dirPath)) {
      for (Location location : locations.values()) {
        locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
        for (Entry<String, Integer> exits : location.getExits().entrySet()) {
          if (!exits.getKey().equalsIgnoreCase("q")) {
            dirFile.write(
                location.getLocationID() + "," + exits.getKey() + "," + exits.getValue() + "\n");
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }*/
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
