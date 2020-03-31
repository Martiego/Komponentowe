package pl.martiego;

import java.util.Comparator;

public class SortByAvgFuelConsumption implements Comparator<Trip> {
    @Override
    public int compare(Trip t1, Trip t2) {
        if (t1.getAvgFuelConsumption() > t2.getAvgFuelConsumption())
            return 1;
        else if (t2.getAvgFuelConsumption() > t1.getAvgFuelConsumption())
            return -1;

        return 0;
    }
}
