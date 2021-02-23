package de.pkro;

public class NumberInWord {
    public static final String NUM_NAMES[] = {"ZERO", "ONE","TWO","THREE","FOUR","FIVE","SIX","SEVEN","EIGHT","NINE"};
    public static void printNumberInWord(int num) {
        if(num>=0 && num <NUM_NAMES.length) {
            System.out.println(NUM_NAMES[num]);
            return;
        }
        System.out.println("other");
    }
}
