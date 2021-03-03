package de.pkro;

import java.util.Scanner;

public class MinimumElement {

    /*public static void main() {
        System.out.println("how many");
        int amount = readInteger();
        System.out.println("Please enter " + amount + " numbers");
        int[] arr = readElements(amount);
        int min = findMin(arr);
        System.out.println("Min is " + min);
    }*/

    private static Scanner scanner = new Scanner(System.in);

    private static int readInteger() {
        int myInt = scanner.nextInt();
        scanner.nextLine();
        return myInt;
    }

    private static int[] readElements(int amount) {
        int[] arr = new int[amount];
        for(int i=0; i<arr.length; i++) {
            arr[i] = readInteger();
        }
        return arr;
    }

    private static int findMin(int[] arr) {
        int min = arr[0];
        for(int i=1; i<arr.length; i++) {
            if(arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }
}
