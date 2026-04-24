package com.pao.project.etapa1.exceptions;

public class AppointmentIsAlreadyFinalException extends Exception {
    public AppointmentIsAlreadyFinalException() {
        super("Order is already in a final state.");
    }
}
