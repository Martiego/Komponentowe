package pl.komponentowe.logic.exceptions;

/**
 * Klasa odpowiedzialna za wyjatek polegajacy na braku rekordu o podanym numerze id.
 */
public class IDNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "Id not found!";
    }
}
