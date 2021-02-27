package de.pkro;

public class Car {
    private String name;
    private boolean engine;
    private int cylinders;
    private int wheels;

    public Car(int cylinders, String name) {
        this.name = name;
        this.engine = true;
        this.cylinders = cylinders;
        this.wheels = 4;
    }

    public String getName() {
        return name;
    }

    public int getCylinders() {
        return cylinders;
    }


    public String startEngine() {
        return "Car engine is starting";
    }

    public String accelerate() {
        return "Car is accelerating";
    }
    public String brake() {
        return "Car is stopping";
    }
}
