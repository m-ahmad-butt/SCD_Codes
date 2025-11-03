package org.example.testing;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private final String accountNumber;
    private double balance;
    private final List<Transaction> transactions = new ArrayList<>();

    public BankAccount(String accountNumber, double initialBalance) {
        if (accountNumber == null || accountNumber.isEmpty()) 
            throw new IllegalArgumentException("Account number required");
        if (initialBalance < 0) 
            throw new IllegalArgumentException("Initial balance cannot be negative");

        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive");
        balance += amount;
        transactions.add(new Transaction("TXN" + (transactions.size() + 1), amount, 1));
    }

    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal amount must be positive");
        if (amount > balance) throw new IllegalArgumentException("Insufficient balance");
        balance -= amount;
        transactions.add(new Transaction("TXN" + (transactions.size() + 1), amount, -1));
    }

    public double getBalance() {
        return balance;
    }

    public void setBln(double am){
        balance +=am;
    }

    public double totalDeposits() {
        return transactions.stream()
                .filter(t -> t.getType() == 1)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double totalWithdrawals() {
        return transactions.stream()
                .filter(t -> t.getType() == -1)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions); // return copy for safety
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", transactions=" + transactions +
                '}';
    }
}
