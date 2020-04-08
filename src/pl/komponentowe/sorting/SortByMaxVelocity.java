package pl.martiego.sorting;

import pl.martiego.Trip;

import java.util.Comparator;

public class SortByMaxVelocity implements Comparator<Trip> {
    @Override
    public int compare(Trip t1, Trip t2) {
        return Double.compare(t1.getMaxVelocity(), t2.getMaxVelocity());
    }
}
