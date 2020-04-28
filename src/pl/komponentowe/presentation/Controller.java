package pl.komponentowe.presentation;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

import java.util.AbstractCollection;
import java.util.Collection;

public class Controller {
    @FXML
    private Button button;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Polygon left;

    @FXML
    private Polygon right;

    @FXML
    private Pane pane;

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

    public void onClickKey() {
        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println(keyEvent.getCharacter());
            }
        });
    }
}
