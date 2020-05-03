package pl.komponentowe.logic.fluids;

public class Fuel implements Fluid {
    private double amount;
    private final double maxAmount;

    public Fuel(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Override
    public double checkLevel() {
        return amount / 100;
    }

    @Override
    public void fill(double amount) {
        if (this.amount + amount <= maxAmount) {
            this.amount += amount;
        } else {
            this.amount = maxAmount;
        }
    }

    @Override
    public boolean isEnough() {
        return amount >= maxAmount * 0.1;
    }

    @Override
    public void update(boolean isMoving) {
        if (isMoving) {
            amount -= 1;
        }
    }


}
