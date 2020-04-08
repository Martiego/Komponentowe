package pl.martiego.sorting;

import pl.martiego.Trip;

import java.util.Comparator;

public class SortByAvgFuelConsumption implements Comparator<Trip> {
    @Override
    public int compare(Trip t1, Trip t2) {
        return Double.compare(t1.getAvgFuelConsumption(), t2.getAvgFuelConsumption());
    }
}
