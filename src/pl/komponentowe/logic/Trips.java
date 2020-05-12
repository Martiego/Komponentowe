package pl.komponentowe.logic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Trips extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../presentation/trips.fxml"));
        stage.setTitle("Wycieczki");
        stage.setResizable(false);
        Scene scene = new Scene(root, 720, 405);

        stage.setScene(scene);
        stage.show();
    }
}
