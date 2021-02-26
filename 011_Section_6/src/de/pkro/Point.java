package de.pkro;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Point() {
        this(0,0);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double distance() {
        return distance(new Point());
    }
    public double distance(int a, int b) {
        return distance(new Point(a,b));
    }
    public double distance(Point another) {
        int a = Math.abs(getX() - another.getX());
        int b = Math.abs(getY() - another.getY());

        return Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
    }
}
