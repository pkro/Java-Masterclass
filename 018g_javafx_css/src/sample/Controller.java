package sample;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.apache.commons.lang3.SystemUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class Controller {
    @FXML
    GridPane gridPane;

    @FXML
    WebView webView;

    public void initialize() {

    }

    @FXML
    public void zoomOnHover(Event event) {
        double scale = 1.0;
        DropShadow dropShadow = null;
        if(event.getEventType().getName().equals("MOUSE_ENTERED")) {
            scale = 1.2;
            dropShadow = new DropShadow(0.5, Color.BLUE);
        }
        Control control = (Control) event.getSource();
        control.setEffect(dropShadow);
        control.setScaleX(scale);
        control.setScaleY(scale);
    }

    @FXML
    public void openFile() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text", "*.txt"),
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png", "*.gif"),
                new FileChooser.ExtensionFilter("All files", "*.*") // catch all
        );

        File file = chooser.showOpenDialog(gridPane.getScene().getWindow());
        // example for opeing multiple files
        //List<File> file = chooser.showOpenMultipleDialog(gridPane.getScene().getWindow());
        if(file != null) { // user canceled out of file chooser
            System.out.println(file);
        }
    }

    @FXML
    public void openDirectory() {
        DirectoryChooser chooser = new DirectoryChooser();
        File directory = chooser.showDialog(gridPane.getScene().getWindow());
        if(directory != null) {
            System.out.println(directory);
        }
    }

    @FXML
    public void saveFile() {
        FileChooser chooser = new FileChooser();
        File file = chooser.showSaveDialog(gridPane.getScene().getWindow());
        if(file != null) { // user canceled out of file chooser
            System.out.println(file.getPath());
        }
    }

    @FXML
    public void handleLinkClicked() {
        browseURL("http://www.javafx.com");
    }

    public void browseURL(String urlString) {
        try {
            if (SystemUtils.IS_OS_LINUX) {
                // Workaround for Linux because "Desktop.getDesktop().browse()" doesn't work on some Linux implementations
                if (Runtime.getRuntime().exec(new String[] { "which", "xdg-open" }).getInputStream().read() != -1) {
                    Runtime.getRuntime().exec(new String[] { "xdg-open", urlString });
                } else {
                    System.out.println("x-dg open not supported!");
                }
            } else {
                if (Desktop.isDesktopSupported())
                {
                    Desktop.getDesktop().browse(new URI(urlString));
                } else {
                    System.out.println("Desktop command not supported!");
                }
            }

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showPageInWebView() {
        WebEngine engine = webView.getEngine();
        engine.load("http://www.javafx.com");
    }
}
