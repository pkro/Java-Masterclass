package de.pkro;

public class PC {
    private Case theCase;
    private Monitor monitor;
    private Motherboard motherboard;

    public PC(Case theCase, Monitor monitor, Motherboard motherboard) {
        this.theCase = theCase;
        this.monitor = monitor;
        this.motherboard = motherboard;
    }

/*  Not needed anymore
    public Case getTheCase() {
        return theCase;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }*/

    /*public void powerUp() {
        theCase.pressPowerButton();
        drawLogo();
    }

    public void drawLogo() {
        monitor.drawPixelAt(40, 30, "purple");
        monitor.drawPixelAt(40, 30, "green");
    }*/

    Couch couch = new Couch(3,true );

}
