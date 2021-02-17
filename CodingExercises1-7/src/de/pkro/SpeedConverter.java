package de.pkro;

public class SpeedConverter {
    static final double MILE_IN_KM = 1.609;

    public static long toMilesPerHour(double kilometersPerHour) {
        if (kilometersPerHour < 0) {
            return -1;
        }
        return Math.round(kilometersPerHour / MILE_IN_KM);
    }

    public static void printConversion(double kilometersPerHour) {
        int result = (int) toMilesPerHour(kilometersPerHour);
        String message = result == -1 ? "Invalid Value" : kilometersPerHour + " km/h = " + result + " mi/h";
        System.out.println(message);
    }
}
