package de.pkro;

public class NumberPalindrome {
    public static boolean isPalindrome(int number) {
        number = Math.abs(number);
        int reversed = 0;
        int myNum = number;
        int rest = 0;

        do {
            rest = myNum % 10;
            myNum = myNum / 10;
            reversed = (reversed*10) +rest;

        } while (myNum > 0);

        return reversed == number;
    }
}
/*
 * 321 / 10 = 32 Rest 1 (*100)
 * 32 % 10 = 3 rest 2 (* 10)
 * 3 % 10 = 0 rest 3 (* 1)
 * */