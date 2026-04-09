package com.pao.laboratory06.exercise3;
import java.util.*;

public class LegalEntity extends Person implements OnlinePaymentSMS {
    private List<String> messages = new ArrayList<>();

    public LegalEntity(String companyName, String type, String phone) {
        super(companyName, type, phone);
    }

    @Override
    public boolean sendSMS(String message) {
        if (message == null || message.isEmpty() || this.phone == null || this.phone.isEmpty()) {
            return false;
        }
        messages.add(message);
        return true;
    }

    @Override public void authenticate(String user_name, String password_number) { /* logic */ }
    @Override public double checkBalance() { return 10000.0; }
    @Override public boolean makePayment(double payment) { return true; }
}