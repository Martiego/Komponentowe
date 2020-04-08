package pl.komponentowe;

public interface Preservation<T> {
    void save(String path, T object);
    T load(String path);
}
