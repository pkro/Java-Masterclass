package de.pkro;

public class MyRunnable implements Runnable {
  @Override
  public void run() {
    System.out.println(ThreadColor.ANSI_RED + "Hello from MyRunnables implementation of run");
  }
}
