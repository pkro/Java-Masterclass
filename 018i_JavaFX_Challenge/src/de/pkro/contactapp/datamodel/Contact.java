package de.pkro.contactapp.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class Contact {
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty phoneNumber;
    private SimpleStringProperty notes;

    public Contact(String firstName, String lastName, String phoneNumber, String notes) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.notes = new SimpleStringProperty(notes);
    }

    public Contact() {
        this("", "", "", "");
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getNotes() {
        return notes.get();
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }

    public String getFullName() {
        return firstName.get() + " " + lastName.get();
    }

    // These must exist and be named like this for data binding to work on updates!
    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public SimpleStringProperty notesProperty() {
        return notes;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName=" + firstName +
                ", lastName=" + lastName +
                ", phoneNumber=" + phoneNumber +
                ", notes=" + notes +
                '}';
    }
}
