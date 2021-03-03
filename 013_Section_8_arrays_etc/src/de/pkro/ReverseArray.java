package de.pkro;

import java.util.Arrays;

public class ReverseArray {
    private static void reverse(int[] arr) {
        System.out.println("Array = " + Arrays.toString(arr));

        for(int i=0; i<arr.length/2; i++) {
            int tmp = arr[i];
            arr[i] = arr[arr.length-1-i];
            arr[arr.length-1-i] = tmp;
        }

        System.out.println("Reversed array = " + Arrays.toString(arr));
    }

    public static void main() {
        int[] x = {1,2,3,4,5};
        reverse(x);
    }
}
