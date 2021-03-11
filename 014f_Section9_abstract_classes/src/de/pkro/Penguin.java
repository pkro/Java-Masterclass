package de.pkro;

public class Penguin extends Bird {
    public Penguin(String name) {
        super(name);
    }

    @Override
    public void fly() {
        super.fly(); // optional but a penguin can flap its wings...
        System.out.println("Can't fly :(");
    }
}
