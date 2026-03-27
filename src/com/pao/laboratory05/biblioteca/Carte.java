package com.pao.laboratory05.biblioteca;

public class Carte implements Comparable<Carte> {
    private String title;
    private String author;
    private int year;
    private double rating;

    // full constructor
    public Carte (String title, String author, int year, double rating){
        this.title = title;
        this.author = author;
        this.year = year;
        this.rating = rating;
    }

    // getter for all fields
    public String getTitle(){return title;}
    public String getAuthor(){return author;}
    public int getYear(){return year;}
    public double getRating(){return rating;}

    @Override
    public int compareTo (Carte other) {
        // sort by 'rating' descending
        return Double.compare(other.rating, this.rating);
    }

    @Override
    public String toString(){
        return "Carte{titlu='" + title + "', autor='" + author + "', an=" + year + ", rating=" + rating + "}";
        }
}
