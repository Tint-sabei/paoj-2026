package com.pao.project.etapa2.model;

public class Owner extends Person {

    public Owner(long id, String firstName, String lastName){
        super(id, firstName, lastName);
    }

    public Owner() {
        super(0, "", "");
    }

    @Override
    public String getDescription(){return "Pet Owner";}
}














