package de.pkro;

public class Kaefer extends ChallengeCar {
    public Kaefer() {
        super(100, 3, 1, 0);
    }

    @Override
    public void setSpeed(int speed) {
        if(speed > getMaxSpeed()) {
            System.out.println(this.getClass().getSimpleName()+" can't possibly go faster than "+ getMaxSpeed()+", going to " + getMaxSpeed()+" instead.");
        }
        super.setSpeed(speed);
    }
}
