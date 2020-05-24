package pl.komponentowe.logic.sorting;

import pl.komponentowe.data.Trip;

import java.util.Comparator;

/**
 * Klasa implementujaca interfejs Comparator, sluzy do sortowania wycieczek po czasie trwania.
 */
public class SortByTime implements Comparator<Trip> {
    @Override
    public int compare(Trip t1, Trip t2) {
        return Long.compare(t1.getTime(), t2.getTime());
    }
}
