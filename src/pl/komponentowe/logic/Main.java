package pl.komponentowe.logic;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import pl.komponentowe.data.IODataBase;
import pl.komponentowe.data.Trip;

import java.util.Date;

/**
 * In order to run this program you need to add XStream library to your project. You can find it here: "http://x-stream.github.io/"
 * Also you need JavaFX 14. You can find it here: https://gluonhq.com/products/javafx/
 */

public class Main extends Application {
    private static String PATH_TO_FILE_XML = "C:\\Users\\Desktop\\Semestr 4 2020\\Komponentowe\\plik1.xml";

    public static void main(String[] args)  {
        IODataBase<Trip> ioDataBase = new IODataBase<>("root", "");
        ioDataBase.save("trips", new Trip(new Date(), 12.0, 20.0, 50.0, 724543));
        // Fragment responsible for 
//        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../presentation/sample.fxml"));
        stage.setTitle("Brum brum");
        stage.setResizable(false);
        Scene scene = new Scene(root, 960, 540);
        scene.getStylesheets().add(getClass().getResource("../presentation/style.css").toExternalForm());

//        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent keyEvent) {
//                System.out.println(keyEvent.getCode());
//            }
//        });

        // FIXME: 11.05.2020
        // trzeba zrobić tak, że aplikacja się zamyka po kliknięciu w x
        stage.setScene(scene);
        stage.show();
    }
}
