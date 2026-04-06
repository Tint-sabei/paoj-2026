package com.pao.laboratory06.exercise2;
import java.util.Scanner;

public class SRLColaborator extends Colaborator implements LegalEntity{

    private double monthlyExpenses;

    @Override
    public void read(Scanner in){
        super.read(in);
        this.monthlyExpenses  = in.nextDouble();
    }
    @Override
    public double calculateAnnualNetIncome () {
        return (grossMonthlyIncome - monthlyExpenses) * 12 * 0.84; // only 16% profit tax applies
    }

    @Override
    public void display(){
        System.out.printf("SRL: %s %s, venit net anual: %.2f lei\n", lastName, firstName, calculateAnnualNetIncome());
    }

    public ColaboratorType getType(){
        return ColaboratorType.SRL;
    };


}
