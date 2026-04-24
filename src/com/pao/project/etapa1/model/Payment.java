package com.pao.project.etapa1.model;

public class Payment {

    private double price;
    private double discount;

    public Payment(double price, double discount){
        this.price = price;
        this.discount = discount;
    }

    public double finalPrice(){
        return 0.0;
    }
}

