package pl.komponentowe.logic;

import pl.komponentowe.data.IOXml;
import pl.komponentowe.data.Trip;
import pl.komponentowe.logic.fluids.Fuel;
import pl.komponentowe.logic.fluids.Oil;
import pl.komponentowe.logic.lights.Indicators;

import java.util.ArrayList;

public class Dashboard {
    private boolean highBeams;

    private Oil oil;
    private Indicators indicators;
    private OnBoardComputer onBoardComputer;

    public Dashboard() {
        oil = new Oil(5);
        onBoardComputer = new OnBoardComputer();
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
