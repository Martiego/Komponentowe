package pl.komponentowe.logic.fluids;

public class Oil implements Fluid {
    private double amount;
    private final double maxAmount;
    private double temperature;

    public Oil(double maxAmount) {
        this.maxAmount = maxAmount;
        temperature = 20;
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
        return amount >= maxAmount * 0.25 && temperature < 80;
    }

    @Override
    public void update(boolean isMoving) {
        if (isMoving) {
            temperature += 2;
            amount -= 0.1;
        } else {
            temperature -= 1;
        }

    }

    public double getTemperature() {
        return temperature;
    }


}