package com.pao.project.etapa1.model;

import java.util.Objects;

public class Pet {
    private final String id;
    private String name;
    private String specie;
    private Owner owner;

    public Pet(String id, String name, String specie, Owner owner){
        this.id = id;
        this.name = name;
        this.specie = specie;
        this.owner = owner;
    }

    public String getPetId(){return id;}
    public String getPetName(){return name;}
    public String getSpecie() { return specie; }
    public Owner getOwner() { return owner; }

    public void setName(String name) { this.name = name; }
    public void setSpecie(String specie) { this.specie = specie; }
    public void setOwner(Owner owner) { this.owner = owner; }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        String ownerName = "None";
        if (owner != null) {
            ownerName = owner.getFirstName() + " " + owner.getLastName();
        }
        return String.format("Pet [ID=%s, Name=%s, Specie=%s, Owner=%s]", id, name, specie, ownerName);
    }


}

















