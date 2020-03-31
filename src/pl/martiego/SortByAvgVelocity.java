package pl.martiego;

import java.util.Comparator;

public class SortByAvgVelocity implements Comparator<Trip> {
    @Override
    public int compare(Trip  t1, Trip t2) {
        if (t1.getAvgVelocity() > t2.getAvgVelocity())
            return 1;
        else if (t2.getAvgVelocity() > t1.getAvgVelocity())
            return -1;

        return 0;
    }
}
