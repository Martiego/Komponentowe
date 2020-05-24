package pl.komponentowe.logic.fluids;

/**
 * Klasa reprezentujaca paliwo pojazdu.
 *
 * @author Patryk Kolanek
 * @author Kacper Swiercz
 */
public class Fuel implements Fluid {
    /** Obecna ilosc paliwa */
    private double amount;
    /** Maksymalna ilosc paliwa */
    private double maxAmount;

    /** Konstruktor przyjmujacy maskymalna ilosc paliwa
     *
     * @param maxAmount Maksymalna pojemnosc baku paliwa.
     */
    public Fuel(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    /**
     * Aktualny poziom paliwa z przedzialu od 0 do 1.
     *
     * @return Aktualny poziom paliwa podany jako procentowa wartosc zbiornika <b>od 0 do 1</b>.
     */
    @Override
    public double checkLevel() {
        return amount / maxAmount;
    }

    /**
     * Metoda uzupelniajaca paliwo.
     *
     * @param amount Dodanie paliwa do zbiornika w <b>litrach</b>.
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
        return amount >= maxAmount * 0.05;
    }

    /**
     * Metoda wykorzystywana przez deske rozdzielcza do aktualizowania obecnego poziomu paliwa.
     *
     * @param amount Ilosc spalonego paliwa.
     */
    @Override
    public void update(double amount, boolean driving) {
        this.amount -= amount;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
        this.amount = Math.min(this.amount, this.maxAmount);
    }
}
