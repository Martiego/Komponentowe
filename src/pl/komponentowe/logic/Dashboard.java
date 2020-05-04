package pl.komponentowe.logic;

import pl.komponentowe.logic.fluids.Fuel;
import pl.komponentowe.logic.fluids.Oil;
import pl.komponentowe.logic.lights.Indicators;

public class Dashboard {
    private boolean highBeams;
    private double mileage;
    private double odometer;
    private double tripMeter;

    private Speedometer speedometer;
    private Fuel fuel;
    private Oil oil;
    private Indicators indicators;
    private OnBoardComputer onBoardComputer;

    public Dashboard() {
        speedometer = new Speedometer();
        fuel = new Fuel(50);
        oil = new Oil(5);
        onBoardComputer = new OnBoardComputer();
    }

    public Speedometer getSpeedometer() {
        return speedometer;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public Oil getOil() {
        return oil;
    }

    public boolean isHighBeams() {
        return highBeams;
    }

    public double getMileage() {
        return mileage;
    }

    public double getOdometer() {
        return odometer;
    }

    public double getTripMeter() {
        return tripMeter;
    }

    public Indicators getIndicators() {
        return indicators;
    }

    public OnBoardComputer getOnBoardComputer() {
        return onBoardComputer;
    }
}
