package com.pao.laboratory06.exercise3;
import java.util.*;

public class Engineer extends Employee implements OnlinePaymentSMS, Comparable<Engineer> {
    private List<String> messages = new ArrayList<>();

    public Engineer(String lastName, String firstName, String phone, double salary) {
        super(lastName, firstName, phone, salary);
    }

    @Override
    public boolean sendSMS(String message) {
        if (message == null || message.isEmpty()) return false;
        if (this.phone == null || this.phone.isEmpty()) return false;

        messages.add(message);
        return true;
    }

    @Override
    public void authenticate(String user, String password) {
        if (user == null || user.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("User or password cannot be null/empty.");
        }
    }

    @Override
    public double checkBalance() { return this.salary; }

    @Override
    public boolean makePayment(double amount) {
        if (amount <= salary) {
            salary -= amount;
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Engineer other) {
        return this.lastName.compareTo(other.lastName);
    }

    @Override
    public String toString() {
        return "Engineer{" + lastName + ", Salary: " + salary + "}";
    }
}