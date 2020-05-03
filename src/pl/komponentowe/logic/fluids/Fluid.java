package pl.komponentowe.logic.fluids;

public interface Fluid {
    double checkLevel();
    void fill(double amount);
    boolean isEnough();
    void update(boolean isMoving);
}
