package pl.komponentowe.sorting;

import pl.komponentowe.Trip;

import java.util.Comparator;

public class SortByAvgVelocity implements Comparator<Trip> {
    @Override
    public int compare(Trip  t1, Trip t2) {
        return Double.compare(t1.getAvgVelocity(), t2.getAvgVelocity());
    }
}
