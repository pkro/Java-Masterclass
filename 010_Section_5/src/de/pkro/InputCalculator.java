package de.pkro;

import java.util.Scanner;

public class InputCalculator {
    public static void inputThenPrintSumAndAverage() {
        int sum = 0;
        int numInputs = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextInt()) {
                int val = scanner.nextInt();
                numInputs++;
                sum = sum + val;
            } else {
                break;
            }
            scanner.nextLine();
        }

        System.out.println("SUM = " + sum + " AVG = "+Math.round((double) sum/numInputs));
        scanner.close();
    }
}
