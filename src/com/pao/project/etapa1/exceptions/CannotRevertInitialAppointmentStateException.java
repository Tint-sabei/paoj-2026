package com.pao.project.etapa1.exceptions;

public class CannotRevertInitialAppointmentStateException extends Exception {
    public CannotRevertInitialAppointmentStateException() {
        super("Cannot undo the initial order state.");
    }
}