package de.pkro;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location> {
  private static Map<Integer, Location> locations = new LinkedHashMap<>();
  private static final String LOCACTIONS_FILE = "locations_big.txt";
  private static final String LOCACTIONS_OUT = "locations.txt";
  private static final String DIRECTIONS_FILE = "directions_big.txt";
  private static final String DIRECTIONS_OUT = "directions.txt";
  private static final String DELIMITER = ",";

  static {
    // as the choice of delimiter in the original file is poor (comma in a csv that contains text that has commas as well),
    // we use the scanner to be able to read the rest of the line regardless of commas
    // instead using the bufferedreader directly and then splitting;
    // the directionsfile has no such ambiguities
    // UPDATE: we could have used input.split(",", 2); to split input in exactly 2 chunks
    try (Scanner locationsScanner = new Scanner(new BufferedReader(new FileReader(LOCACTIONS_FILE)));
        BufferedReader directionsFile = new BufferedReader(new FileReader(DIRECTIONS_FILE))) {

      locationsScanner.useDelimiter(DELIMITER);
      while (locationsScanner.hasNextLine()) {
        int loc = locationsScanner.nextInt();
        locationsScanner.skip(locationsScanner.delimiter());
        String description = locationsScanner.nextLine();
        Location location = new Location(loc, description, new HashMap<>());
        locations.put(loc, location);
      }

      String input;
      while ((input = directionsFile.readLine()) != null) {
        String[] line = input.split(DELIMITER);
        int loc = Integer.parseInt(line[0]);
        String direction = line[1];
        int destination = Integer.parseInt(line[2]);
        locations.get(loc).addExit(direction, destination);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Locations() {}

  public static void main(String[] args) {
    try (BufferedWriter locationsWriter = new BufferedWriter(new FileWriter(LOCACTIONS_OUT));
         BufferedWriter directionsWriter = new BufferedWriter(new FileWriter(DIRECTIONS_OUT))) {
      for(Entry<Integer, Location> loc: locations.entrySet()) {
        locationsWriter.write(loc.getKey() + DELIMITER + loc.getValue().getDescription());
        locationsWriter.newLine();
        for(Entry<String, Integer> exit: loc.getValue().getExits().entrySet()) {
          boolean isRealDirection = ! exit.getKey().toLowerCase(Locale.ROOT).equals("q");
          if(isRealDirection) {
            directionsWriter.write(loc.getKey()+DELIMITER+exit.getKey()+DELIMITER+exit.getValue());
            directionsWriter.newLine();
          }
        }
      }
    } catch (IOException e) {
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
