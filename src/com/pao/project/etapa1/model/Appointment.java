package com.pao.project.etapa1.model;
import com.pao.project.etapa1.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Appointment {
    private String id;
    private Pet pet;
    private Vet vet;
    private ServiceType service;
    private State currentState;
    private final List<State> history = new ArrayList<>();
    private LocalDateTime dateTime;
    private String clientReason;
    private String doctorNote;

    public Appointment(String id, Pet pet, Vet vet, ServiceType service, State currentState, LocalDateTime dateTime, String clientReason, String doctorNote){
        this.id = id;
        this.pet = pet;
        this.vet = vet;
        this.service = service;
        this.currentState = currentState;
        this.dateTime = dateTime;
        this.clientReason = clientReason;
        this.doctorNote = doctorNote;
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

    public ServiceType getService(){
        return service;
    }

    public Vet getVet(){return vet;}

    public void getMedicalNote(String clientReason, String doctorNote){
        this.clientReason = clientReason;
        this.doctorNote = doctorNote;
    }

}
