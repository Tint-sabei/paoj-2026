package com.pao.project.etapa2.model;

import java.util.Objects;

public class Pet {
    private long id;
    private String name;
    private String specie;
    private Owner owner;

    public Pet(long id, String name, String specie, Owner owner){
        this.id = id;
        this.name = name;
        this.specie = specie;
        this.owner = owner;
    }

    public long getId() { return id; }
    public String getPetName(){return name;}
    public String getSpecie() { return specie; }
    public Owner getOwner() { return owner; }

    public void setId(long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setSpecie(String specie) { this.specie = specie; }
    public void setOwner(Owner owner) { this.owner = owner; }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return id == pet.id;
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
        return String.format("Pet [ID=%d, Name=%s, Specie=%s, Owner=%s]", id, name, specie, ownerName);
    }


}

















