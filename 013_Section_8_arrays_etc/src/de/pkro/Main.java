package de.pkro;


import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /*int[] myVar = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] a = new String[]{"a", "b", "c"};
        String[] b = {"d", "e", "f"};
        String[] days = {"Monday", "Tuesday"};
        String c[];
        System.out.println(days[0]);
        System.out.println(a[1]);

        int[] myIntArray = new int[25];

        fillInt(myIntArray);
        System.out.println(myIntArray[4]);*/


        /*int n = 3;
        System.out.println("Please enter " + n + "values:");
        int[] result = getIntegers(n);
        double avg = calculateAverage(result);
        System.out.println("The average is " + avg);*/
        /*int[] a = {4,3,5,2,6};
        SortedArray.printArray(SortedArray.sortIntegers(a));
        System.out.println(Arrays.toString((new int[5])));*/

        /*System.out.println("how many");
        int amount = readInteger();
        System.out.println("Please enter " + amount + " numbers");
        int[] arr = readElements(amount);
        int min = findMin(arr);
        System.out.println("Min is " + min);*/

        ReverseArray.main();

    }
    private static Scanner scanner = new Scanner(System.in);

    private static int readInteger() {
        return scanner.nextInt();
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

    private static void printIntArray(int[] arr) {
        for (int value : arr) {
            System.out.println(value);
        }
    }
    private static void fillInt(int[] myIntArray) {
        for(int i = 0; i< myIntArray.length; i++) {
            myIntArray[i] = i*10;
        }
    }

    public static int[] getIntegers(int amount) {
        Scanner scanner = new Scanner(System.in);
        int[] values = new int[amount];
        for(int i=0; i<values.length; i++) {
            values[i] = scanner.nextInt();
        }
        return values;
    }

    public static double calculateAverage(int[] numbers) {
        int sum = 0;
        for(int value: numbers) {
            sum += value;
        }

        return (double) sum / numbers.length;
    }
}
