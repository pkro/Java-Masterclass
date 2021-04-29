package de.pkro;

public class Main {

  public static void main(String[] args) {
    System.out.println(ThreadColor.ANSI_PURPLE + "Hello from the main thread");

    // start a new thread using a subclass of Thread
    Thread anotherThread = new AnotherThread();
    anotherThread.setName("Another thread");
    anotherThread.start(); // Hello from == Another thread
    // anotherThread.run(); // "Hello from the main thread


    // new thread using anonymous Thread subclass
    new Thread() {
      public void run() {
        System.out.println(ThreadColor.ANSI_GREEN + "Hello from the anonymous class thread");
      }
    }.start();

    // new Thread using class implementing the Runnable interface
    new Thread(new MyRunnable()).start();

    // new thread using anonymous Runnable class
    Thread myRunnableThread =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                System.out.println(ThreadColor.ANSI_CYAN + "Hello from anonymous Runnable");
                try {
                  anotherThread.join(1000); // wait max 1 sec for the other thread
                  System.out.println(ThreadColor.ANSI_CYAN + "anotherThread terminated or timed out so I'm running again");
                } catch (InterruptedException e) {
                  System.out.println(ThreadColor.ANSI_CYAN + "I couldn't wait. I was interrupted");
                }
              }
            });

    myRunnableThread.start();

    //anotherThread.interrupt();

    System.out.println(ThreadColor.ANSI_PURPLE + "Hello again from the main thread");
  }
}
