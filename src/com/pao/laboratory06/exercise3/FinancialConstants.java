package com.pao.laboratory06.exercise3;

public enum FinancialConstants {
    VAT(0.19), MINIMUM_WAGE(3000.0), TAX_RATE(0.4);

    private final double value;

    FinancialConstants(double value){
        this.value = value;
    }

    public double getValue(){
        return value;
    }


}
