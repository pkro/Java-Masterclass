package de.pkro;

import java.util.Scanner;

public class ReadingUserInputChallenge {
    public static void readInput() {
        int sum = 0;
        int validNumbers = 1;
        Scanner scanner = new Scanner(System.in);
        while (validNumbers <= 10) {
            System.out.println("Please enter number #" + validNumbers + ":");
            if(scanner.hasNextInt()) {
                int num = scanner.nextInt();
                sum = sum + num;
                validNumbers++;
            } else {
                System.out.println("Please enter only numbers.");
            }
            scanner.nextLine();


        }
        System.out.println(sum);
        scanner.close();
    }
}
