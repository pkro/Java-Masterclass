package de.pkro;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        /*************************************
         * Challenges / Coding exercises
         */

        /*DayOfWeekChallenge.printDayOfTheWeek(0);
        DayOfWeekChallenge.printDayOfTheWeek(6);
        DayOfWeekChallenge.printDayOfTheWeek(7);
        DayOfWeekChallenge.printDayOfTheWeek(-1);*/

        /*NumberInWord.printNumberInWord(0);
        NumberInWord.printNumberInWord(9);
        NumberInWord.printNumberInWord(11);*/

        /*System.out.println(NumbersOfDaysInMonth.isLeapYear(-1600));
        System.out.println(NumbersOfDaysInMonth.isLeapYear(1600));
        System.out.println(NumbersOfDaysInMonth.isLeapYear(2017));
        System.out.println(NumbersOfDaysInMonth.isLeapYear(2000));
        System.out.println(NumbersOfDaysInMonth.isLeapYear(9000));*/

        //Sum3and5Challenge.challenge();

        /*System.out.println(SumOddRange.sumOdd(1,100));
        System.out.println(SumOddRange.sumOdd(-1,100));
        System.out.println(SumOddRange.sumOdd(200,100));
        System.out.println(SumOddRange.sumOdd(13,13));*/

        /*System.out.println(DigitSum.sumDigits(1));
        System.out.println(DigitSum.sumDigits(123));
        System.out.println(DigitSum.sumDigits(321));*/

        //System.out.println(NumberPalindrome.isPalindrome(-1221));

        //System.out.println(FirstLastDigitSum.sumFirstAndLastDigit(321));

        //System.out.println(EvenDigitSum.getEvenDigitSum(123456789));

        /*System.out.println(SharedDigit.hasSharedDigit(12,23));
        System.out.println(SharedDigit.hasSharedDigit(9,99));
        System.out.println(SharedDigit.hasSharedDigit(15,55));*/

        /*System.out.println(GreatestCommonDivisor.getGreatestCommonDivisor(25,15));
        System.out.println(GreatestCommonDivisor.getGreatestCommonDivisor(12,30));
        System.out.println(GreatestCommonDivisor.getGreatestCommonDivisor(1010,10));*/

        /*FactorPrinter.printFactors(6);
        FactorPrinter.printFactors(32);
        FactorPrinter.printFactors(10);*/

        /*System.out.println(PerfectNumber.isPerfectNumber(6));
        System.out.println(PerfectNumber.isPerfectNumber(28));
        System.out.println(PerfectNumber.isPerfectNumber(5));*/

        /*NumberToWords.numberToWords(123321);
        System.out.println(NumberToWords.reverse(123321));
        System.out.println(NumberToWords.reverse(-123));
        System.out.println(NumberToWords.getDigitCount(0));
        System.out.println(NumberToWords.getDigitCount(123));
        System.out.println(NumberToWords.getDigitCount(-12));
        System.out.println(NumberToWords.getDigitCount(5200));
        System.out.println(NumberToWords.getDigitCount(101));*/

        /*String number = "123.0";
        double dnum = 1;
        number = number + dnum; // = 123.01.0
        double n = Double.parseDouble(number); // -> error
        System.out.println(n);*/

        //System.out.println(FlourPacker.canPack(1,0,4));
        //System.out.println(FlourPacker.canPack(1,0,5));
        //System.out.println(FlourPacker.canPack(0,5,4));
        //System.out.println(FlourPacker.canPack(2,2,11));

        //System.out.println(LargestPrime.getLargestPrime(16));
        //DiagonalStar.printSquareStar(8);

        /*******************************************
         * User Input Lesson
         */
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your birthyear: ");
        boolean hasNextInt = scanner.hasNextInt();
        if (hasNextInt) {
            int birthYear = scanner.nextInt();
            scanner.nextLine(); // handle next line character (enter)
            int currentYear = Integer.parseInt(new SimpleDateFormat("YYYY").format(new Date(System.currentTimeMillis())));

            int age = (currentYear - birthYear);
            System.out.println("Enter your name: ");
            String name = scanner.nextLine();

            if (age >= 0 && age <= 100) {
                System.out.println("Hi " + name + ", you " + age + " years old fart!");
            } else {
                System.out.println("Wrong!!!");
            }
        } else {
            System.out.println("Please enter only integers");
        }
        scanner.close();*/

        /**********
         * Reading User Input Challenge
         */
        //ReadingUserInputChallenge.readInput();

        /**********
         * MinMax Challenge
         */
        //MinMaxChallenge.minMax();

        /**********
         * Coding exercises 27/28
         */
        //InputCalculator.inputThenPrintSumAndAverage();
        System.out.println(PaintJob.getBucketCount(-3.4,2.1,1.5,2));
        System.out.println(PaintJob.getBucketCount(3.4,2.1,1.5,2));


    }
}
