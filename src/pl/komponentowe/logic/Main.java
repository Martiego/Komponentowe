package pl.komponentowe.logic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.komponentowe.data.IODataBase;
import pl.komponentowe.data.Trip;

import java.util.ArrayList;

/**
 * In order to run this program you need to add XStream library to your project. You can find it here: "http://x-stream.github.io/"
 * Also you need JavaFX 14. You can find it here: https://gluonhq.com/products/javafx/
 */

public class Main extends Application {
    private static String PATH_TO_FILE_XML = "C:\\Users\\Desktop\\Semestr 4 2020\\Komponentowe\\plik1.xml";

    public static void main(String[] args)  {
        IODataBase ioDataBase = new IODataBase("root", "");
        ArrayList<Trip> trips = new ArrayList<>();

        launch(args);
    }

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

            controller.stop();
        });
    }

}
