package com.pao.laboratory07.exercise3;

public final class ComandaGratuita extends Comanda {

    public ComandaGratuita(String name, String client) {
        super(name, client);
    }

    @Override
    public double pretFinal() { return 0.0; }

    @Override
    public String description() {
        return String.format("GIFT: %s, gratuit [%s] - client: %s" , name, state, client);
    }
    @Override
    public String shortDescription() {
        return String.format("GIFT: %s, free - client: %s", name, client);
    }

}