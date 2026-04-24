package com.pao.project.etapa1.model;

public abstract class Person {
    private final String id;
    private String firstName;
    private String lastName;

    public Person(String id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId(){return this.id;};

    public String getFirstName(){return this.firstName;};
    public String setFirstName(String firstName){return this.firstName = firstName;};

    public String getLastName(){return this.lastName};
    public String setLastName(String lastName){return this.lastName = lastName};

    public abstract String getDescription();
}
