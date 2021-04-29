package de.pkro;

public class Main {
  public static Object lock1 = new Object();
  public static Object lock2 = new Object();

  public static void main(String[] args) {
    new Thread1().start();
    new Thread2().start();
  }

  private static class Thread1 extends Thread {
    @Override
    public void run() {
      synchronized (lock1) {
        System.out.println("Thread 1 has lock1");
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("Thread 1: waiting for lock 2");
        synchronized (lock2) {
          System.out.println("Thread 1: Has lock1 and lock2");
        }
        System.out.println("Thread 1: released lock2");
      }
      System.out.println("Thread 1: Released lock1. Exiting...");
    }
  }

  // this thread acquires the locks in the same order (outer and inner lock)
  // as thread 1 so it terminates
  private static class Thread2 extends Thread {
    @Override
    public void run() {
      synchronized (lock1) {
        System.out.println("Thread 2 has lock1");
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("Thread 2: waiting for lock 2");
        synchronized (lock2) {
          System.out.println("Thread 2: Has lock1 and lock2");
        }
        System.out.println("Thread 2: released lock2");
      }
      System.out.println("Thread 2: Released lock1. Exiting...");
    }
  }

  // this leads to lock with thread1
  private static class Thread2Locking extends Thread {
    @Override
    public void run() {
      synchronized (lock2) {
        System.out.println("Thread 2 has lock2");
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("Thread 2: waiting for lock 1");
        synchronized (lock1) {
          System.out.println("Thread 2: Has lock2 and lock1");
        }
        System.out.println("Thread 2: released lock1");
      }
      System.out.println("Thread 2: Released lock2. Exiting...");
    }
  }
}
