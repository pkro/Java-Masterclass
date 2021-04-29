package de.pkro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
  public static final String EOF = "EOF";

  public static void main(String[] args) {
    // List is unsynchronized (not thread safe)
    // 2 threads could try to remove the same item,
    // resulting in an IndexOutOfBoundsException
    List<String> buffer = new ArrayList<>();
    ReentrantLock bufferLock = new ReentrantLock();


    MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_YELLOW, bufferLock);
    MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE, bufferLock);
    MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_CYAN, bufferLock);

    ExecutorService executorService = Executors.newFixedThreadPool(5);

    executorService.execute(producer);
    executorService.execute(consumer1);
    executorService.execute(consumer2);

    Future<String> future = executorService.submit(new Callable<String>() {
      @Override
      public String call() throws Exception {
        System.out.println(ThreadColor.ANSI_WHITE+ "I'm being printed from the callable class");
        return "this is the callable result";
      }
    });

    try {
      String result = future.get();
      System.out.println(result);
    } catch (ExecutionException | InterruptedException e) {
      e.printStackTrace();
    }

    executorService.shutdown();
    /*new Thread(producer).start();
    new Thread(consumer1).start();
    new Thread(consumer2).start();*/
  }
}

class MyProducer implements Runnable {
  private List<String> buffer;
  private String color;
  private ReentrantLock bufferLock;

  public MyProducer(List<String> buffer, String color, ReentrantLock bufferLock) {
    this.buffer = buffer;
    this.color = color;
    this.bufferLock = bufferLock;

  }

  @Override
  public void run() {
    Random random = new Random();
    String[] nums = {"1", "2", "3", "4", "5"};
    for (String num : nums) {
      try {
        System.out.println(color + "Adding..." + num);
        bufferLock.lock();
        try {
          buffer.add(num);
        } finally {
          bufferLock.unlock();
        }
        Thread.sleep(random.nextInt(1000));
      } catch (InterruptedException e) {
        System.out.println("Producer was interrupted");
      }
    }
    System.out.println(color + "Adding EOF and exiting");
    bufferLock.lock();
    try {
      buffer.add(Main.EOF);
    } finally {
      bufferLock.unlock();
    }
  }
}

class MyConsumer implements Runnable {
  private List<String> buffer;
  private String color;
  private ReentrantLock bufferLock;

  public MyConsumer(List<String> buffer, String color, ReentrantLock bufferLock) {
    this.buffer = buffer;
    this.color = color;
    this.bufferLock = bufferLock;
  }

  @Override
  public void run() {
    int counter = 0;
    while (true) {
      if(bufferLock.tryLock()) {
        try {
          if (buffer.isEmpty()) {
            continue;
          }
          System.out.println(color+"tried to lock " + counter + " times");
          if (buffer.get(0).equals(Main.EOF)) {
            System.out.println(color + "Exiting");
            break;
          } else {
            System.out.println(color + "Removed " + buffer.remove(0));
          }
        } finally {
          bufferLock.unlock();
        }
      } else {
        counter++;
      }
    }
  }
}
