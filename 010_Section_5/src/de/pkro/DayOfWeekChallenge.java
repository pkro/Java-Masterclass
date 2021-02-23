package de.pkro;



import java.lang.reflect.Array;
import java.text.DateFormatSymbols;
import java.time.DayOfWeek;
import java.util.Arrays;

public class DayOfWeekChallenge {
    private static String[] javaDays = DateFormatSymbols.getInstance().getWeekdays();


    public static void printDayOfTheWeek(int day) {
        //String[] days = Arrays.stream(javaDays).skip(1).toArray(String[]::new);
        //System.arraycopy(javaDays,1, days, 7);
        String days[] = new String[7];
        int runner = 0;
        for(int i=2; i<javaDays.length-2; i++) {
            days[runner] = javaDays[i];
            runner++;
        }
        days[6] = javaDays[1];

        try {
            System.out.println(days[day]);
        } catch (Exception e) {
            System.out.println("Invalid Day");
        }

        /*switch(day) {
            case 0:
                System.out.println("Monday");
                break;
            case 1:
                System.out.println("Tuesday");
                break;
            case 2:
                System.out.println("Wednesday");
                break;
            case 3:
                System.out.println("Thursday");
                break;
            case 4:
                System.out.println("Friday");
                break;
            case 5:
                System.out.println("Saturday");
                break;
            case 6:
                System.out.println("Sunday");
                break;
            default:
                System.out.println("Invalid Day");
                break;
        }*/
    }
}
