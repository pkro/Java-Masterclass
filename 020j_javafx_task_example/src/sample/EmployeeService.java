package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.security.Provider;

public class EmployeeService extends Service<ObservableList<String>> {
  @Override
  protected Task<ObservableList<String>> createTask() {
    return new Task<ObservableList<String>>() {
      @Override
      protected ObservableList<String> call() throws Exception {
        String[] names = {"Peer", "Tim", "Jack", "Bill", "crazy german christina", "bob"};

        ObservableList<String> employees = FXCollections.observableArrayList();

        for (int i = 0; i < names.length; i++) {
          employees.add(names[i]);
          updateProgress(i + 1, 6);
          updateMessage("Added " + names[i] + " to the list");
          //updateValue(employees); throws exception as it's not a fx application thread
          Thread.sleep(200);
        }
        // just reset
        updateProgress(0, 6);
        return employees;
      }
    };
  }
}
