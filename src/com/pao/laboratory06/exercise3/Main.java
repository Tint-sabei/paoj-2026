package com.pao.laboratory06.exercise3;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Engineer[] engineers = new Engineer[2];
        engineers[0] = new Engineer("Sabei", "Tint", "0711111111", 5000);
        engineers[1] = new Engineer("Soo", "Jenna", "0722222222", 7000);

        Arrays.sort(engineers);
        System.out.println("Sorted by Name (Natural): " + Arrays.toString(engineers));

        Arrays.sort(engineers, new EngineerSalaryComparator());
        System.out.println("Sorted by Salary (Descending): " + engineers[0].salary);

        OnlinePayment payment = engineers[0];
        payment.authenticate("admin", "1111");
        System.out.println("Balance: " + payment.checkBalance());

        OnlinePaymentSMS payment_sms = new LegalEntity("Institute", "Engineering", "0733333333");
        boolean sent = payment_sms.sendSMS("We have a meeting.");
        System.out.println("SMS: " + sent);

        System.out.println("VAT rate: " + FinancialConstants.VAT.getValue());

        // Error handling
        Engineer noPhoneEngineer = new Engineer("Chi", "Zar", null, 6000);
        System.out.println("SMS sent with no phone: " + noPhoneEngineer.sendSMS("Hello"));

        try {
            engineers[0].authenticate(null, "");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        OnlinePaymentSMS restrictedEntity = new OnlinePaymentSMS() {
            @Override public boolean sendSMS(String message) {
                throw new UnsupportedOperationException("This entity does not have SMS capability.");
            }
            @Override public void authenticate(String user_name, String password_number) {}
            @Override public double checkBalance() { return 0; }
            @Override public boolean makePayment(double a) { return false; }
        };

        try {
            restrictedEntity.sendSMS("Alert");
        } catch (UnsupportedOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}