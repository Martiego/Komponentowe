package pl.komponentowe.logic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Patryk Kolanek
 * @author Kacper Swiercz
 *
 * Klasa odpowiedzialna za uruchamianie okna z widokiem ustawien aplikacji.
 */
public class Settings extends Application {
    /**
     * Pole reprezentuje kontroler glownego okna aplikacji
     */
    private MainController mainController;

    /**
     * Konstruktor klasy, używany do inicjalizacji pola controller.
     * @param mainController Inicjalizuje pole controller
     */
    public Settings(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Metoda wymagana przez implementacje interfejsu Application z biblioteki JavaFX
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../presentation/settings.fxml"));
        Parent root = loader.load();

        stage.setTitle("Ustawienia");
        stage.setResizable(false);

        SettingsController settingsController = loader.getController();
        settingsController.setController(mainController);
        settingsController.updateText();

        Scene scene = new Scene(root, 720, 405);
        scene.getStylesheets().add(getClass().getResource("../presentation/style.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }
}
