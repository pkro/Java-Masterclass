package de.pkro;

public class HealthyBurger extends Hamburger {
    private String healthyExtra1Name;
    private double healthyExtra1Price;
    private String healthyExtra2Name;
    private double healthyExtra2Price;

    public HealthyBurger(String meat, double price) {
        super("Healthyburger", meat, price, "Brown rye");
    }

    public void addHealthyAddition1(String name, double price) {
        healthyExtra1Name = name;
        healthyExtra1Price = price;
    }

    public void addHealthyAddition2(String name, double price) {
        healthyExtra2Name = name;
        healthyExtra2Price = price;
    }

    @Override
    public double itemizeHamburger() {
        double finalPrice = super.itemizeHamburger();
        if (healthyExtra1Name != null) {
            System.out.println("Added " + healthyExtra1Name + " for an extra " + healthyExtra1Price);
            finalPrice += healthyExtra1Price;
        }
        if (healthyExtra2Name != null) {
            System.out.println("Added " + healthyExtra2Name + " for an extra " + healthyExtra2Price);
            finalPrice += healthyExtra2Price;
        }


        return finalPrice;
    }
}
