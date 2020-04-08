package pl.martiego.exceptions;

public class WrongDateException extends Exception {
    @Override
    public String getMessage() {
        return "Wrong data!";
    }
}
