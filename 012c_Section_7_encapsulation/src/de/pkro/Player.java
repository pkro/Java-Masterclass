package de.pkro;

public class Player {
    public String name;
    public int health;
    public String weapon;

    public void loseHealth(int damage) {
        health -= damage;
        if(health <= 0) {
            System.out.println("Placer knocked out");
        }
    }

    public int healthRemaining() {
        return this.health;
    }
}
