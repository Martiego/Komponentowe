package pl.martiego;

import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
    private double rate;
    private String genre;
    private double budget;

    public Movie(String title, double rate, String genre, double budget) {
        this.title = title;
        this.rate = rate;
        this.genre = genre;
        this.budget = budget;
    }

    public Movie(Object o) {
        this.title = ((Movie) o).getTitle();
        this.rate = ((Movie) o).getRate();
        this.genre = ((Movie) o).getGenre();
        this.budget = ((Movie) o).getBudget();
    }

    public String getTitle() {
        return title;
    }

    public double getRate() {
        return rate;
    }

    public String getGenre() {
        return genre;
    }

    public double getBudget() {
        return budget;
    }

    @Override
    public String toString() {
        String str = "";
        str += "Tytul: " + title + '\n';
        str += "Ocena: " + rate + '\n';
        str += "Gatunek: " + genre + '\n';
        str += "Budżet: " + budget + '\n' + '\n';
        return str;
    }

}
