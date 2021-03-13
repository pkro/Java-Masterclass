package de.pkro;

import java.util.ArrayList;

public class Account {
    private String accountName;
    private int balance;
    private ArrayList<Integer> transactions;

    public Account(String accountName) {
        this.accountName = accountName;
        this.transactions = new ArrayList<>();
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        if(amount < 0) {
            System.out.println("Only positive deposits allowed");
            return;
        }
        transactions.add(amount);
        amount += amount;
        System.out.println(amount + " deposited. Balance: " + balance);
    }

    public void withdraw(int amount) {
        int withdrawal = -amount;
        if(withdrawal > 0) {
            System.out.println("Can't withdraw negative amount");
            return;
        }
        balance += withdrawal;
        transactions.add(withdrawal);
        System.out.println(amount + " withdrawn. Balance: " + balance);

    }

    public void calculateBalance() {
        balance = 0;
        for(int transaction: transactions) {
            balance += transaction;
        }
        System.out.println("Balance: " + balance);

    }
}
