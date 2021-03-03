package de.pkro;

import java.util.ArrayList;

public class MobilePhone {
    private  String myNumber;
    private ArrayList<Contact> myContacts;

    public MobilePhone(String myNumber){
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<Contact>();
    }
    private int findContact(Contact contact){
        for(int i = 0; i < this.myContacts.size();i++) {
            if (this.myContacts.get(i).getName().equals(contact.getName()))
                return i;
        }
        return -1;
    }
    private int findContact(String contactName){

        for(int i = 0; i < this.myContacts.size();i++){
            Contact contact = this.myContacts.get(i);
            if(contact.getName().equals(contactName)){
                System.out.println(i);
                return i;
            }
        }
        return -1;
    }
    public boolean addNewContact(Contact contact){
        if(findContact(contact) < 0 ){
            this.myContacts.add(contact);

            return true;
        }

        return false;
    }
    public boolean updateContact(Contact oldContact,Contact newContact){
        if(findContact(oldContact) >= 0){
            this.myContacts.set(findContact(oldContact),newContact);
            return true;
        }
        return false;

    }
    public boolean removeContact(Contact contact){
        if(findContact(contact) >= 0){
            this.myContacts.remove(contact);
            return true;
        }
        else {
            return false;
        }
    }
    public Contact queryContact(String name){
        if(findContact(name) >= 0){
            return this.myContacts.get(findContact(name));
        }
        return null;
    }
    public void printContacts(){
        System.out.println("Contact List:");
        for(int i = 0; i  < this.myContacts.size();i++){
            System.out.println((i+1)+". "+ this.myContacts.get(i).getName() + " -> "+ this.myContacts.get(i).getPhoneNumber());
        }
    }
}