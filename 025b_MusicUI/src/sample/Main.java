package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.DataSource;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
    Parent root = loader.load();
    Controller controller = loader.getController(); // get instance of controller
    controller.listArtists();

    primaryStage.setTitle("Music Database UI");
    primaryStage.setScene(new Scene(root, 800, 675));
    primaryStage.show();
  }

  @Override
  public void init() throws Exception {
    super.init();

    if (!DataSource.getInstance().open()) {
      System.out.println("fatal error: couldn't connect do db");
      Platform.exit();
    }
  }

  @Override
  public void stop() throws Exception {
    super.stop();
    DataSource.getInstance().close();
  }
}
