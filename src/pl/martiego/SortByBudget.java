package pl.martiego;

import java.util.Comparator;

public class SortByBudget implements Comparator<Movie> {
    @Override
    public int compare(Movie f1, Movie f2) {
        if (f1.getBudget() > f2.getBudget())
            return 1;
        else if (f2.getBudget() > f1.getBudget())
            return -1;

        return 0;
    }
}
