package de.pkro;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

  public static void main(String[] args) {
    Lock lock = new ReentrantLock(true);
    BankAccount bankAccount = new BankAccount("12345", 1000, lock);

    Thread t1 = new Thread(
        new Runnable() {
          @Override
          public void run() {
            bankAccount.deposit(300.00);
            bankAccount.withdraw(50.00);
          }
        });

    Thread t2 = new Thread(
        new Runnable() {
          @Override
          public void run() {
            bankAccount.deposit(203.75);
            bankAccount.withdraw(100.00);
          }
        });

    t1.start();
    t2.start();
    try {
      Thread.sleep(500);
      //
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(bankAccount.getBalance());
  }
}
