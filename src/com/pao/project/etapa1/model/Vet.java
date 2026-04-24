package com.pao.project.etapa1.model;

import java.util.*;

public class Vet extends Staff {
    public Vet(String id, String firstName, String lastName){
        super(id, firstName, lastName);
    }

    @Override
    public String getDescription(){return "Veterinary Doctor";}
}
























//
//public class Vet extends Person {
//
//    private Set<Specialization> specialties;
//
//    protected Set<Specialization> getSpecialtiesInternal() {
//        if (this.specialties == null) {
//            this.specialties = new HashSet<>();
//        }
//        return this.specialties;
//    }
//
//    protected void setSpecialtiesInternal(Set<Specialization> specialties) {
//        this.specialties = specialties;
//    }
//
//    public List<Specialization> getSpecialties() {
//        List<Specialization> sortedSpecs = new ArrayList<>(getSpecialtiesInternal());
//        sortedSpecs.sort(Comparator.comparing(Specialty::getName, String.CASE_INSENSITIVE_ORDER));
//        return Collections.unmodifiableList(sortedSpecs);
//    }
//
//    public int getNrOfSpecialties() {
//        return getSpecialtiesInternal().size();
//    }
//
//    public void addSpecialty(Specialization specialization) {
//        getSpecialtiesInternal().add(specialization);
//    }
//
//    private List<Vet> vetList;
//
//    public List<Vet> getVetList() {
//        if (vetList == null) {
//            vetList = new ArrayList<>();
//        }
//        return vetList;
//    }
//
//
//}
