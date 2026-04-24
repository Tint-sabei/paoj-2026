package com.pao.project.etapa1.model;

public class StandardService extends MedicalService{

    public StandardService(ServiceType service, double price){
        super(service, price);
    }

    @Override
    public double getPrice(){
        return price;
    }
}
