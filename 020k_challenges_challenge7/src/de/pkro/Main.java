// This is the videos solution which is not great

package de.pkro;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

  public static void main(String[] args) {

    BankAccount account1 = new BankAccount("12345-678", 500.00);
    BankAccount account2 = new BankAccount("98765-432", 1000.00);

    Thread t1 = new Thread(new Transfer(account1, account2, 10.00), "Transfer1");
    Thread t2 = new Thread(new Transfer(account2, account1, 55.88), "Transfer2");

    ExecutorService executorService = Executors.newFixedThreadPool(2);
    executorService.execute(t1);
    executorService.execute(t2);
    executorService.shutdown();

    try {
      executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
      //
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println(account1.getBalance());
    System.out.println(account2.getBalance());
  }
}

class BankAccount {
  private double balance;
  private String accountNumber;
  private Lock lock = new ReentrantLock();

  BankAccount(String accountNumber, double balance) {
    this.accountNumber = accountNumber;
    this.balance = balance;
  }

  public double getBalance() {
    return balance;
  }

  public boolean withdraw(double amount) {

    if (lock.tryLock()) {
      try {
        try {
          // Simulate database access
          Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        balance -= amount;
        System.out.printf("%s: Withdrew %f\n", Thread.currentThread().getName(), amount);
        return true;
      } finally {
        lock.unlock();
      }
    }

    return false;
  }

  public boolean deposit(double amount) {

    if (lock.tryLock()) {
      try {
        try {
          // Simulate database access
          Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        balance += amount;
        System.out.printf("%s: Deposited %f\n", Thread.currentThread().getName(), amount);
        return true;
      } finally {
        lock.unlock();
      }
    }

    return false;
  }

  public boolean transfer(BankAccount destinationAccount, double amount) {
    if (withdraw(amount)) {
      if (destinationAccount.deposit(amount)) {
        return true;
      } else {
        // The deposit failed. Refund the money back into the account.
        System.out.printf(
            "%s: Destination account busy. Refunding money\n", Thread.currentThread().getName());
        deposit(amount);
      }
    }

    return false;
  }
}

class Transfer implements Runnable {
  private BankAccount sourceAccount, destinationAccount;
  private double amount;

  Transfer(BankAccount sourceAccount, BankAccount destinationAccount, double amount) {
    this.sourceAccount = sourceAccount;
    this.destinationAccount = destinationAccount;
    this.amount = amount;
  }

  public void run() {
    while (!sourceAccount.transfer(destinationAccount, amount)) continue;
    System.out.printf("%s completed\n", Thread.currentThread().getName());
  }
}
