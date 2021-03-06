package pl.komponentowe.logic;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.komponentowe.data.IOXml;
import pl.komponentowe.data.Settings;
import pl.komponentowe.logic.exceptions.NegativeValueException;

import java.io.File;
/**
 * Klasa kontrolera dla okna ustawien aplikacji.
 *
 * @author Patryk Kolanek
 * @author Kacper Swiercz
 */
public class SettingsController {
    /**
     * Pole reprezentuje tekst oznaczajacy sciezke do pliku wybrana za pomoca przycisku "Wybierz plik".
     */
    @FXML
    private Text path;

    /**
     * Pole reprezentuje pole tekstowe gdzie mozna wprowadzic maksymalna ilosc paliwa.
     */
    @FXML
    private TextField maxFuel;

    /**
     * Pole reprezentuje pole tekstowe gdzie mozna wprowadzic maksymalna ilosc oleju.
     */
    @FXML
    private TextField maxOil;

    /**
     * Pole reprezentuje pole tekstowe gdzie mozna wprowadzic ilosc paliwa do dolania.
     */
    @FXML
    private TextField fuelAmount;

    /**
     * Pole reprezentuje pole tekstowe gdzie mozna wprowadzic ilosc oleju do dolania.
     */
    @FXML
    private TextField oilAmount;

    /**
     * Pole reprezentuje tekst oznaczajacy maksymalna ilosc paliwa.
     */
    @FXML
    private Text actualMaxFuel;

    /**
     * Pole reprezentuje tekst oznaczajacy maksymalna ilosc oleju.
     */
    @FXML
    private Text actualMaxOil;
    /**
     * Pole przechowuje obiekt reprezentujacy plik, ktory zawiera ustawienia.
     */
    private File file;
    /**
     * Pole przechowuje obiekt, ktory sluzy do interakcji z plikiem w formacie xml (zapisywanie, wczytywanie).
     */
    private final IOXml ioXml;
    /**
     * Pole przechowuje odwolanie do kontrolera glownego okna aplikacji.
     */
    private MainController mainController;

    /**
     * Konstruktor inicjalizuje obiekt ioXml.
     */
    public SettingsController() {
        ioXml = new IOXml();
    }

    public void setController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Metoda wywolywana po kliknieciu przycisku "Wybierz plik".
     * Sluzy wybraniu pliku z urzadzenia, w ktorym zapisane zostana lub z ktorego wczytane zostana ustawienia.
     */
    @FXML
    public void openFile() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Wybierz plik");

        file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            path.setText(file.getAbsolutePath());
        }
    }

    /**
     * Metoda wywolywana po kliknieciu przycisku "Zapisz".
     * Sluzy zapisaniu aktualnych ustawien w wybranym wczesniej pliku.
     * Bez wybrania pliku nie mam mozliwosci zapisania ustawien inaczej niz przez zamkniecie aplikacji.
     */
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
                settings.setOdometer1(mainController.getDashboard().getOdometer1());
                settings.setOdometer2(mainController.getDashboard().getOdometer2());

                ioXml.save(file.getAbsolutePath(), settings);
            } catch (NegativeValueException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }
    /**
     * Metoda wywolywana po kliknieciu przycisku "Wczytaj".
     * Sluzy wybraniu pliku z urzadzenia, z ktorego wczytane zostana ustawienia.
     * Bez wybrania pliku nie mam mozliwosci wczytania ustawien inaczej niz przez ponowne otwarcie aplikacji.
     */
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
                mainController.getDashboard().setOdometer1(settings.getOdometer1());
                mainController.getDashboard().setOdometer2(settings.getOdometer2());
            }

            updateText();
        }
    }

    /**
     * Metoda wywolywana po kliknieciu przycisku "Zmien" po lewej stronie.
     * Zmienia maksymalna ilosc paliwa na ta podana w polu tekstowym.
     */
    public void setMaxFuel() {
        try {
            mainController.getDashboard().getFuel().setMaxAmount(Double.parseDouble(maxFuel.getText()));
            maxFuel.setText("");
            updateText();
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Metoda wywolywana po kliknieciu przycisku "Zmien" po prawej stronie.
     * Zmienia maksymalna ilosc oleju na ta podana w polu tekstowym.
     * Wartosc jest przyjmowana w litrach jako liczba rzeczywista (separator jest kropka).
     */
    public void setMaxOil() {
        try {
            mainController.getDashboard().getOil().setMaxAmount(Double.parseDouble(maxOil.getText()));
            maxOil.setText("");
            updateText();
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Metoda wywolywana po kliknieciu przycisku "Zatankuj" po lewej stronie.
     * Dodaje do aktualnej ilosci paliwa wartosc podana w polu tekstowym.
     * Wartosc jest przyjmowana w litrach jako liczba rzeczywista (separator jest kropka).
     */
    public void fillFuel() {
        try {
            mainController.getDashboard().getFuel().fill(Double.parseDouble(fuelAmount.getText()));
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Metoda wywolywana po kliknieciu przycisku "Dolej" po lewej stronie.
     * Dodaje do aktualnej ilosci oleju wartosc podana w polu tekstowym.
     * Wartosc jest przyjmowana w litrach jako liczba rzeczywista (separator jest kropka).
     */
    public void fillOil() {
        try {
            mainController.getDashboard().getOil().fill(Double.parseDouble(oilAmount.getText()));
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Metoda aktualizuje tekst ktory wyswietla maksymalna ilosc oleju oraz maksymalna ilosc paliwa.
     */
    public void updateText() {
        actualMaxFuel.setText(mainController.getDashboard().getFuel().getMaxAmount() + " l");
        actualMaxOil.setText(mainController.getDashboard().getOil().getMaxAmount() + " l");
    }
}
