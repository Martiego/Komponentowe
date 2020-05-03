package pl.komponentowe.data;

public class IODataBase<T> implements Preservation<T> {
    @Override
    public void save(String path, T object) {
        // Method responsible for save to database
    }

    @Override
    public T load(String path) {
        // Method responsible for save to database
        return null;
    }
}
