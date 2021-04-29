package de.pkro;

public class Main {

  public static void main(String[] args) {
    // Anonymous class
    new Thread(new Runnable() {
      @Override
      public void run() {
        // this is the only non-boilerplate code
        System.out.println("printing from the Runnable anon");
      }
    }).start();

    // as a Lambda expression:
    new Thread(() -> System.out.println("Printing from lambda runnable")).start();

    // Multi line lambda (shouldn't really be done imho

    new Thread(() -> {
      System.out.println("Printing from multiline lambda runnable");
      System.out.println("Printing more from multiline  lambda runnable");
    }).start();
  }
}

class CodeToRun implements Runnable {
  @Override
  public void run() {
    System.out.println("Printing from runnable");
  }
}
