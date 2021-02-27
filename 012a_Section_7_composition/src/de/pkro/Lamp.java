package de.pkro;

public class Lamp {
    private boolean isOn;
    private int lux;

    public Lamp(int lux) {
        this(false, lux);
    }

    public Lamp(boolean isOn, int lux) {
        this.isOn = isOn;
        this.lux = lux;
    }

    public boolean isOn() {
        return isOn;
    }

    public int getLux() {
        return lux;
    }

    public void setOn(boolean on) {
        boolean wasOn = isOn;
        isOn = on;
        System.out.println("The room is "+(wasOn == isOn ? "still " : "") + (isOn ? "bright" : "dark"));

    }

    public void switchOnOff() {
        setOn(!isOn);
    }
}
