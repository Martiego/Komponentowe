package pl.komponentowe.logic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.komponentowe.data.IODataBase;
import pl.komponentowe.data.IOXml;
import pl.komponentowe.data.Settings;
import pl.komponentowe.data.Trip;
import pl.komponentowe.logic.exceptions.NegativeValueException;

import java.util.ArrayList;

/**
 * Klasa odpowiedzialna za uruchamianie okno z graficznym widokiem aplikacji.
 *
 * @author Patryk Kolanek
 * @author Kacper Swiercz
 */
public class GraphicalUserInterface extends Application {

    /**
     * Metoda wymagana przez implementacje interfejsu Application z biblioteki JavaFx.
     * Umozliwia zainicjalizowanie okna aplikacji.
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../presentation/main.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle("Brum brum");
        stage.setResizable(false);
        Scene scene = new Scene(root, 960, 540);
        scene.getStylesheets().add(getClass().getResource("../presentation/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            MainController controller = (MainController) fxmlLoader.getController();
            Trip trip = controller.makeTrip();
            IODataBase ioDataBase = new IODataBase("root", "");
            ArrayList<Trip> arrayList = new ArrayList<>();
            arrayList.add(trip);
            ioDataBase.save("trips", arrayList);

            try {
                Settings settings = new Settings();
                settings.setMileage(controller.getDashboard().getMileage());
                settings.setMaxFuel(controller.getDashboard().getFuel().getMaxAmount());
                settings.setActualFuel(controller.getDashboard().getFuel().checkLevel() * settings.getMaxFuel());
                settings.setMaxOil(controller.getDashboard().getOil().getMaxAmount());
                settings.setActualOil(controller.getDashboard().getOil().checkLevel() * settings.getMaxOil());

                new IOXml().save("settings.xml", settings);
            } catch (NegativeValueException ex) {
                System.out.println(ex.getMessage());
            }
            
            controller.stop();
        });
    }
}
