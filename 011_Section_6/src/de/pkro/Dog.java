package de.pkro;

public class Dog extends Animal {
    private int eyes;
    private int legs;
    private int tail;
    private int teeth;
    private String coat;

    public Dog(String name, int size, int weight, int eyes, int legs, int tail, int teeth, String coat) {
        super(name, 1, 1, size, weight);
        this.eyes = eyes;
        this.legs = legs;
        this.tail = tail;
        this.teeth = teeth;
        this.coat = coat;
    }
    public void bark() {
        System.out.println("Dog is barking");
    }

    public void chew() {
        System.out.println(getName() + " eats!");
    }

    public void walk() {
        System.out.println("Dog is walking calmly");
    }

    public void run() {
        System.out.println("Dog is running happily");
    }
    @Override
    public void eat() {
        super.eat();
        chew();
    }
    public void eat(String what) {
        System.out.println("eating " + what);
        super.eat();
    }
    @Override
    public void move(int speed) {
        super.move(speed);
        if(speed > 10) {
            run();
        } else {
            walk();
        }
    }


}
