package com.pao.laboratory05.biblioteca;
import java.util.Comparator;

public class CarteAnComparator implements Comparator<Carte> {

    @Override
    public int compare(Carte c1, Carte c2)
    {
        //Sort by `year` ascending (oldest appears first).
        return Integer.compare(c1.getYear(), c2.getYear());
    }
}