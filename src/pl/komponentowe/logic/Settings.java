package pl.komponentowe.logic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Settings extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../presentation/settings.fxml"));
        stage.setTitle("Ustawienia");
        stage.setResizable(false);
        Scene scene = new Scene(root, 720, 405);

        stage.setScene(scene);
        stage.show();
    }
}
