package pl.komponentowe.data;

/**
 * Interfejs implementowany przez klasy odpowiedzialne za zachowywanie danych. (Baza danych, pliki w formacie XML).
 *
 * @author Patryk Kolanek
 * @author Kacper Swiercz
 */
public interface Preservation {
    /**
     * Metoda do zapiywania danych
     * @param path Sciezka do pliku lub bazy danych
     * @param object Objekt, ktory chcemy zapisac
     */
    void save(String path, Object object);

    /**
     * Metoda do wczytywania danych
     * @param path Sciezka do pliku lub bazy danych
     * @return Zwraca wczytany objekt
     */
    Object load(String path);
}
