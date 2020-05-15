package pl.komponentowe.logic.fluids;

public interface Fluid {
    double checkLevel();
    void fill(double amount);
    boolean isEnough();
    void update(double amount);
}
