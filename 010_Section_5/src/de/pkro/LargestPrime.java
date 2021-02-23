package de.pkro;

public class LargestPrime {
    public static int getLargestPrime(int number) {
        if (number <= 1) {
            return -1;
        }
        for(int i=number; i>0; i--) {
            boolean isPrime = true;
            for(int j=2; j<i; j++) {
                if( i%j == 0 ) {
                    isPrime = false;
                    break;                }
            }

            if(isPrime && number%i==0) {
                return i;
            }
        }
        return -1;
    }

    /* Try to understad this at some point...
    public static int getLargestPrime(int number) {
        if (number < 2) {
            return -1;
        }
        for (int i = 2; i < number; i++) {
            if ((number % i) == 0) {
                number /= i;
                i--;
            }
        }
        return number;
    }*/

}
