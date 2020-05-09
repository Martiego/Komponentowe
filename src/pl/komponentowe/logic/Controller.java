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
    protected Polygon rightIndicator;

    @FXML
    protected Text speedometer;

    protected boolean isHold;

    private Dashboard dashboard;

    private Thread thread;

    public Controller() {
        dashboard = new Dashboard();

        thread = new Thread(() -> {
            while (isHold) {
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
    }

    @FXML
    public void keyPressedController(KeyEvent event) {

        if (KeyCode.RIGHT == event.getCode()) {
            isHold = true;
            if (!thread.isAlive()) {

                thread = new Thread(() -> {
                    while (isHold) {
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

                thread.start();
                System.out.println("New thread " + thread.getName());
            }
        }
    }

    @FXML
    public void keyReleasedController(KeyEvent event) {
        if (KeyCode.RIGHT == event.getCode()) {
            isHold = false;
        }
    }
}
