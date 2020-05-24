package pl.komponentowe.logic.sorting;

import pl.komponentowe.data.Trip;

import java.util.Comparator;

/**
 * Klasa implementujaca interfejs Comparator, sluzy do sortowania wycieczek po dacie.
 */
public class SortByDate implements Comparator<Trip> {
    @Override
    public int compare(Trip t1, Trip t2) {
        return t1.getDate().compareTo(t2.getDate());
    }
}
