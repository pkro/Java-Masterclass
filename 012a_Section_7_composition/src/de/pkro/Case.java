package de.pkro;

public class Case {
    private String model;
    private String manufacturer;
    private String powerSupply;
    private Dimensions dimensions;
    private boolean poweredOn = false;

    public Case(String model, String manufacturer, String powerSupply, Dimensions dimensions) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.powerSupply = powerSupply;
        this.dimensions = dimensions;
    }

    public void pressPowerButton() {
        poweredOn = ! poweredOn;
        System.out.println("Power button pressed, turned Computer " + (isPoweredOn() ? "on" : "off"));
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getPowerSupply() {
        return powerSupply;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public boolean isPoweredOn() {
        return poweredOn;
    }
}
