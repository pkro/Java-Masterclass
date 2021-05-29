package sample.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Artist {
  private SimpleIntegerProperty id;
  private SimpleStringProperty name;

  public Artist(int id, String name) {
    this.id = new SimpleIntegerProperty(id);
    this.name = new SimpleStringProperty(name);
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

  @Override
  public String toString() {
    return "Artist{" + "id=" + id + ", name='" + name + '\'' + '}';
  }
}
