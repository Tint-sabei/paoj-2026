package com.pao.project.etapa1.exceptions;

public class CannotCancelFinalAppointmentException extends Exception {
    public CannotCancelFinalAppointmentException() {
        super("Cannot cancel a final appointment state.");
    }
}