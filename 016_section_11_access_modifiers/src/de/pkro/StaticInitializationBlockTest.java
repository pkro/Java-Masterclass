package de.pkro;

public class StaticInitializationBlockTest {
    public static final String owner;

    static {
        owner = "Peer";
        System.out.println("Static initialization block called");
    }

    public StaticInitializationBlockTest() {
        System.out.println("Constructor called");
    }

    static {
        System.out.println("Second Static initialization block called");
    }

    public void someMethod() {
        System.out.println("someMethod called");
    }
}
