package com.pao.laboratory06.exercise3;

public class Employee extends Person{
    double salary;

    public Employee(String lastName, String firstName, String phone, double salary){
        super(lastName, firstName, phone);
        this.salary = salary;

    }


}
