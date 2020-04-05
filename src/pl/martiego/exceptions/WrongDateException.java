package pl.martiego.exceptions;

public class WrongDateException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Wrong data!";
    }
}
