package pl.komponentowe.logic;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.komponentowe.data.IOXml;
import pl.komponentowe.data.Settings;

import java.io.File;

public class SettingsController {
    @FXML
    private Text path;

    @FXML
    private TextField maxFuel;

    @FXML
    private TextField maxOil;

    @FXML
    private TextField fuelAmount;

    @FXML
    private TextField oilAmount;

    private File file;

    private IOXml ioXml;

    private MainController mainController;

    public SettingsController() {
        ioXml = new IOXml();
    }

    public void setController(MainController mainController) {
        this.mainController = mainController;
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
            settings.setMileage(mainController.getDashboard().getMileage());
            settings.setMaxFuel(mainController.getDashboard().getMaxFuel());
            settings.setMaxOil(mainController.getDashboard().getMaxOil());

            ioXml.save(file.getAbsolutePath(), settings);
        }
    }

    @FXML
    public void loadSettings() {
        if (null != file) {
            Settings settings = (Settings) ioXml.load(file.getAbsolutePath());
            mainController.getDashboard().setMileage(settings.getMileage());
        }
    }


    public void setMaxFuel() {
        try {
            mainController.getDashboard().getFuel().setMaxAmount(Integer.parseInt(maxFuel.getText()));
            maxFuel.setText("");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setMaxOil() {
        try {
            mainController.getDashboard().getOil().setMaxAmount(Integer.parseInt(maxOil.getText()));
            maxOil.setText("");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void fillFuel() {
        try {
            mainController.getDashboard().getFuel().fill(Integer.parseInt(fuelAmount.getText()));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void fillOil() {
        try {
            mainController.getDashboard().getFuel().fill(Integer.parseInt(oilAmount.getText()));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
