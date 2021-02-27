package de.pkro;

public class Room {
    private Lamp ceilingLamp;
    private Couch couch;
    private Dimensions dimensions;
    private boolean isInSleepMode = false;

    public Room(Dimensions dimensions, Lamp lamp, Couch couch) {
        this.dimensions = dimensions;
        this.ceilingLamp = lamp;
        this.couch = couch;
    }

    public void toggleSleepMode() {
        if(isInSleepMode) {
            couch.retract();
            ceilingLamp.setOn(true);
            isInSleepMode = false;
        } else {
            couch.extend();
            ceilingLamp.setOn(false);
            isInSleepMode = true;
        }
    }

    public Lamp getCeilingLamp() {
        return ceilingLamp;
    }

    public double getLivingSpace() {
        return dimensions.getArea();
    }
    public double getCubic() {
        return dimensions.getCubic();
    }


}
