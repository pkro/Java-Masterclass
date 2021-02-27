package de.pkro;

public class EnhancedPlayer {
    private String name;
    private int hitPoints;
    private String weapon;

    public EnhancedPlayer(String name, int hitPoints, String weapon) {
        this.name = name;
        if (hitPoints <= 0 || hitPoints > 200) {
            hitPoints = 100;
        }
        this.hitPoints = hitPoints;
        this.weapon = weapon;
    }

    public void loseHealth(int damage) {
        hitPoints -= damage;
        if (hitPoints <= 0) {
            System.out.println("Placer knocked out");
        }
    }

    public int healthRemaining() {
        return this.hitPoints;
    }
}
