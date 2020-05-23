package pl.komponentowe.logic;

import pl.komponentowe.logic.fluids.Fuel;
import pl.komponentowe.logic.fluids.Oil;

import java.util.Date;

/**
 * Glowna klasa projektu, odpowiada za cala funkcjonalnosc deski rozdzielczej.
 * Obiekt tej klasy przechowuje wszystkie wartosci opisujace stan pojazdu.
 *
 * @author Patryk Kolanek
 * @author Kacper Swiercz
 */
public class Dashboard {
    /**
     * Pole wskazujace czy program jest uruchomiony, sluzy do konczenia pracy watkow.
     * Przy konczeniu programu zmieniana jest na <b>false</b>
     */
    private boolean running;
    /**
     * Pole przechowujace informacje o czasie uruchomienia programu.
     * Jest to jednosczesnie czas zapisywany jako nowa wycieczka przy zakonczeniu programu.
     */
    private final Date date;
    /**
     * Pole przechowujace aktualna predkosc chwilowa pojazdu w <b>km/h</b>.
     */
    private double actualVelocity;
    /**
     * Pole przechowujace maksymalna predkosc pojazdu w czasie jednej wycieczki w <b>km/h</b>.
     */
    private double maxVelocity;
    /**
     * Pole przechowujace odleglosc przebyta w czasie jednej wycieczki w <b>km</b>.
     */
    private double distance;
    /**
     * Pole przechowujace wartosc licznika przebiegu calkowitego pojazdu w <b>km</b>.
     */
    private double mileage;
    /**
     * Pole przechowujace wartosc pierwszego przebiegu dziennego w <b>km</b>.
     */
    private double odometer1;
    /**
     * Pole przechowujace wartosc drugiego przebiegu dziennego w <b>km</b>.
     */
    private double odometer2;
    /**
     * Pole przechowujace informacje o paliwie w pojezdzie.
     */
    private Fuel fuel;
    /**
     * Pole przechowujace informacje o oleju w pojezdzie.
     */
    private Oil oil;
    /**
     * Pole przechowujace zuzycie paliwa w <b>l/km</b>.
     */
    private double fuelConsumption;

    /**
     * Konstruktor klasy inicjalizuje pola running jako <b>true</b>, oraz zmienne fuel, oil i date.
     * Zostaje utworzony nowy watek odpowiedzialny za aktualizacje pol zwiazanych z przejechanym dystansem w zaleznosci od aktualnej predkosci (milage, odometer1, odmoeter2, distance).
     *
     * @param mileagee Przebieg calkowity pojazdu.
     * @param maxFuel Maksymalna pojemnosc zbiornika paliwa w <b>litrach</b>.
     * @param actualFuel Obecna ilosc paliwa w <b>litrach</b>.
     * @param maxOil Maksymalna pojemnosc zbiornika oleju w <b>litrach</b>.
     * @param actualOil Obecna ilosc oleju w <b>litrach</b>.
     */
    public Dashboard(double mileagee, double maxFuel, double actualFuel, double maxOil, double actualOil) {
        this.mileage = mileagee;
        running = true;
        fuel = new Fuel(maxFuel);
        fuel.fill(actualFuel);
        oil = new Oil(maxOil);
        oil.fill(actualOil);
        date = new Date();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                double avgDistance;
                while (running) {
                    try {
                        Thread.sleep(100);
                        avgDistance = actualVelocity / 3_600_000 * 100;
                        // droga w km
                        distance += avgDistance;
                        mileage += avgDistance;
                        odometer1 += avgDistance;
                        odometer2 += avgDistance;
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });

        thread.start();
    }

    /**
     * Metoda sluzy do zmiany wartosci pola running, wywolywana przy zakonczeniu programu.
     */
    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public double getMileage() {
        return mileage;
    }

    /**
     * Metoda sluzy do przyspieszania pojazdu, im wyzsza aktualna predkosc tym wolniej przyspiesza.
     * Zwieksza tez zuzycie paliwa i aktualizuje maksymalna predkosc w czasie wycieczki.
     * Maksymalna predkosc do jakiej mozemy przyspieszyc to <b>186 km/h</b>.
     */
    public void accelerate() {
        if (0 <= actualVelocity && 30 >= actualVelocity) {
            actualVelocity += 0.50;
        } else if (30 < actualVelocity && 60 >= actualVelocity) {
            actualVelocity += 0.40;
        } else if (60 < actualVelocity && 90 >= actualVelocity) {
            actualVelocity += 0.35;
        } else if (90 < actualVelocity && 160 >= actualVelocity) {
            actualVelocity += 0.30;
        } else if (160 < actualVelocity && 186 >= actualVelocity) {
            actualVelocity += 0.20;
        }

        if (actualVelocity > maxVelocity) {
            maxVelocity = actualVelocity;
        }

        double timeFuelConsumption = Math.random() / 100;
        fuelConsumption += timeFuelConsumption;

        fuel.update(timeFuelConsumption, true);
        oil.update(0.01, true);
    }

    /**
     * Metoda sluzy do zwalniania pojazdu, im wyzsza aktualna predkosc tym bardziej pojazd zwalnia.
     */
    public void decelerate(int value) {
        if (0 < actualVelocity && 30 >= actualVelocity) {
            actualVelocity -= value * 0.25;
        } else if (30 < actualVelocity && 60 >= actualVelocity) {
            actualVelocity -= value * 0.30;
        } else if (60 < actualVelocity && 90 >= actualVelocity) {
            actualVelocity -= value * 0.35;
        } else if (90 < actualVelocity) {
            actualVelocity -= value * 0.40;
        } else if (0 >= actualVelocity) {
            actualVelocity = 0;
        }

        oil.update(0.001, false);
    }

    /**
     * Metoda resetuje wartosc pierwszego licznika przebiegu dziennego.
     */
    public void resetOdometer1() {
        odometer1 = 0;
    }

    /**
     * Metoda resetuje wartosc drugiego licznika przebiegu dziennego.
     */
    public void resetOdometer2() {
        odometer2 = 0;
    }

    public double getActualVelocity() {
        return actualVelocity;
    }

    public double getMaxVelocity() {
        return maxVelocity;
    }

    /**
     * Metoda liczy czas, ktory uplynal od inicjalizacji pola date do wywolania tej metody.
     * @return Czas jest zwracany w <b>ms</b>.
     */
    public long getTime() {
        return System.currentTimeMillis() - date.getTime();
    }

    public double getDistance() {
        return distance;
    }

    public double getOdometer1() {
        return odometer1;
    }

    public double getOdometer2() {
        return odometer1;
    }

    /**
     * Metoda oblicza srednia predkosc na podstawie czasu i przejechanej odleglosci w aktualnej podrozy.
     * @return Zwraca predkosc srednia w <b>km/h</b>.
     */
    public double getAvgVelocity() {
        double meter = distance * 1000;
        double seconds = (double)(getTime() / 1_000);
        return meter / seconds * 36 / 10;
    }

    public Date getDate() {
        return date;
    }

    public double getAvgFuelConsumption() {
        if (distance != 0)
            return fuelConsumption / distance;
        return 0;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public Oil getOil() {
        return oil;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }
}
