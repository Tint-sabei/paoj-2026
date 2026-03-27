package com.pao.laboratory05.biblioteca;
import java.util.Arrays;
import java.util.Comparator;

class BibliotecaService {

    private Carte[] carti = new Carte[0];

    // private constructor
    private BibliotecaService() {}

    // getInstance()
    private static class Holder {
        private static final BibliotecaService instance = new BibliotecaService();
    }

    public static BibliotecaService getInstance() {
        return Holder.instance;
    }

    void addCarte(Carte carte) {
        // instruction: resize +  add + print confirmation
        Carte[] newCarti = new Carte[carti.length + 1];
        System.arraycopy(this.carti, 0, newCarti, 0, carti.length);
        newCarti[carti.length] = carte;
        this.carti = newCarti;
        System.out.println("Carte added: " + carte.getTitle());
    }

    void listSortedByRating() {
        Carte[] copy = carti.clone();
        Arrays.sort(copy);
        for (Carte c : copy){
            System.out.println(c);
        }
    }

    void listSortedBy(Comparator<Carte> comparator) {
        Carte[] copy = carti.clone();
        Arrays.sort(copy, comparator);
        for (Carte c : copy){
            System.out.println(c);
        }
    }

}