package pl.komponentowe.logic;

public class Speedometer {
    private double actualVelocity;

    public void accelerate() {
        if (0 <= actualVelocity && 30 >= actualVelocity) {
            actualVelocity += 0.50;
        } else if (30 < actualVelocity && 60 >= actualVelocity) {
            actualVelocity += 0.40;
        } else if (60 < actualVelocity && 90 >= actualVelocity) {
            actualVelocity += 0.35;
        } else if (90 < actualVelocity && 160 >= actualVelocity) {
            actualVelocity += 0.30;
        } else if (160 < actualVelocity && 186 >= actualVelocity) {
            actualVelocity += 0.15;
        }
    }

    public void decelerate(int value) {
        if (0 < actualVelocity && 30 >= actualVelocity) {
            actualVelocity -= value * 0.25;
        } else if (30 < actualVelocity && 60 >= actualVelocity) {
            actualVelocity -= value * 0.30;
        } else if (60 < actualVelocity && 90 >= actualVelocity) {
            actualVelocity -= value * 0.35;
        } else if (90 < actualVelocity) {
            actualVelocity -= value * 0.40;
        } else if (0 >= actualVelocity) {
            actualVelocity = 0;
        }
    }

    public double getActualVelocity() {
        return actualVelocity;
    }
}
