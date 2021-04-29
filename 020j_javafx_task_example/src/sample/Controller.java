package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

public class Controller {
  @FXML ProgressBar progressBar;
  // private Task<ObservableList<String>> task;
  @FXML private ListView<String> listView;
  @FXML private Label progressLabel;
  private Service<ObservableList<String>> service;

  public void initialize() {
    service = new EmployeeService();
    listView.itemsProperty().bind(service.valueProperty());
    progressBar.progressProperty().bind(service.progressProperty());
    progressLabel.textProperty().bind(service.messageProperty());
    progressBar.visibleProperty().bind(service.runningProperty());
    progressLabel.visibleProperty().bind(service.runningProperty());

    // code below replaced by the 2 lines (binding) above
    /*progressBar.setVisible(false);
    progressLabel.setVisible(false);

    service.setOnRunning(
        new EventHandler<WorkerStateEvent>() {
          @Override
          public void handle(WorkerStateEvent workerStateEvent) {
            progressBar.setVisible(true);
            progressLabel.setVisible(true);
          }
        });

    service.setOnSucceeded(
        new EventHandler<WorkerStateEvent>() {
          @Override
          public void handle(WorkerStateEvent workerStateEvent) {
            progressBar.setVisible(false);
            progressLabel.setVisible(false);
          }
        });*/
  }

  @FXML
  public void buttonPressed() {
    // new Thread(task).start();
    if (service.getState() == Worker.State.SUCCEEDED) {
      service.reset();
      progressBar.setDisable(false);
      service.start();
    } else if (service.getState() == Worker.State.READY) {
      service.start();
    }
  }
}
