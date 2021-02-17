package de.pkro;

public class DecimalComparator {
    public static boolean areEqualByThreeDecimalPlaces(double num1, double num2) {
        double rounded1 = (long) (num1 * 1000);
        double rounded2 = (long) (num2 * 1000);
        return rounded1 == rounded2;
    }
}
