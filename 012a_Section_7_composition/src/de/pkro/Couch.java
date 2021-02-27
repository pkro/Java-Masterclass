package de.pkro;

public class Couch {
    private boolean isExtensible;
    private boolean isExtended;
    private int seats;

    public Couch(int seats, boolean isExtensible, boolean isExtended) {
        if (!isExtensible) {
            isExtended = false;
        }
        this.isExtensible = isExtensible;
        this.isExtended = isExtended;
        this.seats = seats;
    }

    public Couch(int seats, boolean isExtensible) {
        this(seats, isExtensible, false);
    }

    public Couch(int seats) {
        this(seats, false);
    }

    public void extend() {
        System.out.println("Couch is " + (!isExtended ? "now" : "still") + " extended");
        isExtended = true;
    }

    public void retract() {
        System.out.println("Couch is " + (isExtended ? "now" : "still") + " retracted");
        isExtended = false;
    }

    public int getSeats() {
        return seats;
    }
}
