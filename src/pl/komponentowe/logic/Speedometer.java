package pl.komponentowe.logic;

public class Speedometer {
    private double actualVelocity;

    public void accelerate() {
        if (220 > actualVelocity) {
            actualVelocity += 0.5;
        }
    }

    public void decelerate(int value) {
        if (0 > (actualVelocity - value * 0.5)) {
            actualVelocity -= value * 0.5;
        }
    }

    public double getActualVelocity() {
        return actualVelocity;
    }
}
