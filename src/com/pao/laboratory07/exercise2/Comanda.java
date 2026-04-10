package com.pao.laboratory07.exercise2;

import com.pao.laboratory07.exercise1.OrderState;

public abstract sealed class Comanda permits ComandaStandard, ComandaRedusa, ComandaGratuita {
    protected String name;
    protected OrderState state;

    Comanda(String name) {
        this.name = name;
        this.state = OrderState.PLACED;
    }

    public abstract double pretFinal();
    public abstract String description();
}