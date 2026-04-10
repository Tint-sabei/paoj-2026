package com.pao.laboratory07.exercise2;

public final class ComandaRedusa extends Comanda {
    private final double price;
    private final int discountPercent;

    public ComandaRedusa(String name, double price, int discountPercent) {
        super(name);
        this.price = price;
        this.discountPercent = discountPercent;
    }

    @Override
    public double pretFinal() {
        return price * (1 - discountPercent / 100.0);
    }

    @Override
    public String description() {
        return String.format("DISCOUNTED: %s, pret: %.2f lei (-%d%%) [%s]",
                name, pretFinal(), discountPercent, state);
    }
}