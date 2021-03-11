package de.pkro;

public class Main {

    public static void main(String[] args) {
        Dog dog = new Dog("Jack");
        Bird parrot = new Parrot("sparrow");

        dog.breathe();
        dog.eat();
        parrot.breathe();
        parrot.eat();
        parrot.fly();

        Penguin penguin = new Penguin("Paul");
        penguin.fly();
    }
}
