package de.pkro;

public class NumbersOfDaysInMonth {
    public static boolean isLeapYear(int year) {
        return year > 3 && year < 9999 && (year %400== 0 || (year%4==0 && year % 100 !=0));
    }
    public static int getDaysInMonth(int month, int year) {
        if(year<1|| year >9999 || month < 1 || month >12) {
            return -1;
        }
        switch(month) {
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29: 28;
            default:
                return 31;
        }
    }
}
