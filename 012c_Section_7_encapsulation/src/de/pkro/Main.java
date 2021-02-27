package de.pkro;

public class Main {

    public static void main(String[] args) {
        /*Player player = new Player();
        player.health = 100;
        player.name = "Peer";
        player.weapon = "Sausage";*/

/*        EnhancedPlayer player = new EnhancedPlayer("Peer", 300, "sausage");
        player.loseHealth(10);
        System.out.println("Player has now " + player.healthRemaining() + " health");
        player.loseHealth(100);*/

        Printer p = new Printer(50, true);
        System.out.println(p.addToner(50));
        System.out.println("initial page count = " + p.getPagesPrinted());
        int pagesPrinted = p.printPages(4);
        System.out.println("Printed " + pagesPrinted + " pages");
        pagesPrinted = p.printPages(5);
        System.out.println("Printed " + pagesPrinted + " pages");
    }
}
