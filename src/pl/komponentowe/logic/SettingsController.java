package pl.komponentowe.logic;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.komponentowe.data.IOXml;
import pl.komponentowe.data.Settings;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SettingsController {
    @FXML
    private Text path;

    @FXML
    private TextField maxFuel;

    @FXML
    private TextField maxOil;

    private File file;

    private FXMLLoader fxmlLoader;
    private IOXml ioXml;

    private Controller controller;

    public SettingsController() {
        fxmlLoader = new FXMLLoader(getClass().getResource("../presentation/sample.fxml"));

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ioXml = new IOXml();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @FXML
    public void openFile() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Wybierz plik");

        file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            path.setText(file.getAbsolutePath());
        }
    }

    @FXML
    public void saveSettings() {
        if (null != file) {
            Settings settings = new Settings();
            settings.setMileage(controller.getDashboard().getMileage());
            settings.setMaxFuel(controller.getDashboard().getMaxFuel());
            settings.setMaxOil(controller.getDashboard().getMaxOil());

            ioXml.save(file.getAbsolutePath(), settings);
        }
    }

    @FXML
    public void loadSettings() {
        if (null != file) {
            Settings settings = (Settings) ioXml.load(file.getAbsolutePath());
            controller.getDashboard().setMileage(settings.getMileage());
        }
    }


    public void setMaxFuel() {
        try {
            controller.getDashboard().getFuel().setMaxAmount(Integer.parseInt(maxFuel.getText()));
            maxFuel.setText("");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setMaxOil() {
        try {
            controller.getDashboard().getOil().setMaxAmount(Integer.parseInt(maxOil.getText()));
            maxOil.setText("");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
