package de.pkro;

public class Main {

    public static void main(String[] args) {
        boolean gameOver = true;
        int score = 5000;
        int levelCompleted = 5;
        int bonus = 100;

        int highScore =  calculateScore(gameOver, score, levelCompleted, bonus);
        System.out.println("Your score was " + highScore);

        displayHighScorePosition("Peter", calculateHighScorePosition(1500));
        displayHighScorePosition("Paul", calculateHighScorePosition(900));
        displayHighScorePosition("Mary", calculateHighScorePosition(400));
        displayHighScorePosition("Jack", calculateHighScorePosition(40));

    }

    public static int calculateScore(boolean gameOver, int score, int levelCompleted, int bonus) {
        if (gameOver) {
            int finalScore = score + (levelCompleted * bonus);
            return finalScore;
        }
        return -1;
    }

    public static void displayHighScorePosition(String name, int position) {
        System.out.println("Player " + name + " managed to get into position " + position + " on the high score table.");
    }

    public static int calculateHighScorePosition(int score) {
        int position = 4;
        if(score >= 1000) {
            position = 1;
        }
        else if(score >= 500) {
            position = 2;
        }
        else if(score > 100) {
            position = 3;
        }
        return position;
    }
}
