package de.pkro;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

class BankAccount {

  private double balance;
  private String accountNumber;
  private Lock lock;

  public BankAccount(String accountNumber, double initialBalance, Lock lock) {
    this.accountNumber = accountNumber;
    this.balance = initialBalance;
    this.lock = lock;
  }

  /*public synchronized void deposit(double amount) {
      balance += amount;
  }

  public synchronized void withdraw(double amount) {
      balance -= amount;
  }*/
  /*
  public void deposit(double amount) {
    synchronized (this) {
      balance += amount;
    }
  }

  public void withdraw(double amount) {
    synchronized (this) {
      balance -= amount;
    }
  }*/

  public void deposit(double amount) {
    boolean status = false;

    try {
      if (lock.tryLock(1, TimeUnit.SECONDS)) {
        try {
          balance += amount;
          status = true;
        } finally {
          lock.unlock();
        }
      } else {
        System.out.println("Couldn't get lock");
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("transaction status: "+status);
  }

  public void withdraw(double amount) {
    boolean status = false;
    try {
      if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
        try {
          balance -= amount;
          status = true;
        } finally {
          lock.unlock();
        }
      }
    } catch (InterruptedException e) {
      System.out.println("Couldn't get the lock");
    }
    System.out.println("transaction status: "+status);
  }

  public double getBalance() {
    return balance;
  }

  public void printBalance() {
    System.out.println(balance);
  }

  // doesn't need to be synchronized
  public String getAccountNumber() {
    return accountNumber;
  }

  public void printAccountNumber() {
    System.out.println(accountNumber);
  }
}
