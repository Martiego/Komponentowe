package pl.komponentowe;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Controller {
    @FXML
    private Button button;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Polygon left;

    @FXML
    private Polygon right;

    public void method() {
        System.out.println("test");
        if ("test".equals(button.getText())) {
            button.setText("test Testowy");
            left.setFill(Color.BLACK);
            right.setFill(Color.BLACK);

        } else {
            button.setText("test");
            left.setFill(Color.GREEN);
            right.setFill(Color.GREEN);
        }

        button.setTextFill(colorPicker.getValue());
    }
}
