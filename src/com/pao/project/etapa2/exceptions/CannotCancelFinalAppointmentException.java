package com.pao.project.etapa2.exceptions;

public class CannotCancelFinalAppointmentException extends Exception {
    public CannotCancelFinalAppointmentException() {
        super("Cannot cancel an appointment that has already been paid/cancelled.");
    }
}