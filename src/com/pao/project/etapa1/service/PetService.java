package com.pao.project.etapa1.service;

import com.pao.project.etapa1.model.*;
import java.util.*;

public class PetService {

    private final Map<String, Pet> pets = new HashMap<>();

    private PetService(){};

    private static class Holder {
        private static final PetService instance = new PetService();
    }

    public static PetService getInstance(){return Holder.instance;}

    // add
    public void addPet(Pet pet) {
        if (pets.containsKey(pet.getPetId())) {
            System.out.println("ERROR: Pet ID " + pet.getPetId() + " already exists!");
            return;
        }
        pets.put(pet.getPetId(), pet);
        System.out.println("Pet " + pet.getPetName() + " registered successfully.");
    }

    // find by id
    public Pet findPetById(String id){return pets.get(id);}

    // list all
    public List<Pet> getAll() {return new ArrayList<>(pets.values());}

    // delete
    public void deletePet(String id){

        Pet removedPet = pets.remove(id);

        if (removedPet != null){
            System.out.println("Removed pet name: " + removedPet.getPetName());
        } else {
            System.out.println("No pet found with id: " + id);
        }
    }

    // Update
    public void updatePet(String id, String newName, String newSpecie, Owner newOwner) {
        Pet pet = pets.get(id);
        if (pet != null) {
            pet.setName(newName);
            pet.setSpecie(newSpecie);
            pet.setOwner(newOwner);
            System.out.println("Updated pet info for ID: " + id);
        } else {
            System.out.println("Update failed: Pet ID " + id + " not found.");
        }
    }

}
