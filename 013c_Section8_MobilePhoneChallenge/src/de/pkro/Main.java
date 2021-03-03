package de.pkro;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone phone = new MobilePhone("12345");

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            int input = -1;
            showMenu();
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
            } else {
                System.out.println("Please enter only numbers");
            }
            scanner.nextLine();

            switch (input) {
                case 0:
                    phone.printContacts();
                    break;
                case 1:
                    storeContact();
                    break;
                case 2:
                    modifyContact();
                    break;
                case 3:
                    deleteContact();
                    break;
                case 4:
                    queryContact();
                    break;
                case 5:
                    isRunning = false;
                    break;
            }
        }
    }

    private static void showMenu() {
        String[] options = {"show contacts", "store", "modify", "remove", "query", "quit"};
        for (int i = 0; i < options.length; i++) {
            System.out.println((i) + " " + options[i]);
        }
    }

    private static void storeContact() {
        System.out.println("Please enter the contact's name:");
        String name = scanner.nextLine();
        System.out.println("Please enter the contact's number:");
        String number = scanner.nextLine();
        phone.addNewContact(new Contact(name, number));
    }

    private static void modifyContact() {
        System.out.println("Please enter the contact's name:");
        String name = scanner.nextLine();
        Contact oldContact = phone.queryContact(name);
        if (oldContact == null) {
            System.out.println("Contact with that name not found");
            return;
        }
        System.out.println("Updating " + oldContact.getName() + ": " + oldContact.getPhoneNumber());
        System.out.println("Please enter the contact's new name (enter for no change):");
        String newName = scanner.nextLine();
        System.out.println("Please enter the contact's new number (enter for no change):");
        String newNumber = scanner.nextLine();
        if (newName.isEmpty()) {
            newName = oldContact.getName();
        }
        if (newNumber.isEmpty()) {
            newNumber = oldContact.getPhoneNumber();
        }
        phone.updateContact(oldContact, new Contact(newName, newNumber));
    }

    private static void deleteContact() {
        System.out.println("Please enter name of contact:");
        String name = scanner.nextLine();
        phone.removeContact(phone.queryContact(name));

    }

    private static void queryContact() {
        System.out.println("Please enter name:");
        String name = scanner.nextLine();
        Contact contact = phone.queryContact(name);
        System.out.println(contact.getName() + " " + contact.getPhoneNumber());
    }
}
