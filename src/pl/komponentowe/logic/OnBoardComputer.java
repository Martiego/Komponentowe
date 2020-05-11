package pl.komponentowe.logic;

import pl.komponentowe.data.Trip;

import java.util.ArrayList;
import java.util.Date;

public class OnBoardComputer {
    private Date date;
    private double actualVelocity;
    private double avgFuelConsumption;
    private double maxVelocity;
    private double avgVelocity;
    private double street;
    private long time;
    private ArrayList<Trip> trips;


    public OnBoardComputer() {
        date = new Date();
    }

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

        if (actualVelocity > maxVelocity) {
            maxVelocity = actualVelocity;
        }

        street += actualVelocity / 3_600_000 * getTime();
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

    public double getMaxVelocity() {
        return maxVelocity;
    }

    public long getTime() {
        time = date.getTime() - (new Date()).getTime();
        return time;
    }

    public double getStreet() {
        return street;
    }
}
