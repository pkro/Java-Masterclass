package de.pkro;

public class ChallengeCar extends Vehicle {
    private int gears = 4;
    private int gear = 1;
    private int direction = 0; // degrees where 0 = north


    public ChallengeCar() {
        this(5, 1, 40);
    }

    public ChallengeCar(int gears, int gear, int direction) {
        this(200, gears, gear, direction); // cars CAN NEVER GO GASTER THAN 200
    }

    public ChallengeCar(int maxSpeed, int gears, int gear, int direction) {
        super(maxSpeed); // cars CAN NEVER GO GASTER THAN 200
        this.gears = gears;
        this.gear = gear;
        this.direction = direction;
    }

    public void steer(int degrees) {
        if (Math.abs(degrees) > 360) {
            System.out.println("Wrong value for steering, use degrees from -360 to + 360");
            return;
        }
        if (direction + degrees > 360) {
            degrees -= 360;
        } else if (direction + degrees < 0) {
            degrees += 360;
        }
        direction += degrees;

        System.out.println("Car now steering towards " + direction + "°");
    }

    public void setGear(int gear) {
        if (gear < 1 || gear > gears) {
            System.out.println("ChallengeCar has only " + gears + " gears");
            return;
        }
        this.gear = gear;
    }

    public int getGear() {
        return gear;
    }

    public int getDirection() {
        return direction;
    }


}
