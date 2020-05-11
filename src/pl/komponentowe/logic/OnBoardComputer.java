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
    private double mileage;
    private long time;
    private ArrayList<Trip> trips;
    private Thread thread;


    public OnBoardComputer() {
        date = new Date();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                        street += actualVelocity / 3_600_000 * 100;
                        mileage += street;
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });

        thread.start();
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
            actualVelocity += 0.20;
        }

        if (actualVelocity > maxVelocity) {
            maxVelocity = actualVelocity;
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

    public double getMaxVelocity() {
        return maxVelocity;
    }

    public long getTime() {
        time = (new Date()).getTime() - date.getTime();
        return time;
    }

    public double getStreet() {
        return street;
    }
}
