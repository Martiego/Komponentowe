package pl.komponentowe.logic;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class SettingsController {
    @FXML
    private Text path;

    private File file;

    private Stage stage;
    private FXMLLoader fxmlLoader;

    public SettingsController() {
        fxmlLoader = new FXMLLoader(getClass().getResource("../presentation/sample.fxml"));
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
        ((Controller)fxmlLoader.getController()).getDashboard();
    }
}
