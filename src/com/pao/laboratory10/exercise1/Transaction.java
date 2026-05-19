package com.pao.laboratory10.exercise1;

import java.time.LocalDate;


public class Transaction {

    int id;
    double amount;
    LocalDate date; // (yyyy-MM-dd)
    TransactionType type;

    public Transaction(int id, double amount, LocalDate date, TransactionType type){
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    public int getId(){ return id; }
    public double getAmount() { return amount; }
    public LocalDate getDate() {return date;}
    public TransactionType getType() { return type; }

    @Override
    public String toString() {return String.format(java.util.Locale.US, "[%d] %s %s: %.2f RON", id, date, type, amount);}
}
