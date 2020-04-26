package pl.komponentowe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.komponentowe.data.IOXml;
import pl.komponentowe.logic.Indicators;
import pl.komponentowe.logic.Trip;

import java.util.*;

/**
 * In order to run this program you need to add XStream library to your project. You can find it here: "http://x-stream.github.io/"
 */

public class Main extends Application {
    private static String PATH_TO_FILE_XML = "C:\\Users\\Patryk\\Desktop\\Semestr 4 2020\\Kompo\\pliczek1.xml";

    public static void main(String[] args) {
        System.out.println("costam");
        ArrayList<Trip> trips = new ArrayList<>();
        try {
            trips.add(new Trip(new Date((new Date()).getTime() - 10000000000L), 7.5, 55, 20, 22000020));
            trips.add(new Trip(new Date((new Date()).getTime() - 2000000000), 9.5, 55, 80, 42030000));
            trips.add(new Trip(new Date((new Date()).getTime() - 30000000000L), 2.5, 15, 30, 12032000));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            System.out.println("End of adding trips");
        }


        IOXml<ArrayList<Trip>> ioXml = new IOXml<>();
        ioXml.save(PATH_TO_FILE_XML, trips);

        ArrayList<Trip> trips1 = ioXml.load(PATH_TO_FILE_XML);
        System.out.println(trips1);

        Indicators.LEFT.turnOn();
        Indicators.LEFT.turnOff();

        Indicators.RIGHT.turnOn();
        Indicators.RIGHT.turnOff();

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("presentation/sample.fxml"));
        stage.setTitle("Brum brum");
        stage.setResizable(false);
        stage.setScene(new Scene(root, 600, 475));
        stage.show();
    }
}
