package com.pao.laboratory10.exercise3;

import com.pao.laboratory10.exercise1.Transaction;
import com.pao.laboratory10.exercise1.TransactionType;
import java.time.LocalDate;

public class Transaction2 extends Transaction {
    String sourceAccount;

    public Transaction2(int id, double amount, LocalDate date, TransactionType type, String sourceAccount) {
        super(id, amount, date, type);
        this.sourceAccount = sourceAccount;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    @Override
    public String toString() {
        return super.toString() + " (Acc: " + sourceAccount + ")";
    }
}