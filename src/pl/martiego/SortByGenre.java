package pl.martiego;

import java.util.Comparator;

public class SortByGenre implements Comparator<Movie> {
    @Override
    public int compare(Movie f1, Movie f2) {
        return f1.getGenre().compareTo(f2.getGenre());
    }
}
