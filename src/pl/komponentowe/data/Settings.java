package pl.komponentowe.data;

import java.io.Serializable;

/**
 * Klasa odpowiedzialna za przechowywanie ustawien samochodu.
 *
 * @author Patryk Kolanek
 * @author Kacper Swiercz
 */
public class Settings implements Serializable {
    private double mileage;
    private double maxFuel;
    private double maxOil;

    public Settings() {
    }

    /**
     * Konstruktor przyjmujacy jako parametr wszystkie pola.
     *
     * @param mileage Przebieg calkowity samochodu w <b>metrach</b>.
     * @param maxFuel Maksymalna pojemnosc paliwa w <b>litrach</b>.
     * @param maxOil Maksymalna pojmnosc oleju w <b>litrach</b>.
     */
    public Settings(double mileage, double maxFuel, double maxOil) {
        this.mileage = mileage;
        this.maxFuel = maxFuel;
        this.maxOil = maxOil;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public double getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(double maxFuel) {
        this.maxFuel = maxFuel;
    }

    public double getMaxOil() {
        return maxOil;
    }

    public void setMaxOil(double maxOil) {
        this.maxOil = maxOil;
    }
}
