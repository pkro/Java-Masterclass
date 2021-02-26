package de.pkro;

public class Vehicle {
    private int maxSpeed = 100;
    private int speed = 0;

    public Vehicle() {
        this(100, 0);
    }
    public Vehicle(int maxSpeed) {
        this(maxSpeed, 0);
    }
    public Vehicle(int maxSpeed, int speed) {
        this.maxSpeed = maxSpeed;
        this.speed = speed;
    }

    public void setSpeed(int speed) {
        if(speed > maxSpeed) {
            System.out.println("Vehicle can't go faster than "+ maxSpeed);
            return;
        }
        this.speed = speed;
        System.out.println("Vehicle is moving at " + speed);
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getSpeed() {
        return speed;
    }


}
