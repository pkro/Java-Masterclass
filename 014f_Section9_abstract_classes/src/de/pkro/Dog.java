package de.pkro;

public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println("Dog "+getName()+" eating");
    }

    @Override
    public void breathe() {
        System.out.println("Dog "+getName()+" breathing");
    }
}
