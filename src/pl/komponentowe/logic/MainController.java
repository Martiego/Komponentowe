package pl.komponentowe.logic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pl.komponentowe.data.IOXml;
import pl.komponentowe.data.Trip;
import pl.komponentowe.data.Settings;

import java.io.IOException;

/**
 * Klasa kontrolera dla glownego okna aplikacji, przechowuje obiekty reprezentujace elementy interfejsu graficznego
 * oraz metody, ktore sa przez nie wywolywane. Interpretuje tez przyciski wciskane przez uzytkownika na klawiaturze.
 *
 * @author Patryk Kolanek
 * @author Kacper Swiercz
 */
public class MainController {

    @FXML
    public ToggleButton headlights;
    @FXML
    public ToggleButton highBeams;
    @FXML
    public ToggleButton fogLights;
    @FXML
    public ToggleButton runningLights;
    @FXML
    public ToggleButton cruiseControl;
    @FXML
    private ToggleButton checkEngine;
    @FXML
    private Polygon leftIndicator;
    @FXML
    private Polygon rightIndicator;
    @FXML
    private Text speedometer;
    @FXML
    private Text avgVelocity;
    @FXML
    private Text maxVelocity;
    @FXML
    private Text time;
    @FXML
    private Text street;
    @FXML
    private Text avgFuelConsumption;
    @FXML
    private Text mileage;
    @FXML
    private Text odometer1;
    @FXML
    private Text odometer2;
    @FXML
    private ProgressBar fuel;
    @FXML
    private ProgressBar temperature;

    /**
     * Pole przechowuje informacje o tym czy prawa strzalka jest wcisnieta.
     */
    private boolean isRightHold;
    /**
     * Pole przechowuje informacje o tym czy lewa strzalka jest wcisnieta.
     */
    private boolean isLeftHold;
    /**
     *  Pole przechowuje wartosc predkosci pojazdu z chwili wlaczenia tempomatu w <b>km/h</b>.
     */
    private double actualVelocity;
    /**
     * Pole przechowuje obiekt deski rozdzielczej pojazdu.
     */
    private Dashboard dashboard;
    /**
     * Pole przechowuje watek odpoiwadajacy za miganie kierunkowskazow.
     * Zatrzymuje sie z chwila ustawienia pola running w obiekcie dashboard na false.
     */
    private Thread indicatorThread;
    /**
     * Pole przechowuje glowny watek odpowiadajcay za przyspieszanie i spowalnianie pojazdu.
     * Watek aktualizuje takze informacjie o aktualnej podrozy oraz sprawdza czy tempomat jest wlaczony.
     * Zatrzymuje sie z chwila ustawienia pola running w obiekcie dashboard na false.
     */
    private Thread mainThread;
    /**
     * Zmienna przechowuje String o wartosci " km/h". Zapobiega to kazdorazowemu tworzeniu obiektu typu String.
     */
    private final String kmPerHour;
    /**
     * Zmienna przechowuje String o wartosci " km". Zapobiega to kazdorazowemu tworzeniu obiektu typu String.
     */
    private final String km;

    /**
     * Konstruktor inicjalizuje pole dashboard korzystajac z pliku settings.xml, w ktorym zapisane sa ustawienia pojazdu.
     * Jezeli nie uda sie wczytac pliku, uzwane sa wartosci domyslne.
     * Inicjalizowany oraz uruchamiany jest mainThread.
     */
    public MainController() {
        Settings settings = (Settings)(new IOXml().load("settings.xml"));

        if (null == settings) {
            dashboard = new Dashboard(0,30, 30, 5, 5);
        } else {
            dashboard = new Dashboard(settings.getMileage(), settings.getMaxFuel(), settings.getActualFuel(), settings.getMaxOil(), settings.getActualOil());
        }

        kmPerHour = " km/h";
        km = " km";

        indicatorThread = new Thread();

        mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (dashboard.isRunning()) {
                    try {
                        Thread.sleep(200);

                        checkEngine();

                        if (cruiseControl.isSelected()) {
                            if (dashboard.getActualVelocity() > actualVelocity) {
                                dashboard.decelerate(2);
                            } else if (!dashboard.getFuel().isEnough()) {
                                cruiseControl.setSelected(false);
                            } else {
                                dashboard.accelerate();
                            }
                        } else {
                            dashboard.decelerate(1);
                        }

                        speedometer.setText(String.format("%.1f", dashboard.getActualVelocity()) + kmPerHour);
                        avgVelocity.setText(String.format("%.1f", dashboard.getAvgVelocity()) + kmPerHour);
                        maxVelocity.setText(String.format("%.1f", dashboard.getMaxVelocity()) + kmPerHour);
                        time.setText(String.format("%.2f", (double)(dashboard.getTime() / 1_000) / 60) + " min");
                        street.setText(String.format("%.3f", dashboard.getDistance()) + km);
                        avgFuelConsumption.setText(String.format("%.2f", dashboard.getAvgFuelConsumption()) + " l/km");
                        mileage.setText(String.format("%.1f", dashboard.getMileage()) + km);
                        odometer1.setText(String.format("%.1f", dashboard.getOdometer1()) + km);
                        odometer2.setText(String.format("%.1f", dashboard.getOdometer2()) + km);
                        fuel.setProgress(dashboard.getFuel().checkLevel());
                        temperature.setProgress(dashboard.getOil().getTemperature());
                    } catch (Exception ignored) {
                    }
                }
            }
        });

        mainThread.start();
    }

    /**
     * Metoda wczytujaca wcisniety przycisk z klawatury i wywolujaca odpowiednia metode z nim zwiazana.
     * @param event Zdarzenie wywolane przez wcisniecie przycisku.
     */
    @FXML
    public void keyPressedController(KeyEvent event) {

        if (!indicatorThread.isAlive()) {
            if (KeyCode.RIGHT == event.getCode()) {
                isRightHold = true;
                turn('r');
            } else if (KeyCode.LEFT == event.getCode()) {
                isLeftHold = true;
                turn('l');
            }
        }

        if (KeyCode.UP == event.getCode()) {
            dashboard.accelerate();
        } else if (KeyCode.DOWN == event.getCode()) {
            dashboard.decelerate(3);
            cruiseControl.setSelected(false);
        }
    }
    /**
     * Metoda wczytujaca zwolniony przycisk z klawiatury i zminiajaca wartosci pol isRightHold lub isLeftHold.
     * @param event Zdarzenie wywolane przez zwolnienie przycisku.
     */
    @FXML
    public void keyReleasedController(KeyEvent event) {
        if (KeyCode.RIGHT == event.getCode()) {
            isRightHold = false;
        } else if (KeyCode.LEFT == event.getCode()) {
            isLeftHold = false;
        }
    }

    /**
     * Metoda otwiera okno ustawien po wcisnieciu przycisku Ustawienia z gornego paska aplikacji.
     * @throws IOException Wyjatek jest rzucony gdy nie mozna znalezc pliku kontrolera ustawien.
     */
    @FXML
    public void openSettings() throws IOException {
        pl.komponentowe.logic.Settings settings = new pl.komponentowe.logic.Settings(this);
        settings.start(new Stage());
    }
    /**
     * Metoda otwiera okno wycieczek po wcisnieciu przycisku Wycieczki z gornego paska aplikacji.
     * @throws IOException Wyjatek jest rzucony gdy nie mozna znalezc pliku kontrolera wycieczek.
     */
    @FXML
    public void openTrips() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Wycieczki");
        stage.setResizable(false);
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../presentation/trips.fxml")), 720, 405);
        scene.getStylesheets().add(getClass().getResource("../presentation/style.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metoda resetuje pierwszy licznik przebiegu dziennego.
     */
    @FXML
    public void resetOdometer1() {
        dashboard.resetOdometer1();
    }

    /**
     * Metoda resetuje drugi licznik przebiegu dziennego.
     */
    @FXML
    public void resetOdometer2() {
        dashboard.resetOdometer2();
    }
    /**
     * Metoda ustawia pole actualVelocity w momencie wlaczenia tempomatu.
     */
    @FXML
    public void setActualVelocity() {
        actualVelocity = dashboard.getActualVelocity();
    }

    /**
     * Metoda jest wywolywana po wcisniesiu strzalki lewej lub prawej.
     * Uruchamiany jest watek migania odpowiedniego kierunkowskazu w zaleznosci od kierunku.
     * @param direction Kierunek w ktorym pojazd skreca.
     */
    private void turn(char direction) {
        indicatorThread = new Thread(() -> {

            Polygon indicator = new Polygon();
            if ('r' == direction) {
                indicator = rightIndicator;
            } else if ('l' == direction) {
                indicator = leftIndicator;
            }

            while (isRightHold || isLeftHold) {

                indicator.setFill(Color.GREEN);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                indicator.setFill(Color.BLACK);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        indicatorThread.start();
    }

    /**
     * Metoda tworzaca wycieczke na podstawie aktualnych danych.
     * Wykonywana przy zakonczeniu programu.
     * @return Zwraca utworzona wycieczke.
     */
    public Trip makeTrip() {
        return new Trip(dashboard.getDate(), dashboard.getAvgFuelConsumption(), dashboard.getAvgVelocity(), dashboard.getMaxVelocity(), dashboard.getTime());
    }
    /**
     * Metoda otwiera okno O programie po wcisnieciu przycisku O programie z gornego paska aplikacji.
     */
    public void aboutProgram() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../presentation/aboutProgram.fxml"));
            Stage stage = new Stage();
            stage.setTitle("O programie");
            Scene scene = new Scene(root, 480, 270);
            scene.getStylesheets().add(getClass().getResource("../presentation/style.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Metoda wywolywana przy zakonczeniu programu. Zmienia pole running w obiekcie dashboard na false.
     * Wymagane do poprawnego zakonczenia wszystkich watkow aplikacji.
     * @see pl.komponentowe.logic.Dashboard
     */
    public void stop() {
        dashboard.stop();
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    /**
     * Metoda wywolywana przy zmianie stanu swiatel mijania.
     */
    public void headlightsClick() {
        if (headlights.isSelected()) {
            headlights.getStyleClass().clear();
            headlights.getStyleClass().add("headlightsOn");
        } else {
            headlights.getStyleClass().clear();
            headlights.getStyleClass().add("headlights");
        }
    }
    /**
     * Metoda wywolywana przy zmianie stanu swiatel drogowych.
     */
    public void highBeamsClick() {
        if (highBeams.isSelected()) {
            highBeams.getStyleClass().clear();
            highBeams.getStyleClass().add("highBeamsOn");
        } else {
            highBeams.getStyleClass().clear();
            highBeams.getStyleClass().add("highBeams");
        }
    }
    /**
     * Metoda wywolywana przy zmianie stanu swiatel przeciwmgielnych.
     */
    public void fogLightsClick() {
        if (fogLights.isSelected()) {
            fogLights.getStyleClass().clear();
            fogLights.getStyleClass().add("fogLightsOn");
        } else {
            fogLights.getStyleClass().clear();
            fogLights.getStyleClass().add("fogLights");
        }
    }
    /**
     * Metoda wywolywana przy zmianie stanu swiatel mijania.
     */
    public void runningLightsClick() {
        if (runningLights.isSelected()) {
            runningLights.getStyleClass().clear();
            runningLights.getStyleClass().add("runningLightsOn");
        } else {
            runningLights.getStyleClass().clear();
            runningLights.getStyleClass().add("runningLights");
        }
    }
    /**
     * Metoda wywolywana przy sprawdzaniu temperatury oraz ilosci oleju w samochodzie.
     * Gdy zostanie wykryta nieodpowiednia wartosc wyswietlana jest kontrolka silnika.
     */
    public void checkEngine() {
        if (!dashboard.getOil().isEnough()) {
            checkEngine.getStyleClass().clear();
            checkEngine.getStyleClass().add("checkEngineOn");
        } else {
            checkEngine.getStyleClass().clear();
            checkEngine.getStyleClass().add("checkEngineOff");
        }
    }
}
