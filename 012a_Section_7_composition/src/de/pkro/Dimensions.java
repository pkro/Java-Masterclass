package de.pkro;

public class Dimensions {
    private double width;
    private double height;
    private double depth;

    public Dimensions(double width, double height, double depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDepth() {
        return depth;
    }

    public double getArea() {
        return width*depth;
    }
    public double getCubic() {
        return getArea() * height;
    }
}
