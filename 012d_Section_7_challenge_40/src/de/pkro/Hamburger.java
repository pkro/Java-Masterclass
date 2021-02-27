package de.pkro;

public class Hamburger {
    private String name;
    private String breadRollType;
    private String meat;
    private double price;

    private String addition1Name;
    private double addition1Price;
    private String addition2Name;
    private double addition2Price;
    private String addition3Name;
    private double addition3Price;
    private String addition4Name;
    private double addition4Price;

    public Hamburger(String name, String meat, double price, String breadRollType) {
        this.name = name;
        this.breadRollType = breadRollType;
        this.meat = meat;
        this.price = price;
    }

    public void addHamburgerAddition1(String name, double price) {
        addition1Name = name;
        addition1Price = price;
    }

    public void addHamburgerAddition2(String name, double price) {
        addition2Name = name;
        addition2Price = price;
    }

    public void addHamburgerAddition3(String name, double price) {
        addition3Name = name;
        addition3Price = price;
    }

    public void addHamburgerAddition4(String name, double price) {
        addition4Name = name;
        addition4Price = price;
    }

    public double itemizeHamburger() {
        double finalPrice = price;
        if(addition1Name != null) {
            System.out.println("Added " + addition1Name + " for an extra " + addition1Price);
            finalPrice += addition1Price;
        }
        if(addition2Name != null) {
            System.out.println("Added " + addition2Name + " for an extra " + addition2Price);
            finalPrice += addition2Price;
        }
        if(addition3Name != null) {
            System.out.println("Added " + addition3Name + " for an extra " + addition3Price);
            finalPrice += addition3Price;
        }
        if(addition4Name != null) {
            System.out.println("Added " + addition4Name + " for an extra " + addition4Price);
            finalPrice += addition4Price;
        }

        return finalPrice;
    }
}
