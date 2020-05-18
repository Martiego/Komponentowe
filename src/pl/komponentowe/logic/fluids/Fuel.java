package pl.komponentowe.logic.fluids;

public class Fuel implements Fluid {
    private double amount;
    private double maxAmount;

    public Fuel(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Override
    public double checkLevel() {
        return amount / maxAmount;
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
    public void update(double amount) {
        this.amount -= amount;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }
}
