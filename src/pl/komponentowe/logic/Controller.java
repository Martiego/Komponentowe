package pl.komponentowe.logic;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller {
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

    private boolean isRightHold;
    private boolean isLeftHold;

    private Dashboard dashboard;

    private Thread rightIndicatorThread;
    private Thread leftIndicatorThread;
    private Thread mainThread;

    private String kmPerHour;

    public Controller() {
        dashboard = new Dashboard();

        kmPerHour = " km/h";

        rightIndicatorThread = new Thread();
        leftIndicatorThread = new Thread();

        mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(200);
                        dashboard.getOnBoardComputer().decelerate(1);
                        speedometer.setText(String.format("%.1f", dashboard.getOnBoardComputer().getActualVelocity()) + kmPerHour);
                        avgVelocity.setText(String.format("%.1f", dashboard.getOnBoardComputer().getAvgVelocity()) + kmPerHour);
                        maxVelocity.setText(String.format("%.1f", dashboard.getOnBoardComputer().getMaxVelocity()) + kmPerHour);
                        time.setText(String.format("%.2f", (double)(dashboard.getOnBoardComputer().getTime() / 1_000) / 100) + " min");
                        street.setText(String.format("%.3f", dashboard.getOnBoardComputer().getStreet()) + " km");
                        avgFuelConsumption.setText(String.format("%.1f", dashboard.getOnBoardComputer().getAvgFuelConsumption()) + " l/km");
                    } catch (Exception ex) {
                    }
                }
            }
        });

        mainThread.start();
    }

    @FXML
    public void keyPressedController(KeyEvent event) {

        if (KeyCode.RIGHT == event.getCode()) {
            isRightHold = true;

            if (!rightIndicatorThread.isAlive()) {
                turnRight();
            }
        } else if (KeyCode.LEFT == event.getCode()) {
            isLeftHold = true;

            if (!leftIndicatorThread.isAlive()) {
                turnLeft();
            }
        } else if (KeyCode.UP == event.getCode()) {
            dashboard.getOnBoardComputer().accelerate();
            speedometer.setText(String.format("%.1f",dashboard.getOnBoardComputer().getActualVelocity()) + " km/h");
        } else if (KeyCode.DOWN == event.getCode()) {
            dashboard.getOnBoardComputer().decelerate(3);
            speedometer.setText(String.format("%.1f", dashboard.getOnBoardComputer().getActualVelocity()) + " km/h");
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

    public void openSettings() throws Exception {
        Settings settings = new Settings();
        settings.start(new Stage());
    }

    private void turnRight() {
        rightIndicatorThread = new Thread(() -> {
            while (isRightHold) {
                rightIndicator.setFill(Color.GREEN);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                rightIndicator.setFill(Color.BLACK);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        rightIndicatorThread.start();
    }

    private void turnLeft() {
        leftIndicatorThread = new Thread(() -> {
            while (isLeftHold) {
                leftIndicator.setFill(Color.GREEN);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                leftIndicator.setFill(Color.BLACK);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        leftIndicatorThread.start();
    }
}
