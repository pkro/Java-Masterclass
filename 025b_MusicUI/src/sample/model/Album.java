package sample.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Album {
  private SimpleIntegerProperty id;
  private SimpleStringProperty name;
  private SimpleIntegerProperty artistId;

  public Album(int id, String name, int artistId) {
    this.id = new SimpleIntegerProperty(id);
    this.name = new SimpleStringProperty(name);
    this.artistId = new SimpleIntegerProperty(artistId);
  }

  public int getId() {
    return id.get();
  }

  public void setId(int id) {
    this.id.set(id);
  }

  public String getName() {
    return name.get();
  }

  public SimpleStringProperty nameProperty() {
    return name;
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public int getArtistId() {
    return artistId.get();
  }

  public void setArtistId(int artistId) {
    this.artistId = new SimpleIntegerProperty(artistId);
  }
}
