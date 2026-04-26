package com.pao.project.etapa1.model;

import java.util.*;

public class Vet extends Staff {
    public Vet(String id, String firstName, String lastName){
        super(id, firstName, lastName);
    }

    @Override
    public String getDescription(){return "Veterinary Doctor";}
}

