package pl.komponentowe.exceptions;

public class WrongDateException extends Exception {
    @Override
    public String getMessage() {
        return "Wrong data!";
    }
}
