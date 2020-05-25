package pl.komponentowe.logic.fluids;
/**
 * Intefejs implementowany przez klasy oznaczjace plyny w samochodzie (np. paliwo).
 *
 * @author Patryk Kolanek
 * @author Kacper Swiercz
 */
public interface Fluid {
    /**
     * Metoda sprawdza poziom plynu.
     * @return Zwraca procentowa wartosc wypelnienia zbiornika (od 0 do 1).
     */
    double checkLevel();

    /**
     * Metoda uzupelnia poziom plynu o pewna wartosc.
     * @param amount Wartosc, o ktora, chcemy uzupelnic zbiornik podana jako liczba typu <b>double</b>.
     */
    void fill(double amount);
    boolean isEnough();
    void update(double amount, boolean driving);
}
