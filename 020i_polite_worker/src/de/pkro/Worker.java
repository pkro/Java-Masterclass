package de.pkro;

public class Worker {
  private String name;
  private boolean active;

  public Worker(String name, boolean active) {
    this.name = name;
    this.active = active;
  }

  public String getName() {
    return name;
  }

  public boolean isActive() {
    return active;
  }

  public synchronized void work(SharedRessource sharedRessource, Worker otherWorker) {
    while (active) {
      // waits until it owns the ressource
      if (sharedRessource.getOwner() != this) {
        try {
          wait(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        continue;
      }

      // if the other thread is active, "politely" give it the shared ressource
      if (otherWorker.isActive()) {
        System.out.println(
            getName() + " : give the ressource to the worker " + otherWorker.getName());
        sharedRessource.setOwner(otherWorker);
        continue;
      }

      System.out.println(getName() + " working on the common ressource");
      active = false;
      sharedRessource.setOwner(otherWorker);
    }
  }
}
