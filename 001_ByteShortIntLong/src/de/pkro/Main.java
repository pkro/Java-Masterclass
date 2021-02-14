package de.pkro;

public class Main {
    public static void main(String[] args) {
        int myValue = 10_000; // underscores are permitted for easier readability (java ^7)
        int myMinInt = Integer.MIN_VALUE;
        int myMaxInt = Integer.MAX_VALUE;
        // Compiler error:
        //int mySuperMax = 2147483648;

        System.out.println("int Min: " + myMinInt);
        System.out.println("int Max: " + myMaxInt);
        System.out.println("Busted int max value: " + (myMaxInt + 1));

        byte myMinByte = Byte.MIN_VALUE;
        byte myMaxByte = Byte.MAX_VALUE;
        System.out.println("byte Min: " + myMinByte);
        System.out.println("byte Max: " + myMaxByte);
        System.out.println("byte Busted max value: " + (myMaxByte + 1));

        short myMinShort = Short.MIN_VALUE;
        short myMaxShort = Short.MAX_VALUE;
        System.out.println("short Min: " + myMinShort);
        System.out.println("short Max: " + myMaxShort);
        System.out.println("short Busted max value: " + (myMaxShort + 1));

        long myMinLong = Long.MIN_VALUE;
        long myMaxLong = Long.MAX_VALUE;
        long anotherLong = 100; //ok
        long tooBigWithoutLiteralIndicator = 2147483648L; //only ok with "l" or "L"
        System.out.println("long Min: " + myMinLong);
        System.out.println("long Max: " + myMaxLong);
        System.out.println("long Busted max value: " + (myMaxLong + 1));

        int myTotal = (myMinInt / 2);
        byte myNewByte = (byte) (myMinByte / 2);
        short myNewShort = (short) (myMinShort / 2);

    }
}
