package pl.komponentowe.logic.fluids;

/**
 * Klasa reprezentujaca olej pojazdu.
 *
 * @author Patryk Kolanek
 * @author Kacper Swiercz
 */
public class Oil implements Fluid {
    /** Obecna ilosc oleju. */
    private double amount;
    /** Maksymalna ilosc oleju. */
    private double maxAmount;
    /** Obecna temperatura plynu. */
    private double temperature;

    /** Konstruktor przyjmujacy maskymalna ilosc oleju */
    public Oil(double maxAmount) {
        this.maxAmount = maxAmount;
        temperature = 20;
    }

    /**
     * Aktualny poziom oleju z przedzialu od 0 do 1.
     *
     * @return Aktualny poziom oleju podany jako procentowa wartosc zbiornika <b>od 0 do 1</b>.
     */
    @Override
    public double checkLevel() {
        return amount / 100;
    }

    /**
     * Metoda uzupelniajaca olej.
     *
     * @param amount Dodanie oleju do zbiornika w <b>litrach</b>.
     */
    @Override
    public void fill(double amount) {
        if (this.amount + amount <= maxAmount) {
            this.amount += amount;
        } else {
            this.amount = maxAmount;
        }
    }

    /**
     * Metoda sprawdzajaca czy aktualny poziom paliwa jest wystarczajacy.
     *
     * @return Infromacja czy aktualny poziom paliwa jest wystarczajacy.
     */
    @Override
    public boolean isEnough() {
        return amount >= maxAmount * 0.25 && temperature < 80;
    }

    /**
     * Metoda wykorzystywana przez deske rozdzielcza do aktualizowania obecnego poziomu oleju.
     *
     * @param amount Ilosc zuzytego oleju.
     */
    @Override
    public void update(double amount) {
        this.amount -= amount;
        temperature += 1;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }
}
