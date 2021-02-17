package de.pkro;

public class MegaBytesConverter {
    public static void printMegaBytesAndKiloBytes(int kiloBytes) {
        if (kiloBytes < 0) {
            System.out.println("Invalid Value");
        } else {
            int megabytes = kiloBytes / 1024;
            int remainingKb = kiloBytes % 1024;
            String message = kiloBytes + " KB = " + megabytes + " MB and " + remainingKb + " KB";
            System.out.println(message);
        }

    }
}
