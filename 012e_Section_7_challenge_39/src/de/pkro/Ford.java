package de.pkro;

public class Ford extends Car {
    public Ford(int cylinders, String name) {
        super( cylinders, name);
    }
    @Override
    public String startEngine() {
        return "Ford is starting";
    }

    @Override
    public String accelerate() {
        return "Ford is accelerating";
    }

    @Override
    public String brake() {
        return "Ford is stopping";
    }
}
