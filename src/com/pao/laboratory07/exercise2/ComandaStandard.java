package com.pao.laboratory07.exercise2;

public final class ComandaStandard extends Comanda {
    private final double price;

    public ComandaStandard(String name, double price) {
        super(name);
        this.price = price;
    }

    @Override
    public double pretFinal() { return price; }

    @Override
    public String description() {
        return String.format("STANDARD: %s, pret: %.2f lei [%s]",
                name, pretFinal(), state);
    }
}