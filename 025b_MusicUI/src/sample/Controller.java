package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import sample.model.Album;
import sample.model.Artist;
import sample.model.DataSource;

import java.util.ArrayList;
import java.util.List;

public class Controller {
  // hacky, using the same table for artist and albums, thus no <type> annotation
  // as it can contain artist or album types
  // only works because name property exists in both
  @FXML private TableView artistTable;
  @FXML private ProgressBar progressBar;

  @FXML
  public void listArtists() {
    Task<ObservableList<Artist>> task = new GetAllArtistsTask();
    // bind table to task result to automatically update
    artistTable.itemsProperty().bind(task.valueProperty());
    progressBar.progressProperty().bind(task.progressProperty());
    progressBar.setVisible(true);
    task.setOnSucceeded(e -> progressBar.setVisible(false));
    task.setOnFailed(e -> progressBar.setVisible(false));
    new Thread(task).start(); // just task.run() would block the UI i guess
  }

  @FXML
  public void listAlbumsForArtist() {
    final Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();
    if(artist == null) {
      System.out.println("No artist selected");
      return;
    }
    Task<ObservableList<Album>> task = new Task<ObservableList<Album>>() {
      @Override
      protected ObservableList<Album> call() throws Exception {
        List<Album> albums = DataSource.getInstance().queryAlbumsForArtistId(artist.getId());
        return FXCollections.observableArrayList(albums);
      }
    };
    artistTable.itemsProperty().bind(task.valueProperty());
    new Thread(task).start();
  }

  @FXML
  public void updateArtist(ActionEvent actionEvent) {
    System.out.println("Updataaaaaa");
    final Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();
    if(artist == null) {
      System.out.println("No artist selected");
      return;
    }
    Task<Boolean> task = new Task<Boolean>() {
      @Override
      protected Boolean call() throws Exception {
        return DataSource.getInstance().updateArtistName(artist.getId(), "0 new name yay");
      }
    };

    task.setOnSucceeded(e->{
      if(task.valueProperty().get()) {
        artist.setName("0 new name yay");
        artistTable.refresh();
      }
    });

    new Thread(task).start();
  }
}
// separate class because we may use it in startup and when user explicitly asks
// (as opposed to anonymous task)
class GetAllArtistsTask extends Task {
  @Override
  public ObservableList<Artist> call() throws Exception {
    return FXCollections.observableArrayList(DataSource.getInstance().queryArtists(DataSource.ORDER_BY_ASC));
  }
}
