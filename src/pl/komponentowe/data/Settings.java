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
    private double actualFuel;
    private double maxOil;
    private double actualOil;

    public Settings() {
    }

    /**
     * Konstruktor przyjmujacy jako parametr wszystkie pola.
     *
     * @param mileage Przebieg calkowity samochodu w <b>metrach</b>.
     * @param maxFuel Maksymalna pojemnosc paliwa w <b>litrach</b>.
     * @param actualFuel Obecna ilosc paliwa w <b>litrach</b>.
     * @param maxOil Maksymalna pojmnosc oleju w <b>litrach</b>.
     * @param actualOil Obecna ilosc oleju w <b>litrach</b>.
     */
    public Settings(double mileage, double maxFuel, double actualFuel, double maxOil, double actualOil) {
        this.mileage = mileage;
        this.maxFuel = maxFuel;
        this.actualFuel = actualFuel;
        this.maxOil = maxOil;
        this.actualOil = actualOil;
    }

    @Override
    public String toString() {
        return "Settings: \n" +
                " Mileage: " + mileage + '\n' +
                " Maximum fuel: " + maxFuel + '\n' +
                " Actual fuel: " + actualFuel + '\n' +
                " Max oil: " + maxOil + '\n' +
                " Actual oil: " + actualOil + '\n';
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
        if (this.maxFuel < this.actualFuel) {
            this.actualFuel = this.maxFuel;
        }
    }

    public double getMaxOil() {
        return maxOil;
    }

    public void setMaxOil(double maxOil) {
        this.maxOil = maxOil;
        if (this.maxOil < this.actualOil) {
            this.actualOil = this.maxOil;
        }
    }

    public double getActualFuel() {
        return actualFuel;
    }

    public void setActualFuel(double actualFuel) {
        this.actualFuel = Math.min(actualFuel, maxFuel);
    }

    public double getActualOil() {
        return actualOil;
    }

    public void setActualOil(double actualOil) {
        this.actualOil = Math.min(actualOil, maxOil);
    }
}
