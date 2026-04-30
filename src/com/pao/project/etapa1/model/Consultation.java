package com.pao.project.etapa1.model;

import java.util.*;
import java.time.LocalDateTime; 

public final class Consultation {

    private final Pet pet;
    private final String diagnosis;
    private final double price;
    private final LocalDateTime dateTime;

    public Consultation(Pet pet, String diagnosis, double price){
        this.pet = pet;
        this.diagnosis = diagnosis;
        this.price = price;
        this.dateTime = LocalDateTime.now();
    }

    public Pet getPet(){return pet;}
    public String getDiagnosis(){return diagnosis;}
    public double getPrice(){return price;}
    public LocalDateTime getDateTime(){return dateTime;}


    @Override
    public String toString() {
        return String.format("Consultation [Pet: %s, Diagnosis: %s, Total: %.2f, Date: %s]", pet.getPetName(), diagnosis, price, dateTime.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }



}
