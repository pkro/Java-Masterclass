package de.pkro;

public class ExtendedPassword extends Password {
    private int decryptedPassword;

    public ExtendedPassword(int password) {
        super(password);
        this.decryptedPassword = password;
    }

    // prevented by using final in base class
    /*@Override
    public void storePassword() {
        System.out.println("saving as "+ decryptedPassword);
    }*/
}
