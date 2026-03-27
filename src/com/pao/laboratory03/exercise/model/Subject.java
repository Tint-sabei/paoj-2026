package com.pao.laboratory03.exercise.model;

public enum Subject {
    PAOJ("Advanced Object-Oriented Programming", 6),
    BD("Databases", 5),
    SO("Operating Systems", 4),
    RC("Computer Networks", 5);

    private final String fullName;
    private final int credits;

    Subject(String fullName, int credits) {
        this.fullName = fullName;
        this.credits = credits;
    }

    @Override
    public String toString(){
        return name() + " (" + fullName + ", " + credits + " credits)";

    }
}