package com.pao.laboratory05.angajati;

public record Department (String name, String location) implements Comparable<Department>
{
    @Override
    public int compareTo(Department other){
        return this.name.compareToIgnoreCase(other.name);
    }
}


