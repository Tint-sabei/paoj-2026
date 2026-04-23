package com.pao.laboratory08.exercise1;

public class Student implements Cloneable {
    private String nume;
    private int varsta;
    private Adresa adresa;

    public Student(String nume, int varsta, Adresa adresa) {
        this.nume = nume;
        this.varsta = varsta;
        this.adresa = adresa;
    }

    public String getNume() { return nume; }
    public Adresa getAdresa() { return adresa; }

    @Override
    public String toString() {
        return "Student{nume='" + nume + "', varsta=" + varsta + ", adresa=" + adresa + "}";
    }

    // Part B: Shallow Clone
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Part C: Deep Clone
    public Student deepClone() throws CloneNotSupportedException {
        Student clona = (Student) super.clone();
        clona.adresa = (Adresa) this.adresa.clone();
        return clona;
    }

    public int getVarsta() {
        return varsta;
    }
}