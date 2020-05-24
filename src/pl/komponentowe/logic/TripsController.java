package pl.komponentowe.logic;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pl.komponentowe.data.IODataBase;
import pl.komponentowe.data.Trip;
import pl.komponentowe.logic.exceptions.IDNotFoundException;

import java.util.ArrayList;
/**
 * Klasa kontrolera dla okna wycieczek.
 *
 * @author Patryk Kolanek
 * @author Kacper Swiercz
 */
public class TripsController {
    @FXML
    private Text tripDate;
    @FXML
    private Text tripAvgFuelConsumption;
    @FXML
    private Text tripAvgVelocity;
    @FXML
    private Text tripTime;
    @FXML
    private Text tripMaxVelocity;
    @FXML
    private Text tripID;
    @FXML
    private TextField concatSecondID;
    @FXML
    private TextField concatFirstID;
    @FXML
    private TextField deleteID;

    /**
     * Pole przechowujace obiekt, ktory sluzy do interakcji z baza danych.
     */
    private IODataBase ioDataBase;

    /**
     * Pole przechowuje liste wycieczek pobrana z bazy danych.
     */
    private ArrayList<Trip> trips;

    /**
     * Pole przechowuje numer aktualnie wyswietlanej wycizeczki z listy.
     */
    private int i;

    /**
     * Pole przechowuje nazwe tabeli z ktorej pobrane zostana wycieczki (trips)
     */
    private String path;

    /**
     * Metoda dziala analogicznie do konstruktora, ale jest wywolywana po inicjalizacji
     * obiektow reprezentujacych elementy graficzne.
     * Inicjalizuje wartosci domyslne pol, wczytuje wycieczki z bazy danych do listy.
     */
    public void initialize() {
        ioDataBase = new IODataBase("root", "");
        path = "trips";
        trips = new ArrayList<>();
        i = 0;
        trips = (ArrayList<Trip>) ioDataBase.load(path);
        if (trips.size() > 0) {
            display(i);
        }
    }

    /**
     * Metoda wyswietla wycieczke o numerze i w oknie.
     * @param i Numer wycieczki do wyswietlenia.
     */
    private  void display(int i) {
        tripID.setText("" + trips.get(i).getId());
        tripDate.setText("" + trips.get(i).getDate());
        tripTime.setText(String.format("%.2f", (double)(trips.get(i).getTime() / 1_000) / 60) + " min");
        tripAvgFuelConsumption.setText(String.format("%.2f", trips.get(i).getAvgFuelConsumption()) + " l/km");
        tripAvgVelocity.setText(String.format("%.2f", trips.get(i).getAvgVelocity()) + " km/h");
        tripMaxVelocity.setText(String.format("%.2f", trips.get(i).getMaxVelocity()) + " km/h");
    }

    /**
     * Metoda dekrementuje wartosc i, sprawdzajc przy tym czy nie wychodzi prz tym ponizej 1.
     * Wywoluje metode display dla aktualnego i.
     */
    public void previous() {
        if (i > 0) {
            i--;
            display(i);
        }
    }

    /**
     * Metoda inkrementuje wartosc i, sprawdzajc przy tym czy nie wychodzi prz tym poza zakres listy.
     * Wywoluje metode display dla aktualnego i.
     */
    public void next() {
        if (i + 1 < trips.size()) {
            i++;
            display(i);
        }
    }

    /**
     * Metoda jest wywolywana po nacisnieciu przycisku "Usun wszystkie wycieczki".
     * Usuwa wszystkie wycieczki z bazy danych i ponownnie wczytuje liste.
     */
    public void deleteAll() {
        ioDataBase.deleteAll(path);
        trips = (ArrayList<Trip>) ioDataBase.load(path);
        i = 0;

        clean();
    }

    /**
     * Metoda jest wywolywana po nacisnieciu przycisku "Usun wycieczke o numerze ID".
     * Usuwa wycieczke o numerze ID podanym w polu tekstowym i ponownnie wczytuje liste.
     */
    public void deleteByID() {
        try {
            ioDataBase.delete(path, Integer.parseInt(deleteID.getText()));
            trips = (ArrayList<Trip>) ioDataBase.load(path);
            i = 0;

            if (trips.size() > 0) {
                display(i);
            } else {
                clean();
            }
        } catch (NumberFormatException | IDNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Metoda jest wywolywana po nacisnieciu przycisku "Polacz wycieczki o numerach ID".
     * Laczy wycieczki o numerach ID podanych w polach tekstowych i ponownnie wczytuje liste.
     */
    public void concatTripsByID() {
        try {
            ioDataBase.concatRows(path, Integer.parseInt(concatFirstID.getText()), Integer.parseInt(concatSecondID.getText()));
            trips = (ArrayList<Trip>) ioDataBase.load(path);
            i = 0;

            if (trips.size() > 0) {
                display(i);
            } else {
                clean();
            }
        } catch (NumberFormatException | IDNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Metoda wyswietla informacje domyslne w sytuacji gdy lista wycieczek jest pusta.
     */
    public void clean() {
        tripID.setText("Brak zarchiwizowanych wycieczek");
        tripDate.setText("Brak");
        tripTime.setText("0 min");
        tripAvgFuelConsumption.setText("0 l/km");
        tripAvgVelocity.setText("0 km/h");
        tripMaxVelocity.setText("0 km/h");
    }
}
