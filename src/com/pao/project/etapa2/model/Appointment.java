package com.pao.project.etapa2.model;

import com.pao.project.etapa2.exceptions.AppointmentIsAlreadyFinalException;
import com.pao.project.etapa2.exceptions.CannotCancelFinalAppointmentException;
import com.pao.project.etapa2.exceptions.CannotRevertInitialAppointmentStateException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Appointment {
    private long id;
    private Pet pet;
    private Vet vet;
    private ServiceType service;
    private State currentState;
    private final List<State> history = new ArrayList<>();
    private LocalDateTime dateTime;
    private String clientReason;
    private String doctorNote;

    public Appointment(long id, Pet pet, Vet vet, ServiceType service, State currentState,
                       LocalDateTime dateTime, String clientReason, String doctorNote){
        this.id = id;
        this.pet = pet;
        this.vet = vet;
        this.service = service;
        this.currentState = currentState;
        this.dateTime = dateTime;
        this.clientReason = clientReason;
        this.doctorNote = doctorNote;
        this.history.add(currentState);
    }

    public Appointment(State initialState) {
        this.currentState = initialState;
        this.history.add(initialState);
    }

    public void nextState() throws AppointmentIsAlreadyFinalException {
        if (currentState.isFinal()) {
            throw new AppointmentIsAlreadyFinalException();
        }
        currentState = currentState.next();
        history.add(currentState);
        System.out.println("State updated to: " + currentState);
    }

    public void cancel() throws CannotCancelFinalAppointmentException {
        if (currentState.isFinal()) {
            throw new CannotCancelFinalAppointmentException();
        }
        currentState = State.CANCELED;
        history.add(currentState);
        System.out.println("State: " + currentState + " has been canceled.");
    }

    public void undoState() throws CannotRevertInitialAppointmentStateException {
        if (history.size() <= 1) {
            throw new CannotRevertInitialAppointmentStateException();
        }

        int lastIndex = history.size() - 1;
        history.remove(lastIndex);

        int previousIndex = history.size() - 1;
        this.currentState = history.get(previousIndex);

        System.out.println("State reverted to: " + currentState);
    }

    public long getId(){return id;}
    public Pet getPet() { return pet; }
    public Vet getVet(){return vet;}
    public ServiceType getService(){
        return service;
    }
    public State getState() { return currentState; }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public String getClientReason() { return clientReason; }
    public String getDoctorNote() { return doctorNote; }

    public void setId(long id) { this.id = id; }
    public void setMedicalNote(String clientReason, String doctorNote){
        this.clientReason = clientReason;
        this.doctorNote = doctorNote;
    }

    @Override
    public String toString() {
        java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return String.format("ID: %d | Pet: %s | Vet: Dr. %s | Time: %s | State: %s", id, pet.getPetName(), vet.getLastName(), dateTime.format(fmt), currentState);
    }

}
