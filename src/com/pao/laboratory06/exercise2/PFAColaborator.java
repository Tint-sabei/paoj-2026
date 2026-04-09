package com.pao.laboratory06.exercise2;
import java.util.Scanner;

public class PFAColaborator extends Colaborator implements Individual {

    private double monthlyExpenses;


    @Override
    public void read(Scanner in){
        super.read(in);
        this.monthlyExpenses = in.nextDouble();
    }


    @Override
    public double calculateAnnualNetIncome() {
        double minWage = 4050.0;
        double annualMinWage = minWage* 12;
        double netIncome = (grossMonthlyIncome - monthlyExpenses) * 12;
        double incomeTax = netIncome * 0.10;


        double minCass = 6 * annualMinWage;
        double maxCass = 72 * annualMinWage;
        double cass;

        if (netIncome < minCass) {
            cass = 0.10 * minCass;
        } else if (netIncome <= maxCass) {
            cass = 0.10 * netIncome;
        } else {
            cass = 0.10 * maxCass;
        }

        double minCas = 12 * annualMinWage;
        double maxCas = 24 * annualMinWage;
        double cas;


        if (netIncome < minCas) {
            cas = 0;
        } else if (netIncome <= maxCas) {
            cas = 0.25 * minCas;
        } else {
            cas = 0.25 * maxCas;
        }

        return netIncome - incomeTax - cass - cas;
    }

    @Override
    public void display(){
        System.out.printf("PFA: %s %s, venit net anual: %.2f lei\n", lastName, firstName, calculateAnnualNetIncome());
    }

    @Override
    public ColaboratorType getType(){
        return ColaboratorType.PFA;
    }
}
