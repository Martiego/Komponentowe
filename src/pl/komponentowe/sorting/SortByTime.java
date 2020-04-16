package pl.komponentowe.sorting;

import pl.komponentowe.Trip;

import java.util.Comparator;

public class SortByTime implements Comparator<Trip> {
    @Override
    public int compare(Trip t1, Trip t2) {
        return Long.compare(t1.getTime(), t2.getTime());
    }
}
