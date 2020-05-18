package pl.komponentowe.logic;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pl.komponentowe.data.Trip;

public class Controller {
    @FXML
    public Button headlights;

    @FXML
    public ToggleButton cruiseControl;

    @FXML
    private Polygon leftIndicator;

    @FXML
    private Polygon rightIndicator;

    @FXML
    private Text speedometer;

    @FXML
    private Text avgVelocity;

    @FXML
    private Text maxVelocity;

    @FXML
    private Text time;

    @FXML
    private Text street;

    @FXML
    private Text avgFuelConsumption;

    @FXML
    private Text mileage;

    @FXML
    private Text odometer;

    @FXML
    private ProgressBar fuel;

    private boolean isRightHold;
    private boolean isLeftHold;

    private Dashboard dashboard;

    private Thread indicatorThread;
    private Thread mainThread;

    private String kmPerHour;
    private String km;

    public Controller() {
        dashboard = new Dashboard();

        kmPerHour = " km/h";
        km = " km";

        indicatorThread = new Thread();

        mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (dashboard.isRunning()) {
                    try {
                        Thread.sleep(200);
                        if (!cruiseControl.isSelected()) {
                            dashboard.decelerate(1);
                        }
                        speedometer.setText(String.format("%.1f", dashboard.getActualVelocity()) + kmPerHour);
                        avgVelocity.setText(String.format("%.1f", dashboard.getAvgVelocity()) + kmPerHour);
                        maxVelocity.setText(String.format("%.1f", dashboard.getMaxVelocity()) + kmPerHour);
                        time.setText(String.format("%.2f", (double)(dashboard.getTime() / 1_000) / 60) + " min");
                        street.setText(String.format("%.3f", dashboard.getDistance()) + km);
                        avgFuelConsumption.setText(String.format("%.1f", dashboard.getAvgFuelConsumption()) + " l/km");
                        mileage.setText(String.format("%.1f", dashboard.getMileage()) + km);
                        odometer.setText(String.format("%.1f", dashboard.getOdometer()) + km);
                        fuel.setProgress(dashboard.getFuel().checkLevel());
                    } catch (Exception ex) {
                    }
                }
            }
        });

        mainThread.start();

//        headlights.getStylesheets().add("headlights2");

    }

    @FXML
    public void keyPressedController(KeyEvent event) {

        if (!indicatorThread.isAlive()) {
            if (KeyCode.RIGHT == event.getCode()) {
                isRightHold = true;
                turn('r');
            } else if (KeyCode.LEFT == event.getCode()) {
                isLeftHold = true;
                turn('l');
            }
        }

        if (KeyCode.UP == event.getCode()) {
            dashboard.accelerate();
            speedometer.setText(String.format("%.1f",dashboard.getActualVelocity()) + kmPerHour);
        } else if (KeyCode.DOWN == event.getCode()) {
            dashboard.decelerate(3);
            speedometer.setText(String.format("%.1f", dashboard.getActualVelocity()) + kmPerHour);
        }
    }

    @FXML
    public void keyReleasedController(KeyEvent event) {
        if (KeyCode.RIGHT == event.getCode()) {
            isRightHold = false;
        } else if (KeyCode.LEFT == event.getCode()) {
            isLeftHold = false;
        }
    }

    @FXML
    public void openSettings() throws Exception {
        Settings settings = new Settings(this);
        settings.start(new Stage());
    }

    @FXML
    public void openTrips() throws Exception {
        Trips trips = new Trips();
        trips.start(new Stage());
    }

    @FXML
    public void reset() {
        dashboard.resetOdometer();
    }

    private void turn(char direction) {
        indicatorThread = new Thread(() -> {

            Polygon indicator = new Polygon();
            if ('r' == direction) {
                indicator = rightIndicator;
            } else if ('l' == direction) {
                indicator = leftIndicator;
            }

            while (isRightHold || isLeftHold) {

                indicator.setFill(Color.GREEN);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                indicator.setFill(Color.BLACK);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        indicatorThread.start();
    }


    public Trip makeTrip() {
        return new Trip(dashboard.getDate(), dashboard.getAvgFuelConsumption(), dashboard.getAvgVelocity(), dashboard.getMaxVelocity(), dashboard.getTime());
    }

    public void stop() {
        dashboard.stop();
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }
}
