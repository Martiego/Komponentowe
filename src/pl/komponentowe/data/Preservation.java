package pl.komponentowe.data;

import java.util.ArrayList;

public interface Preservation<T> {
    void save(String path, ArrayList<T> object);
    ArrayList<T> load(String path);
}
