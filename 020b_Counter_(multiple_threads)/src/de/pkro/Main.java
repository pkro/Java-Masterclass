package de.pkro;

public class Main {

  public static void main(String[] args) {
    Countdown countdown = new Countdown();

    CountDownThread t1 = new CountDownThread(countdown);
    t1.setName("Thread 1");
    CountDownThread t2 = new CountDownThread(countdown);
    t2.setName("Thread 2");
    t1.start();
    t2.start();
  }
}

class Countdown {
  private int i; // on heap, variable is shared

  public void doCountdown() {
    String color;
    switch (Thread.currentThread().getName()) {
      case "Thread 1":
        color = ThreadColor.ANSI_CYAN;
        break;
      case "Thread 2":
        color = ThreadColor.ANSI_PURPLE;
        break;
      default:
        color = ThreadColor.ANSI_GREEN;
        break;
    }

    synchronized (this) {
      for (i = 10; i > 0; i--) {
        // for (int i = 10; i > 0; i--) { // on stack (local), variable doesn't influence each other
        System.out.println(color + Thread.currentThread().getName() + ": i = " + i);
      }
    }
  }
}

class CountDownThread extends Thread {
  private Countdown threadCountdown;

  public CountDownThread(Countdown countdown) {
    this.threadCountdown = countdown;
  }

  @Override
  public void run() {
    threadCountdown.doCountdown();
  }
}
