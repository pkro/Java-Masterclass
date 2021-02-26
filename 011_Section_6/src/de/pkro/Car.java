package de.pkro;

import java.util.Locale;

public class Car {
    private int doors;
    private int wheels;
    private String model;
    private String engine;
    private String colour;

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public void setModel(String model) {
        String validModel = model.toLowerCase(Locale.ROOT);
        if(validModel.equals("porsche") || validModel.equals("holden")) {
            this.model = model;
        } else {
            this.model = "Unknown";
        }
    }

    public String getModel() {
        return model;
    }
}
