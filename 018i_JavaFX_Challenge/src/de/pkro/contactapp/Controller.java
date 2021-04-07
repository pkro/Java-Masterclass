package de.pkro.contactapp;

import de.pkro.contactapp.datamodel.Contact;
import de.pkro.contactapp.datamodel.ContactData;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class Controller {
  @FXML private BorderPane mainBorderPane;
  @FXML private TableView<Contact> tableView;
  @FXML private ContextMenu contextMenu;

  @FXML private TableColumn<Object, Object> firstNameCol;
  @FXML private TableColumn<Object, Object> lastNameCol;
  @FXML private TableColumn<Object, Object> phoneNumberCol;
  @FXML private TableColumn<Object, Object> notesCol;

  public void initialize() {
    // Contact c1 = new Contact("Peer", "Teer", "01010101", "super mate");
    // ObservableList<Contact> contacts = FXCollections.observableArrayList(c1);

    // the below doesn't work yet, and worse, it deletes the contacts.xml content
    ContactData.getInstance().loadContacts();
    ObservableList<Contact> contacts = ContactData.getInstance().getContacts();
    ContactData.getInstance().setContacts(contacts);
    tableView.setItems(ContactData.getInstance().getContacts());

    // set all columns to 25%, regardless of window size; fxml sucks a little
    firstNameCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
    lastNameCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
    phoneNumberCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
    notesCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));

    // bind columns to the objects properties (databinding) by name
    // In the course solution, this is done in the fxml:
    // <TableColumn text="firstName">
    //  <cellValueFactory>
    //    <PropertyValueFactory property="firstName" />
    //  </cellValueFactory>
    // </TableColumn>
    firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    notesCol.setCellValueFactory(new PropertyValueFactory<>("notes"));

    // add context menu (just edit as an example)
    contextMenu = new ContextMenu();
    
    MenuItem editMenuItem = new MenuItem();
    editMenuItem.setText("edit");
    editMenuItem.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
            Contact contact = tableView.getSelectionModel().getSelectedItem();
            showDialog(DialogMode.EDIT);
          }
        });
    contextMenu.getItems().add(editMenuItem);

    MenuItem deleteMenuItem = new MenuItem();
    deleteMenuItem.setText("delete");
    deleteMenuItem.setOnAction(
            new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent actionEvent) {
                deleteContact();
              }
            });
    contextMenu.getItems().add(deleteMenuItem);
    
    // edit on doubleclick on row and add contextMenu
    tableView.setRowFactory(
        tv -> {
          TableRow<Contact> row = new TableRow<>();

          row.emptyProperty()
              .addListener(
                  (obs, wasEmpty, isNowEmpty) -> {
                    if (isNowEmpty) {
                      row.setContextMenu(null);
                    } else {
                      row.setContextMenu(contextMenu);
                    }
                  });

          row.setOnMouseClicked(
              event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                  showDialog(DialogMode.EDIT);
                }
              });
          return row;
        });
  }

  @FXML
  public void dispatchMenuAction(ActionEvent e) {
    MenuItem menuItem = (MenuItem) e.getTarget();
    switch (menuItem.getId()) {
      case "editMenuItem":
        showDialog(DialogMode.EDIT);
        break;

      case "newMenuItem":
        showDialog(DialogMode.CREATE);
        break;

      case "quitMenuItem":
        Platform.exit();
        break;

      case "deleteMenuItem":
        deleteContact();
        break;
    }
  }

  private void deleteContact() {
    Contact contact = tableView.getSelectionModel().getSelectedItem();
    if(showConfirmationDialog("Delete contact", "Do you want to delete " + contact.getFullName() + "?")) {
      ContactData.getInstance().deleteContact(contact);
    }
  }

  @FXML
  public boolean showConfirmationDialog(String title, String text) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle(title);
    alert.setHeaderText(title);
    alert.setContentText(text);
    Optional<ButtonType> result = alert.showAndWait();
    return result.isPresent() && result.get() == ButtonType.OK;
  }

  @FXML
  public void showDialog(DialogMode mode) {
    Dialog<ButtonType> dialog = new Dialog<>();

    dialog.initOwner(mainBorderPane.getScene().getWindow());
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("itemdialog.fxml"));
    try {
      dialog.getDialogPane().setContent(loader.load());
    } catch (IOException e) {
      e.printStackTrace();
    }
    ItemDialogController controller = loader.getController();

    Contact selectedContact;

    if (mode == DialogMode.CREATE) {
      dialog.setTitle("Create new contact");
      dialog.setHeaderText("Create a new contact:");
      selectedContact = null;
    } else {
      dialog.setTitle("Edit contact");
      dialog.setHeaderText("Edit an existing contact:");
      selectedContact = tableView.getSelectionModel().getSelectedItem();
      controller.populate(selectedContact);
    }

    dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
    dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
    Optional<ButtonType> result = dialog.showAndWait();
    if (result.isPresent()) {
      if (result.get() == ButtonType.OK) {
        Contact contact = controller.processResults(selectedContact);
        tableView.getSelectionModel().select(contact);
      }
    }
  }
}
