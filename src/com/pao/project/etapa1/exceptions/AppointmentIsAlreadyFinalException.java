package com.pao.project.etapa1.exceptions;

public class AppointmentIsAlreadyFinalException extends Exception {
    public AppointmentIsAlreadyFinalException() {
        super("This appointment is already in a final state (Paid or Canceled) and cannot be modified.");
    }
}