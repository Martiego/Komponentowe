package pl.komponentowe.logic.exceptions;

/**
 * Klasa odpowiedzialna za wyjatek polegajacy na braku rekordu o podanym numerze id.
 */
public class NegativeValueException extends Exception {
    @Override
    public String getMessage() {
        return "Negative value!";
    }
}
