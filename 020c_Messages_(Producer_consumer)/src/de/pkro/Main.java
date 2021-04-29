package de.pkro;

import java.util.Collections;
import java.util.Random;

public class Main {

  public static void main(String[] args) {
    Message message = new Message();
    (new Thread(new Writer(message))).start();
    (new Thread(new Reader(message))).start();
  }
}

class Message {
  private String message;
  private boolean empty = true;

  public synchronized String read() {
    // loop until there is a message to read, used by consumer
    while (empty) {
      try {
        // avoid deadlocks; always call in a loop that checks wakeup
        // condition because thread may wake up for other reasons as well
        // before the condition is satisfied
        wait(); // avoid deadlocks
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    empty = true;
    // notify that method is done (BEFORE return);
    // we don't use notyfy(threadname) because we don't know it
    // (Thread doesn't accept any parameters we could pass such as the thread name)
    notifyAll();
    return message;
  }

  public synchronized void write(String message) {
    // loop until message is empty
    while (!empty) {
      try {
        wait(); // avoid deadlocks
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    empty = false;
    notifyAll();
    this.message = message;
  }
}

// Producer class
class Writer implements Runnable {
  private Message message;

  public Writer(Message message) {
    this.message = message;
  }

  @Override
  public void run() {
    String messages[] = {
      "Humpty dumpty sat on a wall",
      "Humpty dumpty had a great fall",
      "All the king's etc.",
      "Couldn't...",
    };

    Random random = new Random();

    // write 4 messages with 0-2 seconds pause in between
    for (int i = 0; i < messages.length; i++) {
      message.write(messages[i]);
      try {
        Thread.sleep(random.nextInt(2000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    message.write("Finished");
  }
}

class Reader implements Runnable {
  private Message message;

  public Reader(Message message) {
    this.message = message;
  }

  @Override
  public void run() {
    Random random = new Random();
    for (String latestMessage = message.read();
        !latestMessage.equals("Finished");
        latestMessage = message.read()) {
      System.out.println(latestMessage);
      try {
        Thread.sleep(random.nextInt(2000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
