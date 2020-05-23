package pl.komponentowe.logic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pl.komponentowe.data.IOXml;
import pl.komponentowe.data.Trip;
import pl.komponentowe.data.Settings;

public class MainController {

    @FXML
    public ToggleButton headlights;
    @FXML
    public ToggleButton highBeams;
    @FXML
    public ToggleButton fogLights;
    @FXML
    public ToggleButton runningLights;
    @FXML
    public ToggleButton cruiseControl;
    @FXML
    private ToggleButton checkEngine;
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
    private Text odometer1;
    @FXML
    private Text odometer2;
    @FXML
    private ProgressBar fuel;
    @FXML
    private ProgressBar temperature;


    private boolean isRightHold;
    private boolean isLeftHold;

    private double actualVelocity;

    private Dashboard dashboard;

    private Thread indicatorThread;
    private Thread mainThread;

    private final String kmPerHour;
    private final String km;

    public MainController() {
        Settings settings = (Settings)(new IOXml().load("settings.xml"));

        if (null == settings) {
            dashboard = new Dashboard(0,30, 30, 5, 5);
        } else {
            dashboard = new Dashboard(settings.getMileage(), settings.getMaxFuel(), settings.getActualFuel(), settings.getMaxOil(), settings.getActualOil());
        }

        kmPerHour = " km/h";
        km = " km";

        indicatorThread = new Thread();

        mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (dashboard.isRunning()) {
                    try {
                        Thread.sleep(200);

                        checkEngine(!dashboard.getOil().isEnough());

                        if (cruiseControl.isSelected()) {
                            if (dashboard.getActualVelocity() > actualVelocity) {
                                dashboard.decelerate(2);
                            } else {
                                dashboard.accelerate();
                            }
                        } else {
                            dashboard.decelerate(1);
                        }

                        speedometer.setText(String.format("%.1f", dashboard.getActualVelocity()) + kmPerHour);
                        avgVelocity.setText(String.format("%.1f", dashboard.getAvgVelocity()) + kmPerHour);
                        maxVelocity.setText(String.format("%.1f", dashboard.getMaxVelocity()) + kmPerHour);
                        time.setText(String.format("%.2f", (double)(dashboard.getTime() / 1_000) / 60) + " min");
                        street.setText(String.format("%.3f", dashboard.getDistance()) + km);
                        avgFuelConsumption.setText(String.format("%.2f", dashboard.getAvgFuelConsumption()) + " l/km");
                        mileage.setText(String.format("%.1f", dashboard.getMileage()) + km);
                        odometer1.setText(String.format("%.1f", dashboard.getOdometer1()) + km);
                        odometer2.setText(String.format("%.1f", dashboard.getOdometer2()) + km);
                        fuel.setProgress(dashboard.getFuel().checkLevel());
                        temperature.setProgress(dashboard.getOil().getTemperature());
                    } catch (Exception ignored) {
                    }
                }
            }
        });

        mainThread.start();
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
        } else if (KeyCode.DOWN == event.getCode()) {
            dashboard.decelerate(3);
            cruiseControl.setSelected(false);
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
        pl.komponentowe.logic.Settings settings = new pl.komponentowe.logic.Settings(this);
        settings.start(new Stage());
    }

    @FXML
    public void openTrips() throws Exception {
        Stage stage = new Stage();
        stage.setTitle("Wycieczki");
        stage.setResizable(false);
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../presentation/trips.fxml")), 720, 405);
        scene.getStylesheets().add(getClass().getResource("../presentation/style.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void resetOdometer1() {
        dashboard.resetOdometer1();
    }

    @FXML
    public void resetOdometer2() {
        dashboard.resetOdometer2();
    }

    public void setActualVelocity() {
        actualVelocity = dashboard.getActualVelocity();
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
    
    public void aboutProgram() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../presentation/aboutProgram.fxml"));
            Stage stage = new Stage();
            stage.setTitle("O programie");
            Scene scene = new Scene(root, 480, 270);
            scene.getStylesheets().add(getClass().getResource("../presentation/style.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
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

    public void headlightsClick(ActionEvent actionEvent) {
        if (headlights.isSelected()) {
            headlights.getStyleClass().clear();
            headlights.getStyleClass().add("headlightsOn");
        } else {
            headlights.getStyleClass().clear();
            headlights.getStyleClass().add("headlights");
        }
    }

    public void highBeamsClick(ActionEvent actionEvent) {
        if (highBeams.isSelected()) {
            highBeams.getStyleClass().clear();
            highBeams.getStyleClass().add("highBeamsOn");
        } else {
            highBeams.getStyleClass().clear();
            highBeams.getStyleClass().add("highBeams");
        }
    }

    public void fogLightsClick(ActionEvent actionEvent) {
        if (fogLights.isSelected()) {
            fogLights.getStyleClass().clear();
            fogLights.getStyleClass().add("fogLightsOn");
        } else {
            fogLights.getStyleClass().clear();
            fogLights.getStyleClass().add("fogLights");
        }
    }

    public void runningLightsClick(ActionEvent actionEvent) {
        if (runningLights.isSelected()) {
            runningLights.getStyleClass().clear();
            runningLights.getStyleClass().add("runningLightsOn");
        } else {
            runningLights.getStyleClass().clear();
            runningLights.getStyleClass().add("runningLights");
        }
    }

    public void checkEngine(boolean turn) {
        if (turn) {
            checkEngine.getStyleClass().clear();
            checkEngine.getStyleClass().add("checkEngineOn");
        } else {
            checkEngine.getStyleClass().clear();
            checkEngine.getStyleClass().add("checkEngineOff");
        }
    }
}
