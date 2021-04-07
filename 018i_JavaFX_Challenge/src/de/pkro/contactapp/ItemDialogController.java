package de.pkro.contactapp;

import de.pkro.contactapp.datamodel.Contact;
import de.pkro.contactapp.datamodel.ContactData;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ItemDialogController {
  @FXML public TextField firstNameField;
  @FXML public TextField lastNameField;
  @FXML public TextField phoneNumberField;
  @FXML public TextArea notesField;

  public void populate(Contact contact) {
    firstNameField.setText(contact.getFirstName());
    lastNameField.setText(contact.getLastName());
    phoneNumberField.setText(contact.getPhoneNumber());
    notesField.setText(contact.getNotes());
  }

  public Contact processResults(Contact contact) {
    String firstName = firstNameField.getText().trim();
    String lastName = lastNameField.getText().trim();
    String phoneNumber = phoneNumberField.getText().trim();
    String notes = notesField.getText().trim();

    if (contact != null) {
      contact.setFirstName(firstName);
      contact.setLastName(lastName);
      contact.setPhoneNumber(phoneNumber);
      contact.setNotes(notes);

    } else {
      contact = new Contact(
              firstNameField.getText().trim(),
              lastNameField.getText().trim(),
              phoneNumberField.getText().trim(),
              notesField.getText().trim());
      ContactData.getInstance().addContact(contact);
    }

    return contact;
  }
}
