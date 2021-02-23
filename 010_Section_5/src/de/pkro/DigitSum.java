package de.pkro;

public class DigitSum {
    public static int sumDigits(int number) {
        if(number < 10) {
            return -1;
        }
        int sum = 0;
        do {
            sum += number % 10;
            number = number / 10;
        } while(number > 0);

        return sum;
    }

}
