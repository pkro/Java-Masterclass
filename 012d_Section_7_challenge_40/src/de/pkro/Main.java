package de.pkro;

public class Main {

    public static void main(String[] args) {
        Hamburger h = new Hamburger("Basic", "Sausage", 0.0, "White");
        h.addHamburgerAddition1("Tomato", 0.27);
        h.addHamburgerAddition2("Lettuce", 0.75);
        h.addHamburgerAddition3("Cheese", 1.13);
        System.out.println("Total burger price is " + h.itemizeHamburger());
    }
}
