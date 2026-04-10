package com.pao.laboratory07.exercise3;

public final class ComandaRedusa extends Comanda {

    private final double price;
    private final int discountPercent;

    public ComandaRedusa(String name, double price, int discountPercent, String client) {
        super(name, client);
        this.price = price;
        this.discountPercent = discountPercent;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    @Override
    public double pretFinal() {
        return price * (1 - discountPercent / 100.0);
    }

    @Override
    public String description() {
        return String.format("DISCOUNTED: %s, pret: %.2f lei (-%d%%) [%s] - client: %s",
                name, pretFinal(), discountPercent, state, client);
    }


    @Override
    public String shortDescription() {
        return String.format("DISCOUNTED: %s, pret: %.2f lei - client: %s",
                name, pretFinal(), client);
    }
}