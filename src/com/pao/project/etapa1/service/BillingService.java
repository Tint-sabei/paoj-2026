package com.pao.project.etapa1.service;

import java.util.*;
import com.pao.project.etapa1.model.*;
import java.util.stream.Collectors;

public class BillingService {

    private final List<Consultation> consultations = new ArrayList<>();

//    private final Map<String, List<Consultation>> history = new HashMap<>();

    private BillingService(){};

    private static class Holder {
        private static final BillingService instance = new BillingService();
    }

    public static BillingService getInstance(){
        return Holder.instance;
    }

    // CRUD
    // Create
    public void addConsultation(Consultation c){
        consultations.add(c);
        System.out.println("Record added for Pet: " + c.getPetId());
    }

    // List all
    public List<Consultation> getAll(){
        return new ArrayList<>(consultations);
    }

    // Read
    public List<Consultation> findByPetId(Pet petId){
        return consultations.stream().filter(c -> c.getPetId().equalsIgnoreCase(petId)).collect(Collectors.toList());
    }

    // Delete by Pet ID (to improve later)
    public void deleteByPetId(String petId){
        int initialSize = consultations.size();
        consultations.removeIf(c -> c.getPetId().equalsIgnoreCase(petId);
        System.out.println("Removed " + (initialSize - consultations.size()) + " records.");
    }

    public List<Consultation> getConsultationsSortedByPrice(){
        List<Consultation> sortedConsultations = new ArrayList<>(consultations);
        sortedConsultations.sort((c1, c2) -> Double.compare(c2.getPrice(), c1.getPrice()));
        return sortedConsultations
    }
    // Statistics Actions (later)


}
