package pl.komponentowe.logic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pl.komponentowe.data.IODataBase;
import pl.komponentowe.data.Trip;

import java.util.ArrayList;

public class TripsController {
    @FXML
    private Button previous;
    @FXML
    private Button next;
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
    private Button delete;
    @FXML
    private Button deleteAll;
    @FXML
    private Button concat;
    @FXML
    private TextField concatSecondID;
    @FXML
    private TextField concatFirstID;
    @FXML
    private TextField deleteID;

    IODataBase ioDataBase;
    ArrayList<Trip> trips;
    int i;


    public void initialize() {
        ioDataBase = new IODataBase("root", "");
        trips = new ArrayList<>();
        i = 0;
        trips = ioDataBase.load("trips");
        if (trips.size() > 0) {
            display(i);
        }
    }

    private  void display(int i) {
        tripID.setText("" + (trips.get(i).getId()));
        tripDate.setText("" + (trips.get(i).getDate()));
        tripTime.setText("" + (trips.get(i).getTime() / 60_000));
        tripAvgFuelConsumption.setText("" + (trips.get(i).getAvgFuelConsumption()));
        tripAvgVelocity.setText("" + (trips.get(i).getAvgVelocity()));
        tripMaxVelocity.setText("" + (trips.get(i).getMaxVelocity()));
    }

    public void previous() {
        if (i > 0) {
            i--;
            display(i);
        }
    }

    public void next(ActionEvent actionEvent) {
        if (i + 1 < trips.size()) {
            i++;
            display(i);
        }
    }
}
