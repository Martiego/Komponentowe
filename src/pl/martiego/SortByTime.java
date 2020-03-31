package pl.martiego;

import java.util.Comparator;

public class SortByTime implements Comparator<Trip> {
    @Override
    public int compare(Trip t1, Trip t2) {
        if (t1.getTime() > t2.getTime())
            return 1;
        else if (t2.getTime() > t1.getTime())
            return -1;

        return 0;
    }
}
