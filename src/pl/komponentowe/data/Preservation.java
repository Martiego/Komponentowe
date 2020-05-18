package pl.komponentowe.data;

public interface Preservation {
    void save(String path, Object object);
    Object load(String path);
}
