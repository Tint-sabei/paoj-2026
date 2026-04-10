package com.pao.laboratory07.exercise2;

public final class ComandaGratuita extends Comanda {
    public ComandaGratuita(String name) {
        super(name);
    }

    @Override
    public double pretFinal() { return 0.0; }

    @Override
    public String description() {
        return String.format("GIFT: %s, gratuit [%s]", name, state);
    }
}