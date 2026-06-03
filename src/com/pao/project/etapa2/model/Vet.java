package com.pao.project.etapa2.model;

public class Vet extends Staff {
    public Vet(long id, String firstName, String lastName){
        super(id, firstName, lastName);
    }

    @Override
    public String getDescription(){return "Veterinary Doctor";}
}

