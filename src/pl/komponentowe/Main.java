package pl.komponentowe;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import pl.komponentowe.data.IOXml;
import pl.komponentowe.logic.Trip;

import java.util.*;

/**
 * In order to run this program you need to add XStream library to your project. You can find it here: "http://x-stream.github.io/"
 * Also you need JavaFX 14. You can find it here: https://gluonhq.com/products/javafx/
 */

public class Main extends Application {
    private static String PATH_TO_FILE_XML = "C:\\Users\\Desktop\\Semestr 4 2020\\Komponentowe\\plik1.xml";

    public static void main(String[] args)  {

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

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("presentation/sample.fxml"));
        stage.setTitle("Brum brum");
        stage.setResizable(false);
        Scene scene = new Scene(root, 600, 475);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println(keyEvent.getText());
            }
        });
        stage.setScene(scene);
        stage.show();
    }
}
