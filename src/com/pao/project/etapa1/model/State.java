package com.pao.project.etapa1.model;

public enum State {
    SCHEDULED, CHECKED_IN, CONSULTED, PAID, CANCELED;

    public State next() {
        switch (this) {
            case SCHEDULED:
                return CHECKED_IN;
            case CHECKED_IN:
                return CONSULTED;
            case CONSULTED:
                return PAID;
            default:
                return this;
        }
    }

    public boolean isFinal() {
        if (this == PAID) {
            return true;
        }
        if (this == CANCELED) {
            return true;
        }
        return false;
    }
}

