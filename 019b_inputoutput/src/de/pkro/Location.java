package de.pkro;

import java.util.HashMap;
import java.util.Map;

public final class Location {
  private final int locationID;
  private final String description;
  private final Map<String, Integer> exits;

  public Location(int locationID, String description, Map<String, Integer> exits) {
    this.locationID = locationID;
    this.description = description;
    if (exits == null) {
      exits = new HashMap<>();
    }
    this.exits = new HashMap<String, Integer>(exits);

    this.exits.put("Q", 0);
  }

  public int getLocationID() {
    return locationID;
  }

  public String getDescription() {
    return description;
  }

  protected void addExit(String direction, int destination) {
    exits.put(direction, destination);
  }
  public Map<String, Integer> getExits() {
    // return copy so outside code can't change HashMap
    return new HashMap<String, Integer>(exits);
  }
}
