package com.pao.project.etapa1.model;

public class Staff extends Person {

    public Staff(String id, String firstName, String lastName){
        super(id, firstName, lastName);
    }

    @Override
    public String getDescription(){return "Clinic Staff";}
}
