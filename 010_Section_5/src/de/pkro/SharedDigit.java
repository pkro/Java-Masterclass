package de.pkro;

public class SharedDigit {

    public static boolean hasSharedDigit(int a, int b) {
        if (a < 10 || a > 99 || b < 10 || b > 99) {
            return false;
        }
        while (a > 0) {
            int d = a % 10;
            int myB = b;
            while (myB > 0) {
                if (myB % 10 == d) {
                    return true;
                }
                myB /= 10;
            }
            a /= 10;
        }
        return false;

    }
}
