package de.pkro;

public class Main {
    public static void main(String[] args) {
        byte challengeByte = 121;
        short challengeShort = 20_000;
        int challengeInt = 200_000;
        // no casting needed as right side in parenthesis is assumed to be int, which fits in short
        long challengeLong = 50_000L + 10 * (challengeByte + challengeShort + challengeInt);
        System.out.println("Challenge result: " + challengeLong);
        // casting needed as range of int is bigger than that of short (not sure if this is a good explanation)
        short challengeShortSum = (short) (1000 + 10 * (challengeByte + challengeShort + challengeInt));

    }
}
