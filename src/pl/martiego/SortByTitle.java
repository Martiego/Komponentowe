package pl.martiego;

import java.util.Comparator;

public class SortByTitle implements Comparator<Movie> {
    @Override
    public int compare(Movie f1, Movie f2) {
        return f1.getTitle().compareTo(f2.getTitle());
    }
}
