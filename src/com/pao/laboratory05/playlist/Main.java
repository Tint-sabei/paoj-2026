package com.pao.laboratory05.playlist;

/**
 * Exercise 1 — Music Playlist
 *
 * Complete requirements can be found in:
 *   src/com/pao/laboratory05/Readme.md       →  section "Exercise 1 — Playlist"
 *
 * Before writing Song.java, read:
 *   src/com/pao/laboratory05/playlist/Readme.md  →  Java Records quick reference (Levels 1–4)
 *
 * Create the files from scratch in this package, then run Main.java
 * to verify the expected output from the Readme.
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Requirements are in Readme.md — Exercise 1 section.");

        Playlist playlist = new Playlist("Road Trip");
        playlist.addSong(new Song("Waterloo", "ABBA", 174));
        playlist.addSong(new Song("Bohemian Rhapsody", "Queen", 354));
        playlist.addSong(new Song("Imagine", "John Lennon", 187));
        playlist.addSong(new Song("Smells Like Teen Spirit", "Nirvana", 301));

        System.out.println("=== " + playlist.getName() + " ===");
        System.out.println("Total duration: " + playlist.getTotalDuration() + "s\n");

        System.out.println("--- Sorted by title ---");
        playlist.printSortedByTitle();

        System.out.println("\n--- Sorted by duration ---");
        playlist.printSortedByDuration();
    }
}
