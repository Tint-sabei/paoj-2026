package com.pao.project.etapa1.model;

public class DiscountedService extends MedicalService{
    public DiscountedService(ServiceType service, double price){
        super(service, price);
    }

    @Override
    public double getPrice(){
        return price * 0.8; // 20 % off
    }
}
