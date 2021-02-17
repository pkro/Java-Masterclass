package de.pkro;

public class LeapYear {
    public static boolean isLeapYear(int year) {
        if(year < 4 || year >= 10000) {
            return false;
        }
        if(year % 4 != 0) {
            return false;
        }
        if(year % 100 !=0) {
            return true;
        }
        if(year % 400 ==0) {
            return true;
        }
        return false;
    }
}
