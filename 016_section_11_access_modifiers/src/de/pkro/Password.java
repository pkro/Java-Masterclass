package de.pkro;

public class Password {
    private static final int key = 9434343;
    private final int encryptedPassword;

    public Password(int password) {
        this.encryptedPassword = encryptDecrypt(password);
    }

    private int encryptDecrypt(int password) {
        return password ^ key;
    }

    public final void storePassword() {
        System.out.println("Saving pass as "+  this.encryptedPassword);
    }

    public boolean letMeIn(int password) {
        boolean res =  encryptDecrypt(password) == this.encryptedPassword;
        if(res) {
            System.out.println("Welcome");
        }
        else {
            System.out.println("Nope");
        }
        return res;
    }
}
