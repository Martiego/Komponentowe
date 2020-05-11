package pl.komponentowe.logic;

import pl.komponentowe.data.IOXml;
import pl.komponentowe.data.Trip;
import pl.komponentowe.logic.fluids.Fuel;
import pl.komponentowe.logic.fluids.Oil;
import pl.komponentowe.logic.lights.Indicators;

import java.util.ArrayList;

public class Dashboard {
    private boolean highBeams;


    private Fuel fuel;
    private Oil oil;
    private Indicators indicators;
    private OnBoardComputer onBoardComputer;

    public Dashboard() {
        fuel = new Fuel(50);
        oil = new Oil(5);
        onBoardComputer = new OnBoardComputer();
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

    public Indicators getIndicators() {
        return indicators;
    }

    public OnBoardComputer getOnBoardComputer() {
        return onBoardComputer;
    }
}
