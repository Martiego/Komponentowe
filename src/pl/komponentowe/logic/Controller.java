package pl.komponentowe.logic;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

public class Controller {
    @FXML
    private Polygon leftIndicator;

    @FXML
    private Polygon rightIndicator;

    @FXML
    private Text speedometer;

    private boolean isRightHold;
    private boolean isLeftHold;

    private Dashboard dashboard;

    private Thread rightIndicatorThread;
    private Thread leftIndicatorThread;

    public Controller() {
        dashboard = new Dashboard();

        rightIndicatorThread = new Thread();
        leftIndicatorThread = new Thread();
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
            dashboard.getSpeedometer().accelerate();
            speedometer.setText(dashboard.getSpeedometer().getActualVelocity() + " km/h");
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
