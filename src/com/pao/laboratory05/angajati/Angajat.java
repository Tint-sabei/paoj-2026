package com.pao.laboratory05.angajati;


public class Angajat implements Comparable<Angajat> {
    private String name;
    private Department department;
    private double salary;

    // Full constructor
    public Angajat (String name, Department department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Getters
    public String getName () { return name; }
    public Department getDepartment() { return department; }
    public double getSalary() { return salary; }

    @Override
    public int compareTo (Angajat other) {
        // sort by `salary` descending
        return Double.compare(other.salary, this.salary);
    }

    @Override
    public String toString(){
        return "Angajat{nume='" + name + "', department=" + department + ", salary=" + salary + "}";
    }

}
