package com.pao.laboratory06.exercise2;
import java.util.Scanner;

public class CIMColaborator extends Colaborator implements Individual{

    public Boolean hasBonus = false;

    @Override
    public void read(Scanner in) {
        super.read(in);

        if (in.hasNext()){
            String bonus = in.next();
            this.hasBonus = bonus.equalsIgnoreCase("DA");
            }
        else {
            this.hasBonus = false;
        }

    }

    @Override
    public double calculateAnnualNetIncome(){
        double netIncome = grossMonthlyIncome * 12 * 0.55;
        if (hasBonus){
            netIncome = netIncome * 1.1;}
        return netIncome;

    }
    @Override
    public void display(){
        System.out.printf("CIM: %s %s, venit net anual: %.2f lei\n", lastName, firstName, calculateAnnualNetIncome());
    }


    public ColaboratorType getType(){
        return ColaboratorType.CIM;
    }


}


