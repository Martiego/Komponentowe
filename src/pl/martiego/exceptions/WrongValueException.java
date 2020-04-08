package pl.martiego.exceptions;

public class WrongValueException extends Exception {
    @Override
    public String getMessage() {
        return "Wrong value!";
    }
}
