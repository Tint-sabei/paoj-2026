package com.pao.laboratory07.exercise3;

public final class ComandaStandard extends Comanda {

    private final double price;

    public ComandaStandard(String name, double price, String client) {
        super(name, client);
        this.price = price;
    }
    @Override public double pretFinal() { return price; }

    @Override public String description() {
        return String.format("STANDARD: %s, pret: %.2f lei [%s] - client: %s", name, pretFinal(), state, client);
    }
    @Override
    public String shortDescription() {
        return String.format("STANDARD: %s, price: %.2f lei - client: %s", name, pretFinal(), client);
    }
}