package com.pao.laboratory09.exercise1;

import java.io.Serializable;
import java.util.Locale;

public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private double amount;
    private String date;
    private String sourceAccount;
    private String destinationAccount;
    private TransactionType type;
    private transient String note;

    public Transaction(int id, double amount, String date, String sourceAccount,
                       String destinationAccount, TransactionType type) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.type = type;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getId() { return id; }
    public String getDate() { return date; }
    public String getNote() { return note; }

    @Override
    public String toString() {
        return String.format(Locale.US, "[%d] %s %s: %.2f RON | %s -> %s",
                id, date, type, amount, sourceAccount, destinationAccount);
    }

}
