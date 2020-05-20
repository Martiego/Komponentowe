package pl.komponentowe.logic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Settings extends Application {
    private MainController mainController;

    public Settings(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../presentation/settings.fxml"));
        Parent root = loader.load();

        stage.setTitle("Ustawienia");
        stage.setResizable(false);

        SettingsController settingsController = loader.getController();
        settingsController.setController(mainController);

        Scene scene = new Scene(root, 720, 405);
        scene.getStylesheets().add(getClass().getResource("../presentation/style.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }
}
