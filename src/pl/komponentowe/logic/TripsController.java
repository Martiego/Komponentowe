package pl.komponentowe.logic;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pl.komponentowe.data.IODataBase;
import pl.komponentowe.data.Trip;

import java.util.ArrayList;

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

    private IODataBase ioDataBase;
    private ArrayList<Trip> trips;
    private int i;

    private String kmPerHour;
    private String path;


    public void initialize() {
        ioDataBase = new IODataBase("root", "");
        kmPerHour = " km / h";
        path = "trips";
        trips = new ArrayList<>();
        i = 0;
        trips = (ArrayList<Trip>) ioDataBase.load(path);
        if (trips.size() > 0) {
            display(i);
        }
    }

    private  void display(int i) {
        tripID.setText("" + trips.get(i).getId());
        tripDate.setText("" + trips.get(i).getDate());
        tripTime.setText(String.format("%.2f", (double)(trips.get(i).getTime() / 1_000) / 60) + " min");
        tripAvgFuelConsumption.setText(String.format("%.2f", trips.get(i).getAvgFuelConsumption()) + " l / km");
        tripAvgVelocity.setText(String.format("%.2f", trips.get(i).getAvgVelocity()) + kmPerHour);
        tripMaxVelocity.setText(String.format("%.2f", trips.get(i).getMaxVelocity()) + kmPerHour);
    }

    public void previous() {
        if (i > 0) {
            i--;
            display(i);
        }
    }

    public void next() {
        if (i + 1 < trips.size()) {
            i++;
            display(i);
        }
    }

    public void deleteAll() {
        ioDataBase.deleteAll(path);
        trips = (ArrayList<Trip>) ioDataBase.load(path);
        i = 0;

        clean();
    }

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
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

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
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void clean() {
        tripID.setText("Brak zarchiwizowanych wycieczek");
        tripDate.setText("Brak");
        tripTime.setText("0 min");
        tripAvgFuelConsumption.setText("0 l / km");
        tripAvgVelocity.setText("0 km / h");
        tripMaxVelocity.setText("0 km / h");
    }
}
