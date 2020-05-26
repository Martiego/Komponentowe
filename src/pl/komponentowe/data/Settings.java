package pl.komponentowe.data;

import pl.komponentowe.logic.exceptions.NegativeValueException;

import java.io.Serializable;

/**
 * Klasa odpowiedzialna za przechowywanie ustawien samochodu.
 *
 * @author Patryk Kolanek
 * @author Kacper Swiercz
 */
public class Settings implements Serializable {
    /**
     * Pole przechowujace wartosc przebiegu calkowitego pojazdu.
     */
    private double mileage;
    /**
     * Pole przechowujące maksymalna ilosc paliwa w pojezdzie.
     */
    private double maxFuel;
    /**
     * Pole przechowujące aktualna ilosc paliwa w pojezdzie.
     */
    private double actualFuel;
    /**
     * Pole przechowujące maksymalna ilosc oleju w pojezdzie.
     */
    private double maxOil;
    /**
     * Pole przechowujące aktualna ilosc oleju w pojezdzie.
     */
    private double actualOil;
    /**
     * Pole pierwszego dziennego licznika przebiegu.
     */
    private double odometer1;
    /**
     * Pole drugiego dziennego licznika przebiegu.
     */
    private double odometer2;

    @Override
    public String toString() {
        return "Settings: \n" +
                " Mileage: " + mileage + " km" + '\n' +
                " Maximum fuel: " + maxFuel + " l" + '\n' +
                " Actual fuel: " + actualFuel + " l" + '\n' +
                " Max oil: " + maxOil + " l" + '\n' +
                " Actual oil: " + actualOil + " l" + '\n' +
                " Odometer 1: " + odometer1 + " km" + '\n' +
                " Odometer 2: " + odometer2 + " km" + '\n';
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) throws NegativeValueException {
        if (mileage < 0) {
            throw new NegativeValueException();
        }

        this.mileage = mileage;
    }

    public double getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(double maxFuel) throws NegativeValueException {
        if (maxFuel < 0) {
            throw new NegativeValueException();
        }

        this.maxFuel = maxFuel;

        if (this.maxFuel < this.actualFuel) {
            this.actualFuel = this.maxFuel;
        }
    }

    public double getMaxOil() {
        return maxOil;
    }

    public void setMaxOil(double maxOil) throws NegativeValueException {
        if (maxOil < 0) {
            throw new NegativeValueException();
        }

        this.maxOil = maxOil;

        if (this.maxOil < this.actualOil) {
            this.actualOil = this.maxOil;
        }
    }

    public double getActualFuel() {
        return actualFuel;
    }

    public void setActualFuel(double actualFuel) throws NegativeValueException {
        if (actualFuel < 0) {
            throw new NegativeValueException();
        }

        this.actualFuel = Math.min(actualFuel, maxFuel);
    }

    public double getActualOil() {
        return actualOil;
    }

    public void setActualOil(double actualOil) throws NegativeValueException {
        if (actualOil < 0) {
            throw new NegativeValueException();
        }

        this.actualOil = Math.min(actualOil, maxOil);
    }

    public double getOdometer1() {
        return odometer1;
    }

    public void setOdometer1(double odometer1) throws NegativeValueException {
        if (odometer1 < 0) {
            throw new NegativeValueException();
        }

        this.odometer1 = odometer1;
    }

    public double getOdometer2() {
        return odometer2;
    }

    public void setOdometer2(double odometer2) throws NegativeValueException {
        if (odometer2 < 0) {
            throw new NegativeValueException();
        }

        this.odometer2 = odometer2;
    }
}
