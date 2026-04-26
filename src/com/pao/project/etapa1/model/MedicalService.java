package com.pao.project.etapa1.model;

public abstract class MedicalService {
    protected ServiceType service;
    protected double price;

    public MedicalService(ServiceType service, double price){
        this.service = service;
        this.price = price;
    }

    public ServiceType getService(){return service;}
    public abstract double getPrice();

    @Override
    public String toString(){
        return "Service Type: " + service + ", Total fee: " + getPrice() + ".";
    }
}
