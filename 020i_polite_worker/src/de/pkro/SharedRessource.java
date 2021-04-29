package de.pkro;

public class SharedRessource {
    private Worker owner;

    public SharedRessource(Worker owner) {
        this.owner = owner;
    }

    public Worker getOwner() {
        return owner;
    }

    public synchronized void  setOwner(Worker owner) {
        this.owner = owner;
    }
}
