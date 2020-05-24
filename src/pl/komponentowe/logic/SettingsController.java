package pl.komponentowe.logic;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.komponentowe.data.IODataBase;
import pl.komponentowe.data.IOXml;
import pl.komponentowe.data.Settings;
import pl.komponentowe.data.Trip;
import pl.komponentowe.logic.exceptions.NegativeValueException;

import java.io.File;
import java.util.ArrayList;

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

    @FXML
    private Text actualMaxFuel;

    @FXML
    private Text actualMaxOil;

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
            try {
                Settings settings = new Settings();
                settings.setMileage(mainController.getDashboard().getMileage());
                settings.setMaxFuel(mainController.getDashboard().getFuel().getMaxAmount());
                settings.setActualFuel(mainController.getDashboard().getFuel().checkLevel() * settings.getMaxFuel());
                settings.setMaxOil(mainController.getDashboard().getOil().getMaxAmount());
                settings.setActualOil(mainController.getDashboard().getOil().checkLevel() * settings.getMaxOil());

                ioXml.save(file.getAbsolutePath(), settings);
            } catch (NegativeValueException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    @FXML
    public void loadSettings() {
        if (null != file) {
            Settings settings = (Settings) ioXml.load(file.getAbsolutePath());

            if (null != settings) {
                mainController.getDashboard().setMileage(settings.getMileage());
                mainController.getDashboard().getFuel().setMaxAmount(settings.getMaxFuel());
                mainController.getDashboard().getFuel().fill(settings.getActualFuel());
                mainController.getDashboard().getOil().setMaxAmount(settings.getMaxOil());
                mainController.getDashboard().getOil().fill(settings.getActualOil());
            }

            updateText();
        }
    }


    public void setMaxFuel() {
        try {
            mainController.getDashboard().getFuel().setMaxAmount(Integer.parseInt(maxFuel.getText()));
            maxFuel.setText("");
            updateText();
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setMaxOil() {
        try {
            mainController.getDashboard().getOil().setMaxAmount(Integer.parseInt(maxOil.getText()));
            maxOil.setText("");
            updateText();
        } catch (NumberFormatException ex) {
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
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateText() {
        actualMaxFuel.setText(mainController.getDashboard().getFuel().getMaxAmount() + " l");
        actualMaxOil.setText(mainController.getDashboard().getOil().getMaxAmount() + " l");
    }
}
