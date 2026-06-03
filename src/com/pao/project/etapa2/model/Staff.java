package com.pao.project.etapa2.model;

public class Staff extends Person {

    public Staff(long id, String firstName, String lastName){
        super(id, firstName, lastName);
    }

    @Override
    public String getDescription(){return "Clinic Staff";}
}
