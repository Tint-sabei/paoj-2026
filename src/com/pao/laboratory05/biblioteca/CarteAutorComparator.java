package com.pao.laboratory05.biblioteca;
import java.util.Comparator;

public class CarteAutorComparator implements Comparator<Carte> {

    @Override
    public int compare(Carte c1, Carte c2)
    {
        //Sort by `autor` alphabetically
        return c1.getAuthor().compareTo(c2.getAuthor());
    }
}