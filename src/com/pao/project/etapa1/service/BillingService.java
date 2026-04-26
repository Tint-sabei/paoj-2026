package com.pao.project.etapa1.service;

import java.util.*;
import com.pao.project.etapa1.model.*;
import java.util.stream.Collectors;

public class BillingService {

    private final List<Consultation> consultations = new ArrayList<>();

    private BillingService(){};

    private static class Holder {
        private static final BillingService instance = new BillingService();
    }

    public static BillingService getInstance(){
        return Holder.instance;
    }


    // add
    public void addConsultation(Consultation c){
        consultations.add(c);
        System.out.println("Record added for Pet: " + c.getPetId());
    }


    // find
    public List<Consultation> findByPetId(Pet pet){
        return consultations.stream().filter(c -> c.getPetId().getPetId().equalsIgnoreCase(pet.getPetId())).collect(Collectors.toList());
    }

    // list all
    public List<Consultation> getAll(){
        return new ArrayList<>(consultations);
    }

    // delete
    public void deleteByPetId(String petId) {
        int initialSize = consultations.size();

        consultations.removeIf(c -> c.getPetId().getPetId().equalsIgnoreCase(petId));

        int removedCount = initialSize - consultations.size();
        System.out.println("Removed " + removedCount + " billing records.");
    }

    // sort by price
    public List<Consultation> getConsultationsSortedByPrice(){
        List<Consultation> sortedConsultations = new ArrayList<>(consultations);
        sortedConsultations.sort((c1, c2) -> Double.compare(c2.getPrice(), c1.getPrice()));
        return sortedConsultations;
    }

}
