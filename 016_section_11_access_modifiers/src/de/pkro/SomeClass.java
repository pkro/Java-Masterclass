package de.pkro;

public class SomeClass {
    public final int instanceNumber;
    private static int classCounter = 0;
    private final String name;

    public SomeClass(String name) {
        this.name = name;
        classCounter++;
        instanceNumber = classCounter; // computed final variable
        System.out.println(name + " created, instance is " + instanceNumber);
    }

}
