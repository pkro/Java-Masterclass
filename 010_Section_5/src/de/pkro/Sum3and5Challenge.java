package de.pkro;

public class Sum3and5Challenge {
    public static void challenge() {
        int sum = 0;
        int cnt = 0;
        for (int i = 1; i <= 1000; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                cnt++;
                sum += i;
                prt(i);
                if (cnt == 5) {
                    break;
                }
            }
        }
        prt(sum);
    }

    public static void prt(int i) {
        System.out.println(i);
    }
}
