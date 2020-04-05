package pl.martiego.exceptions;

public class WrongValueException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Wrong value!";
    }
}
