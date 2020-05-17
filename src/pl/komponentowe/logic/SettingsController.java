package pl.komponentowe.logic;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.komponentowe.data.IOXml;
import pl.komponentowe.data.Settings;

import java.io.File;
import java.util.ArrayList;

public class SettingsController {
    @FXML
    private Text path;

    private File file;

    private Stage stage;
    private FXMLLoader fxmlLoader;
    private IOXml<Settings> ioXml;

    public SettingsController() {
        fxmlLoader = new FXMLLoader(getClass().getResource("../presentation/sample.fxml"));
        ioXml = new IOXml<>();
    }

    @FXML
    public void openFile() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Wybierz plik");

        file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            path.setText(file.getAbsolutePath());
        }
    }

    @FXML
    public void saveSettings() {
        Settings settings = new Settings();
        settings.setMileage(((Controller)fxmlLoader.getController()).getDashboard().getMileage());
        settings.setMaxFuel(((Controller)fxmlLoader.getController()).getDashboard().getMaxFuel());
        settings.setMaxOil(((Controller)fxmlLoader.getController()).getDashboard().getMaxOil());


        ArrayList<pl.komponentowe.data.Settings> settingsArrayList = new ArrayList<>();
        settingsArrayList.add(settings);
        
        ioXml.save(file.getAbsolutePath(), settingsArrayList);

    }
}
