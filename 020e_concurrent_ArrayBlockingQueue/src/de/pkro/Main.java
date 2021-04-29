package de.pkro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
  public static final String EOF = "EOF";

  public static void main(String[] args) {
    // updating code from 020d to use ArrayBlockingQuere (thread safe)
    // FIFO queue
    ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<>(6);

    MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_YELLOW);
    MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE);
    MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_CYAN);

    ExecutorService executorService = Executors.newFixedThreadPool(5);

    executorService.execute(producer);
    executorService.execute(consumer1);
    executorService.execute(consumer2);

    Future<String> future =
        executorService.submit(
            new Callable<String>() {
              @Override
              public String call() throws Exception {
                System.out.println(
                    ThreadColor.ANSI_WHITE + "I'm being printed from the callable class");
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
  private ArrayBlockingQueue<String> buffer;
  private String color;

  public MyProducer(ArrayBlockingQueue<String> buffer, String color) {
    this.buffer = buffer;
    this.color = color;
  }

  @Override
  public void run() {
    Random random = new Random();
    String[] nums = {"1", "2", "3", "4", "5"};
    for (String num : nums) {
      try {
        System.out.println(color + "Adding..." + num);
        buffer.put(num);
        Thread.sleep(random.nextInt(1000));
      } catch (InterruptedException e) {
        System.out.println("Producer was interrupted");
      }
    }
    System.out.println(color + "Adding EOF and exiting");
    try { // can still be interrupted during put call
      buffer.put(Main.EOF);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

class MyConsumer implements Runnable {
  private ArrayBlockingQueue<String> buffer;
  private String color;

  public MyConsumer(ArrayBlockingQueue<String> buffer, String color) {
    this.buffer = buffer;
    this.color = color;
  }

  @Override
  public void run() {
    while (true) {
      synchronized (buffer) { // avoid null pointer exception mentioned in comment below
        try {
          if (buffer.isEmpty()) {
            continue;
          }
          // a null pointer exception can still happen at this point as
          // there might be nothing to peek() available anymore because
          // another thread took the last item between isEmpty() and peek()
          if (buffer.peek().equals(Main.EOF)) {
            System.out.println(color + "Exiting");
            break;
          } else {
            // take() blocks automatically
            System.out.println(color + "Removed " + buffer.take());
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
