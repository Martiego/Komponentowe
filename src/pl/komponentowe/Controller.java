package pl.komponentowe;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;

import java.awt.*;

public class Controller {
    @FXML
    private Button button;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Polygon l;

    @FXML
    private Polygon r;

    public void method() {
        System.out.println("test");
        if ("test".equals(button.getText())) {
            button.setText("test Testowy");

        } else {
            button.setText("test");
        }

        button.setTextFill(colorPicker.getValue());
    }
}
