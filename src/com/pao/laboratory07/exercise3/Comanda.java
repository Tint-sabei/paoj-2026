package com.pao.laboratory07.exercise3;

import com.pao.laboratory07.exercise1.OrderState;

public abstract sealed class Comanda permits ComandaStandard, ComandaRedusa, ComandaGratuita {
    protected String name;
    protected String client;
    protected OrderState state;

    Comanda(String name, String client) {
        this.name = name;
        this.client = client;
        this.state = OrderState.PLACED;
    }

    public abstract double pretFinal();
    public abstract String description();
    public abstract String shortDescription();

    public String getClient() { return client; }

}