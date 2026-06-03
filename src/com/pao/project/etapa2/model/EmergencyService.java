package com.pao.project.etapa2.model;

public class EmergencyService extends MedicalService {

    public EmergencyService(ServiceType service, double price){
        super(service, price);
    }

    @Override
    public double getPrice(){
        return price * 1.5;
    }
}
