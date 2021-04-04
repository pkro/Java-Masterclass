package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField nameField;
    @FXML
    private Button helloButton;
    @FXML
    private Button byeButton;
    @FXML
    private CheckBox ourCheckBox;
    @FXML
    private Label ourLabel;

    private boolean clearTextFieldOnKeyPress = false;

    public void initialize() {
        helloButton.setDisable(true);
        byeButton.setDisable(true);
    }

    @FXML
    public void onButtonClicked(ActionEvent e) {
        if(e.getSource().equals(helloButton)) {
            System.out.println("Well hello, " + nameField.getText());
        }
        else if(e.getSource().equals(byeButton)) {
            System.out.println("Good bye, " + nameField.getText() + " :(");
        }

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try { // sleep needs try/catch
                    String s = Platform.isFxApplicationThread() ? "UI thread" : "background thread";
                    System.out.println("I'm going to sleep on the " + s);
                    Thread.sleep(2000);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            String s = Platform.isFxApplicationThread() ? "UI thread" : "background thread";
                            System.out.println("I'm updating on the " + s);
                            ourLabel.setText("Thread done");
                        }
                    });
                } catch(InterruptedException exception) {
                    // nada
                }
            }
        };
        ourLabel.setText("Starting thread");
        new Thread(task).start();


        if(clearTextFieldOnKeyPress) {
            nameField.clear();
            handleKeyReleased();
        }
    }

    @FXML
    public void handleKeyReleased() {
        String text = nameField.getText();
        boolean disableButtons = text.isEmpty() | text.trim().isEmpty();
        helloButton.setDisable(disableButtons);
        byeButton.setDisable(disableButtons);
    }

    @FXML
    public void handleChange() {
        clearTextFieldOnKeyPress = ourCheckBox.isSelected();
    }
}
