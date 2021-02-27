package de.pkro;

public class Mitsubishi extends Car {
    public Mitsubishi(int cylinders, String name) {
        super( cylinders, name);
    }
    @Override
    public String startEngine() {
        return "Mitsubishi is starting";
    }

    @Override
    public String accelerate() {
        return "Mitsubishi is accelerating";
    }

    @Override
    public String brake() {
        return "Mitsubishi is stopping";
    }
}
