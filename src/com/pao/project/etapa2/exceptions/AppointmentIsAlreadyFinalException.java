package com.pao.project.etapa2.exceptions;

public class AppointmentIsAlreadyFinalException extends Exception {
    public AppointmentIsAlreadyFinalException() {
        super("This appointment is already in a final state (Paid or Canceled) and cannot be modified.");
    }
}