package com.pao.laboratory06.exercise3;

public abstract class Person {

    String lastName;
    String firstName;
    String phone = null;

    public Person(String lastName, String firstName, String phone){
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
    }


}
