package de.pkro;

public class Main {

    public static void main(String[] args) {
	    HeavenlyBody earth = new Planet("earth", 365);
	    HeavenlyBody earth2 = new Planet("earth", 365);
        System.out.println(earth.equals(earth2));
        System.out.println(earth.getKey().getBodyType());
    }
}
