package com.pao.project.etapa1.model;

import java.util.*;
import java.time.LocalDateTime; 

public final class Consultation {

    private final Pet petId;
    private final String diagnosis;
    private final double price;
    private final LocalDateTime dateTime;

    public Consultation(Pet petId, String diagnosis, double price){
        this.petId = petId;
        this.diagnosis = diagnosis;
        this.price = price;
        this.dateTime = LocalDateTime.now();
    }


    public Pet getPetId(){return petId;}
    public String getDiagnosis(){return diagnosis;}
    public double price(){return price;}
    public LocalDateTime getDateTime(){return dateTime;}

    @Override
    public String toString(){
        return "Consultation for Pet ID: " + petId + ", Diagnosis: " + diagnosis + ", Total fee: " + price + ", Date an Time: " + dateTime + ".";
    }



}
