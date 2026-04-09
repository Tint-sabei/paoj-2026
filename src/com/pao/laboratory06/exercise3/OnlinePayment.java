package com.pao.laboratory06.exercise3;

public interface OnlinePayment {
    void authenticate(String user, String password);
    double checkBalance();
    boolean makePayment(double amount);
}
