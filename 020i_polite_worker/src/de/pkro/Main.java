package de.pkro;

public class Main {

  public static void main(String[] args) {
    final Worker worker1 = new Worker("Worker 1", true);
    final Worker worker2 = new Worker("Worker 2", true);

    final SharedRessource sharedRessource = new SharedRessource(worker1);

    new Thread(new Runnable() {
      @Override
      public void run() {
        worker1.work(sharedRessource, worker2);
      }
    }).start();

    new Thread(new Runnable() {
      @Override
      public void run() {
        worker2.work(sharedRessource, worker1);
      }
    }).start();
  }
}
