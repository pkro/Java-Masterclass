package de.pkro;

public class Main {

    private static final String ERROR_MESSAGE = "Invalid Parameters";

    public static void main(String[] args) {
        System.out.println(calcFeetAndInchesToCentimeters(6,1));
        System.out.println(calcFeetAndInchesToCentimeters(73)); // should be same result
        System.out.println(calcFeetAndInchesToCentimeters(6,0));
        System.out.println(getDurationString(90,34)); // should be same result
        System.out.println(getDurationString(3672L)); // should be same result
        System.out.println(getDurationString(-3672L)); // should be same result

    }

    public static int calculateScore(String playerName, int score) {
        System.out.println(playerName + " scored " + score);
        return score * 1000;
    }

    public static int calculateScore(int score) {
        System.out.println("Unnamed player scored " + score);
        return score * 1000;
    }

    public static void calculateScore() {
        System.out.println("Unnamed player scored unknown amount");

    }

    public static double calcFeetAndInchesToCentimeters(double feet, double inches) {
        if (feet < 0 || inches < 0 || inches > 12) {
            return -1;
        }
        return (feet * 12 + inches) * 2.54;
    }

    public static double calcFeetAndInchesToCentimeters(double inches) {
        if (inches < 0) {
            return -1;
        }
        return calcFeetAndInchesToCentimeters((int) inches / 12, inches % 12);
    }

    public static String getDurationString(long minutes, long seconds) {
        if(minutes < 0 || seconds < 0 || seconds > 60) {
            return ERROR_MESSAGE;
        }
        return prefixedNumberString(minutes / 60) + "h " + prefixedNumberString(minutes % 60) + "m " + prefixedNumberString(seconds) + "s";
    }

    public static String prefixedNumberString(long num) {
        return String.format("%02d", num);
    }

    public static String getDurationString(long seconds) {
        if(seconds < 0) {
            return ERROR_MESSAGE;
        }
        long minutes = seconds / 60;
        long secondsRemaining = seconds % 60;
        return getDurationString(minutes, secondsRemaining);
    }
}
