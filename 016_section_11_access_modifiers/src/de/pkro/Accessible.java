package de.pkro;

interface Accessible { // package-private (no "public" specified); only accessible within package
    int SOME_CONSTANT = 100; // public for all classes etc. in package
    public void methodA(); // same as above
    void methodB(); // same as above
    boolean methodC(); // same as above
}
