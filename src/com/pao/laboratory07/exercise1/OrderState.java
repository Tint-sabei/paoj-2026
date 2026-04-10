package com.pao.laboratory07.exercise1;

public enum OrderState {
    PLACED, PROCESSED, SHIPPED, DELIVERED, CANCELED;

    public OrderState next() {
        switch (this) {
            case PLACED:
                return PROCESSED;
            case PROCESSED:
                return SHIPPED;
            case SHIPPED:
                return DELIVERED;
            default:
                return this;
        }
    }

    public boolean isFinal() {
        if (this == DELIVERED) {
            return true;
        }
        if (this == CANCELED) {
            return true;
        }
        return false;
    }
}