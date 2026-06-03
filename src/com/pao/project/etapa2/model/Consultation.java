package com.pao.project.etapa2.model;

import java.time.LocalDateTime;

public final class Consultation {

    private long id;
    private final long appointmentId;
    private final Pet pet;
    private final String diagnosis;
    private final double price;
    private final LocalDateTime dateTime;

    public Consultation(long appointmentId, Pet pet, String diagnosis, double price){
        this.appointmentId = appointmentId;
        this.pet = pet;
        this.diagnosis = diagnosis;
        this.price = price;
        this.dateTime = LocalDateTime.now();
    }

    public long getId() { return id; }
    public long getAppointmentId() { return appointmentId; }
    public Pet getPet(){return pet;}
    public String getDiagnosis(){return diagnosis;}
    public double getPrice(){return price;}
    public LocalDateTime getDateTime(){return dateTime;}

    public void setId(long id) { this.id = id; }

    @Override
    public String toString() {
        return String.format("Consultation [ID=%d, Appointment ID=%d, Pet: %s, Diagnosis: %s, Total: %.2f, Date: %s]",
                id, appointmentId, pet.getPetName(), diagnosis, price,
                dateTime.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }



}
