package pl.martiego;

import java.util.Comparator;

public class SortByRate implements Comparator<Movie> {
    @Override
    public int compare(Movie f1, Movie f2) {
        if (f1.getRate() > f2.getRate())
            return 1;
        else if (f2.getRate() > f1.getRate())
            return -1;

        return 0;
    }
}
