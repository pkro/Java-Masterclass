package de.pkro;

import java.util.Scanner;

public class MinMaxChallenge {
    public static void minMax() {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Enter number:");
            if(scanner.hasNextInt()) {
                int input = scanner.nextInt();

                if(input < min) {
                    min = input;
                }
                if(input > max) {
                    max = input;
                }
            } else {
                break;
            }
            scanner.nextLine();
        }
        System.out.println("Min: " + min + "Max: " + max);
    }
}
