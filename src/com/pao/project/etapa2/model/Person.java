package com.pao.project.etapa2.model;

import java.util.Objects;

public abstract class Person {
    private long id;
    private String firstName;
    private String lastName;

    public Person(long id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId(){return id;}
    public void setId(long id) { this.id = id; }

    public String getFirstName(){return firstName;}
    public void setFirstName(String firstName){this.firstName = firstName;}

    public String getLastName(){return lastName;}
    public void setLastName(String lastName){this.lastName = lastName;}

    public abstract String getDescription();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}
