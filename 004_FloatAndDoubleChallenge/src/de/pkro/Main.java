package de.pkro;

public class Main {
    static final double KG_IN_POUNDS = 0.45359237d;

    public static void main(String[] args) {

        double pounds = 420.5;
        double kg = pounds * KG_IN_POUNDS;
        System.out.println(pounds + " pounds is " + kg + " kg");
    }
}
