package de.pkro;

public class GreatestCommonDivisor {
    public static int getGreatestCommonDivisor(int first, int second) {
        if (first < 10 || second < 10) {
            return -1;
        }
        int divisor = first > second ? second : first;
        while (divisor > 1) {
            if (first % divisor == 0 && second % divisor == 0) {
                return divisor;
            }
            divisor--;
        }
        return 1;
    }
}
