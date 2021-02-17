package de.pkro;

public class BarkingDog {
    public static boolean shouldWakeUp(boolean barking, int hourOfDay) {
        return hourOfDay >= 0 && hourOfDay <= 23 && ((hourOfDay < 8 || hourOfDay > 22) && barking);
    }
}
