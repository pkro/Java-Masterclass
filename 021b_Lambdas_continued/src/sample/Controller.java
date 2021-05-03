package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
  @FXML
  Button clickMeButton;

  public void initialize() {
    /*clickMeButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        System.out.println("Button clicked with a lot of boilerplate");
      }
    });*/

    clickMeButton.setOnAction((e)-> System.out.println("Button clicked with cool kid lambda"));
  }
}
