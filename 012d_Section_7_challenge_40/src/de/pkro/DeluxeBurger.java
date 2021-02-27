package de.pkro;

public class DeluxeBurger extends Hamburger{
    public DeluxeBurger() {
        super("Deluxe hamburger", "sausage", 19.10, "White roll");
        super.addHamburgerAddition1("Bacon", 0.0);
        super.addHamburgerAddition1("Drink", 0.0);
    }

    @Override
    public void addHamburgerAddition1(String name, double price) {
        System.out.println("Can't be done");
    }
    @Override
    public void addHamburgerAddition2(String name, double price) {
        System.out.println("Can't be done");
    }
    @Override
    public void addHamburgerAddition3(String name, double price) {
        System.out.println("Can't be done");
    }
    @Override
    public void addHamburgerAddition4(String name, double price) {
        System.out.println("Can't be done");
    }
}
