package de.pkro;

public class LastDigitChecerk {
    public static boolean hasSameLastDigit(int a, int b, int c) {
        if (!isValid(a) || !isValid(b) || !isValid(c)) {
            return false;
        }
        return a % 10 == b % 10 || b % 10 == c % 10 || a % 10 == c % 10;
    }

    public static boolean isValid(int num) {
        return num >= 10 && num <= 1000;
    }
}
