package pl.komponentowe.logic.exceptions;

public class WrongValueException extends Exception {
    @Override
    public String getMessage() {
        return "Wrong value!";
    }
}
