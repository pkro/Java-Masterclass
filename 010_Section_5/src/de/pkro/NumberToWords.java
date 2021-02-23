package de.pkro;

public class NumberToWords {
    public static void numberToWords(int number) {
        if(number<0) {
            System.out.println("Invalid Value");
        }
        int myNum = reverse(number);
        int digitsReversed = getDigitCount(myNum);

        do {
            switch(myNum%10) {
                case 0:
                    System.out.println("Zero");
                    break;
                case 1:
                    System.out.println("One");
                    break;
                case 2:
                    System.out.println("Two");
                    break;
                case 3:
                    System.out.println("Three");
                    break;
                case 4:
                    System.out.println("Four");
                    break;
                case 5:
                    System.out.println("Five");
                    break;
                case 6:
                    System.out.println("Six");
                    break;
                case 7:
                    System.out.println("Seven");
                    break;
                case 8:
                    System.out.println("Eight");
                    break;
                case 9:
                    System.out.println("Nine");
                    break;
            }
            myNum = myNum/10;
        } while(myNum>0);

        int digitsExpected = getDigitCount(number);
        while(digitsReversed < digitsExpected) {
            System.out.println("Zero");
            digitsReversed++;
        }
    }

    public static int reverse(int number) {
        int reversed = 0;
        while(number!=0) {
            reversed = reversed + number % 10;
            reversed *= 10;
            number = number / 10;
        }

        return reversed/10;
    }

    public static int getDigitCount(int number) {
        if(number < 0) {
            return -1;
        }
        if(number < 10) {
            return 1;
        }
        int digitCount = 0;
        while (number>0) {
            number = number / 10;
            digitCount++;
        }
        return digitCount;
    }
}
