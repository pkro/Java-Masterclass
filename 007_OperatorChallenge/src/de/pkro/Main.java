package de.pkro;

public class Main {

    public static void main(String[] args) {
        double a = 20.00d;
        double b = 80.00d;
        double ab = (a + b) * 100;
        double remainder = ab % 40.0d;

        System.out.println(remainder); // 0.0

        boolean isZero = remainder == 0.0d;

        System.out.println(isZero); // true

        if(!isZero) {
            System.out.println("It's not zero!");
        }
        int base = 5;

        int postfix = base++;
        System.out.println(postfix); // 5
        System.out.println(base); // 6
        base = 5;
        int prefix = ++base;
        System.out.println(prefix); // 6
        System.out.println(base); // 6


    }
}
