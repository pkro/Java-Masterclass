package de.pkro;

import java.util.Arrays;
import java.util.Scanner;

public class SortedArray {
    public static int[] getIntegers(int amount) {
        Scanner scanner = new Scanner(System.in);
        int[] values = new int[amount];
        for(int i=0; i<values.length; i++) {
            values[i] = scanner.nextInt();
        }
        return values;
    }

    public static int[] sortIntegers(int[] values) {
        int[] myValues = values.clone();

        boolean wasSwitched;
        int tmp;
        do {
            wasSwitched = false;
            for(int i=0; i<myValues.length-1; i++) {
                if(myValues[i] < myValues[i+1]) {
                    tmp = myValues[i];
                    myValues[i] = myValues[i+1];
                    myValues[i+1] = tmp;
                    wasSwitched = true;
                }
            }
        } while(wasSwitched);
        return myValues;

    }

    public static void printArray(int[] arr) {
        for(int i=0; i<arr.length; i++) {
            System.out.println("Element "+i+" contents " + arr[i]);
        }
    }

}
