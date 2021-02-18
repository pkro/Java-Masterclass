package de.pkro;

public class MinutesToYearsDaysCalculator {
    private final static String ERROR_MSG = "Invalid Value";
    public static void printYearsAndDays(long minutes) {
        if(minutes < 0) {
            System.out.println(ERROR_MSG);
            return;
        }
        long years = minutes / (365 * 24 * 60);
        long days = (minutes / 24 / 60)  % 365;

        System.out.println(minutes + " min = " + years + " y and " + days + " d");
    }
}
