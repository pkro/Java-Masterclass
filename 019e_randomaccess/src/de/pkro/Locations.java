package de.pkro;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location> {
  private static final String LOCATIONS_BIN = "locations.dat";
  private static final String LOCATIONS_RAND = "locations_rand.dat";
  private static final Map<Integer, Location> locations = new LinkedHashMap<>();
  private static Map<Integer, IndexRecord> index = new LinkedHashMap<>();
  private static RandomAccessFile ra;

  // we just read the index now as the point is to load the actual location on demand
  static {
    try {
      ra = new RandomAccessFile(LOCATIONS_RAND, "rwd");
      int numLocations = ra.readInt(); // not needed but good practice to store num of records
      long locationsStartPoint = ra.readInt();
      while (ra.getFilePointer() < locationsStartPoint) {
        int locationId = ra.readInt();
        int locationStart = ra.readInt();
        int locationLength = ra.readInt();
        IndexRecord record = new IndexRecord(locationStart, locationLength);
        index.put(locationId, record); // the map we also used for writing in the former Main method
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Locations() {}

  // creating a random access / indexed file
  // first 4 bytes: number of locations (bytes 0-4)
  // next 4 bytes: start offset of location section (bytes 4-7)
  // index (1692 bytes, byte 8 - 1699)
  // actual location record from byte 1700 onwards

  // commented out as we've already written the file now
  /*
  public static void main(String[] args) throws IOException {
    try (RandomAccessFile rao = new RandomAccessFile(LOCATIONS_RAND, "rwd")) {
      rao.writeInt(locations.size());
      int numberOfIntsInRecord =
          3; // 3 ints: locationID, file offset of location, size of location record in bytes
      int indexSize = locations.size() * numberOfIntsInRecord * Integer.BYTES;
      // file pointer is now after the first integer from the first writeInt operation
      int locationStart =
          (int) // cast to int because filepointer is long
              (indexSize + rao.getFilePointer() + Integer.BYTES);
      rao.writeInt(locationStart);
      // write locations and simultaneously build index in memory to write later
      long indexStart = rao.getFilePointer(); // save current pointer for writing index later

      // only needed for first location, sequential writing afterwards
      int startPointer = locationStart;
      for (Location location : locations.values()) {
        rao.writeInt(location.getLocationID());
        rao.writeUTF(location.getDescription());
        StringBuilder builder = new StringBuilder();
        for (String direction : location.getExits().keySet()) {
          if (!direction.equalsIgnoreCase(("Q"))) {
            builder.append(direction);
            builder.append(",");
            builder.append(location.getExits().get(direction));
            builder.append(",");
            // = direction, locationID, direction, ..., e.g. N,1,U,2...
          }
        }
        rao.writeUTF(builder.toString());
        // IndexRecord = own simple class with 2 fields, startByte and length
        IndexRecord record =
            new IndexRecord(startPointer, (int) (rao.getFilePointer() - startPointer));
        // index = Map<Integer, IndexRecord>
        index.put(location.getLocationID(), record);
        startPointer = (int) rao.getFilePointer();
      }

      // write the actual index starting on the offset we saved before
      rao.seek(indexStart);
      for (Integer locationID : index.keySet()) {
        rao.writeInt(locationID);
        rao.writeInt(index.get(locationID).getStartByte());
        rao.writeInt(index.get(locationID).getLength());
      }
    }
  }*/

  public void addLocation(int idx, Location location) {
    locations.put(idx, location);
  }

  public Location getLocation(int locationId) throws IOException {
    IndexRecord record = index.get(locationId);
    ra.seek(record.getStartByte());
    int id = ra.readInt(); // same as locationId
    String description = ra.readUTF();
    String exits = ra.readUTF();
    String[] exitPart = exits.split(",");
    Location location = new Location(id, description, null);
    if (locationId != 0) { // the "quit" location
      for (int i = 0; i < exitPart.length; i += 2) {
        String direction = exitPart[i];
        int destination = Integer.parseInt(exitPart[i + 1]);
        location.addExit(direction, destination);
      }
    }
    return location;
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
    try {
      return getLocation((int) key);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
    // return locations.get(key);
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

  public void close() throws IOException {
    ra.close();
  }
}
