package com.pao.laboratory06.exercise2;
import java.util.Scanner;

public class CIMColaborator extends Colaborator implements Individual{

    private boolean hasBonus = false;

    @Override
    public boolean hasBonus() {
        return this.hasBonus;
    }

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
            double bonusAmount = netIncome * 0.10;
            netIncome = netIncome + bonusAmount;}
        return netIncome;

    }
    @Override
    public void display(){
        System.out.printf("CIM: %s %s, venit net anual: %.2f lei\n", lastName, firstName, calculateAnnualNetIncome());
    }

    @Override
    public ColaboratorType getType(){
        return ColaboratorType.CIM;
    }


}


