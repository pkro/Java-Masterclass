package de.pkro;

public class PlayingCat {
    public static boolean isCatPlaying(boolean summer, int temperature) {
        return temperature >= 25 && ((!summer && temperature <= 35) || (summer && temperature <= 45));
    }
}
