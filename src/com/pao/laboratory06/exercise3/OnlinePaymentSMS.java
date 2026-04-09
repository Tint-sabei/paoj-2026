package com.pao.laboratory06.exercise3;

public interface OnlinePaymentSMS extends OnlinePayment {
    boolean sendSMS(String message);

}
