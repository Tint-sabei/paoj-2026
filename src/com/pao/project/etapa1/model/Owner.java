package com.pao.project.etapa1.model;

import java.util.*;

public class Owner extends Person {

    public Owner(String id, String firstName, String lastName){
        super(id, firstName, lastName);
    }

    @Override
    public String getDescription(){return "Pet Owner";}
}
















//
//import java.util.*;
//
//
//// should owner extends Pet?
//
//public class Owner {
//    private int id;
//    private String firstName;
//    private String lastName;
//    private String address;
//    private String city;
//    private String telephone;
//
//
//    public Owner(int id, String firstName, String lastName, String address, String city, String telephone){
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.address = address;
//        this.city = city;
//        this.telephone = telephone;
//    }
//
//    public int getId(){return this.id;}
//    public String getFirstName(){return this.firstName;}
//    public String getLastName(){return this.lastName;}
//
//    public String getAddress(){ return this.address;}
//    public void setAddress(String address){this.address = address;}
//
//    public String getCity(){return this.city;}
//    public void setCity(String city){this.city = city; }
//
//    public String getTelephone(){return this.telephone;}
//    public void setTelephone(String telephone){this.telephone = telephone;}
//
//
//    // pet
//    private final List<Pet> pets = new ArrayList<>();
//
//    // methods
//    public void addPet(Pet pet){
//        if (pet.isNew()){
//            pets.add(pet);
//        }
//    }
//
////    public Pet getPet(String petName){return getPet(petName);}
//
//    public Pet getPet(String petId){
//        for (Pet pet : pets) {
//            if(!pet.isNew()){
//                String id = pet.getId();
//                if (id.equals(petId)) {
//                    return pet;
//                }
//            }
//        }
//        return null;
//    }
//
//    public Pet getPet(String petName, boolean ignoreNew){
//        for (Pet pet : pets){
//            String pName = pet.getPetName();
//            if (petName != null && petName.equalsIgnoreCase(pName)){
//                if (!ignoreNew || !pet.isNew()){
//                    return pet;
//                }
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public String toString(){return "Sth" + "Sth";} // learn different way to print output
//
//
//}
