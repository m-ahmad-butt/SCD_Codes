package org.example.testing;

import java.util.Date;

public class Transaction {
    private final String id;
    private final Date timestamp;
    private final double amount;
    private final int type; // 1 = deposit, -1 = withdrawal

    public Transaction(String id, double amount, int type) {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException("ID required");
        if (amount <= 0.0) throw new IllegalArgumentException("Amount must be positive");
        if (type != 1 && type != -1) throw new IllegalArgumentException("Type must be 1 (deposit) or -1 (withdrawal)");

        this.id = id;
        this.amount = amount;
        this.type = type;
        this.timestamp = new Date(); // set current time automatically
    }

    public double getAmount() {
        return amount;
    }

    public int getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", amount=" + amount +
                ", type=" + (type == 1 ? "Deposit" : "Withdrawal") +
                '}';
    }
}
