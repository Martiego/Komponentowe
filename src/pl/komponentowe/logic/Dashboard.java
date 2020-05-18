package pl.komponentowe.logic;

import pl.komponentowe.logic.fluids.Fuel;
import pl.komponentowe.logic.fluids.Oil;

import java.util.Date;

public class Dashboard {
    private boolean isRunning;

    private Date date;

    private double actualVelocity;
    private double maxVelocity;
    private double distance;
    private double mileage;
    private Thread thread;
    private double odometer;
    private Fuel fuel;
    private Oil oil;
    private double fuelConsumption;

    private double timeFuelConsumption;

    public Dashboard() {
        isRunning = true;
        fuel = new Fuel(50);
        fuel.fill(50);
        oil = new Oil(5);
        oil.fill(5);
        date = new Date();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                double avgDistance;
                while (isRunning) {
                    try {
                        Thread.sleep(100);
                        avgDistance = actualVelocity / 3_600_000 * 100;
                        // droga w km
                        distance += avgDistance;
                        mileage += avgDistance;
                        odometer += avgDistance;
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });

        thread.start();
    }

    public void stop() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
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

        timeFuelConsumption = Math.random() / 100;
        fuelConsumption += timeFuelConsumption;

        fuel.update(timeFuelConsumption);
        oil.update(0.001);
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

    public double getDistance() {
        return distance;
    }

    public double getOdometer() {
        return odometer;
    }

    public double getAvgVelocity() {
        double meter = distance * 1000;
        double seconds = (double)(getTime() / 1_000);
        return meter / seconds * 36 / 10;
    }

    public Date getDate() {
        return date;
    }

    public double getAvgFuelConsumption() {
        if (distance != 0)
            return fuelConsumption / distance;
        return 0;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public Oil getOil() {
        return oil;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public double getMaxFuel() {
        return fuel.getMaxAmount();
    }

    public double getMaxOil() {
        return oil.getMaxAmount();
    }
}
