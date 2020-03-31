package pl.martiego;

import java.util.Comparator;

public class SortByMaxVelocity implements Comparator<Trip> {
    @Override
    public int compare(Trip t1, Trip t2) {
        if (t1.getMaxVelocity() > t2.getMaxVelocity())
            return 1;
        else if (t2.getMaxVelocity() > t1.getMaxVelocity())
            return -1;

        return 0;
    }
}
