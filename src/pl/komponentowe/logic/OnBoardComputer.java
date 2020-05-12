package pl.komponentowe.logic;

import pl.komponentowe.data.Trip;
import pl.komponentowe.logic.fluids.Fuel;

import java.util.ArrayList;
import java.util.Date;

public class OnBoardComputer {
    private Date date;
    private double actualVelocity;
    private double avgFuelConsumption;
    private double maxVelocity;
//    private double avgVelocity;
    private double street;
    private double mileage;
//    private long time;
    private ArrayList<Trip> trips;
    private Thread thread;

    private double odometer;
    private double tripMeter;

    private Fuel fuel;


    public OnBoardComputer() {
        fuel = new Fuel(50);
        fuel.fill(50);
        date = new Date();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                double avgStreet;
                while (true) {
                    try {
                        Thread.sleep(100);
                        avgStreet = actualVelocity / 3_600_000 * 100;
                        street += avgStreet;
                        mileage += avgStreet;
                        odometer += avgStreet;
                        tripMeter += avgStreet;
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });

        thread.start();
    }

    public double getMileage() {
        return mileage;
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

        fuel.update(true);
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

    public void resetOdometer() {
        odometer = 0;
    }

    public double getActualVelocity() {
        return actualVelocity;
    }

    public double getMaxVelocity() {
        return maxVelocity;
    }

    public long getTime() {
        return System.currentTimeMillis() - date.getTime();
    }

    public double getStreet() {
        return street;
    }

    public double getOdometer() {
        return odometer;
    }

    public double getAvgVelocity() {
        // FIXME: 11.05.2020
        // najpierw zamiana na m/s, potem na km/h
        // no i skacze tak +/-, nawet jak cały czas przyspiesza
        // w sensie wiesz, wtedy średnia powinna cały czas rosnąć
        // hmmm
        double meter = street * 1000;
        double seconds = (double)(getTime() / 1_000);
        return meter / seconds * 36 / 10;
    }

    public double getAvgFuelConsumption() {
        return avgFuelConsumption;
    }

    public Fuel getFuel() {
        return fuel;
    }
}
