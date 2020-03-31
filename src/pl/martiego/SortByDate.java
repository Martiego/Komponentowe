package pl.martiego;

import java.util.Comparator;

public class SortByDate implements Comparator<Trip> {
    @Override
    public int compare(Trip t1, Trip t2) {
        return t1.getDate().compareTo(t2.getDate());
    }
}
