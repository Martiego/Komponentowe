package pl.komponentowe.logic.exceptions;

/**
 * Klasa odpowiedzialna za wyjatek polegajacy na podaniu ujemnej wartosci w miejscu niedozwolonym.
 */
public class NegativeValueException extends Exception {
    @Override
    public String getMessage() {
        return "Negative value!";
    }
}
