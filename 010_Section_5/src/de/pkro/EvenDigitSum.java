package de.pkro;

public class EvenDigitSum {
    public static int getEvenDigitSum(int number) {
        if(number < 0) {
            return -1;
        }
        int sum = 0;
        int current = 0;
        while(number > 0) {
            current = number % 10;
            sum += (current % 2 == 0 ? current : 0);
            number = number / 10;
        }
        return sum;
    }
}
