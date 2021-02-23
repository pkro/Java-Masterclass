package de.pkro;

public class FlourPacker {
    public static boolean canPack(int bigCount, int smallCount, int goal) {
        if(bigCount<0||smallCount<0||goal<0) {
            return false;
        }
        while (bigCount > 0 && goal >= 5) {
            if(bigCount > 0) {
                goal -= 5;
                bigCount--;
            }
        }
        while (smallCount > 0 && goal > 0) {
            if(smallCount > 0) {
                goal -= 1;
                smallCount--;
            }
        }
        return goal == 0;

        /*Smarter solution:
        if(bigCount < 0 || smallCount < 0 || goal < 0) {  // #1 validation
            return false;
        }
        if(bigCount*5 + smallCount < goal) { // #2 supply must be greater than demand
            return false;
        }
        return (goal%5 <= smallCount);*/
    }
}
