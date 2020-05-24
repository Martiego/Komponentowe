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
    private double mileage;
    private double maxFuel;
    private double actualFuel;
    private double maxOil;
    private double actualOil;

    @Override
    public String toString() {
        return "Settings: \n" +
                " Mileage: " + mileage + " km" + '\n' +
                " Maximum fuel: " + maxFuel + " l" + '\n' +
                " Actual fuel: " + actualFuel + " l" + '\n' +
                " Max oil: " + maxOil + " l" + '\n' +
                " Actual oil: " + actualOil + " l" + '\n';
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

}
