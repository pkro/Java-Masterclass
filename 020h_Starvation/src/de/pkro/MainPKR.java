package de.pkro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainPKR {
  private static Object lock = new Object();
  private static String[] colors = {
    ThreadColor.ANSI_RED,
    ThreadColor.ANSI_BLUE,
    ThreadColor.ANSI_GREEN,
    ThreadColor.ANSI_CYAN,
    ThreadColor.ANSI_PURPLE
  };

  public static void main(String[] args) {
    List<Thread> threads = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      int priority = (10 - i * 2);
      threads.add(i, new Thread(new Worker(colors[i]), "Priority " + priority));
      //threads.get(i).setPriority(priority);
    }
    Collections.shuffle(threads);
    for (Thread thread : threads) {
      thread.start();
    }
  }

  private static class Worker implements Runnable {
    private int runCount = 1;
    private String threadColor;

    public Worker(String threadColor) {
      this.threadColor = threadColor;
    }

    @Override
    public void run() {
      for (int i = 0; i < 100; i++) {
        synchronized (lock) {
          try {
            Thread.sleep(10);
          } catch (Exception e) {
            e.printStackTrace();
          }
          System.out.format(
              threadColor + "%s: runCount = %d\n", Thread.currentThread().getName(), runCount++);
        }
        // execute critical section of code
      }
    }
  }
}
