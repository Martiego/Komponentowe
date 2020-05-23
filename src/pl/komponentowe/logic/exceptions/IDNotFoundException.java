package pl.komponentowe.logic.exceptions;

public class IDNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "Id not found!";
    }
}
