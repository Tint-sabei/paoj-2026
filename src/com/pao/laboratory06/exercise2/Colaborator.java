package com.pao.laboratory06.exercise2;
import java.util.Scanner;

//1. Create an abstract class `Colaborator` with common fields (last name, first name, gross monthly income)
//and an abstract method `double calculateAnnualNetIncome()`.

public abstract class Colaborator implements IOperationsReadWrite{
    protected String lastName;
    protected String firstName;
    protected double grossMonthlyIncome;

    public abstract double calculateAnnualNetIncome();

    public abstract ColaboratorType getType();

    @Override
    public String contractType(){
        return getType().toString();
    }
    @Override
    public void read(Scanner in) {
        this.lastName = in.next();
        this.firstName = in.next();
        this.grossMonthlyIncome = in.nextDouble();
    }
}
