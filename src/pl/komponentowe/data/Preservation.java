package pl.komponentowe.data;

import java.util.ArrayList;

public interface Preservation {
    void save(String path, ArrayList<Trip> object);
    ArrayList<Trip> load(String path);
}
