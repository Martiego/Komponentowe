package pl.komponentowe.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Klasa warstwy danych, realizujaca zadanie przechowywania informacji o podrozach.
 *
 * @author Patryk Kolanek
 * @author Kacper Swiercz
 */
public class Trip implements Serializable {
    /** Id wycieczki w bazie danych */
    private int id;

    /** Data rozpoczecia wycieczki */
    private final Date date;

    /** Srednie zuzycie paliwa w <b>l/km</b> */
    private final double avgFuelConsumption;

    /** Sredni predkosc podczas podrozy w <b>km/h</b> */
    private final double avgVelocity;

    /** Maksymalna predkosc podczas podrozy w  <b>km/h</b> */
    private final double maxVelocity;

    /** Czas, ktory uplynal od poczatku podrozy w <b>ms</b> */
    private final long time;

    /**
     * Konstruktor wykorzystywany do utworzenia wycieczki.
     *
     * @param date Data przekazywana jako instancja obiektu klasy Date.
     * @param avgFuelConsumption Srednie zuzycie paliwa liczone jako <b>l/km</b>.
     * @param avgVelocity Srednia predkosc osiągnieta w czasie podrozy w <b>km/h</b>.
     * @param maxVelocity Maksymalna predkosc osiagnieta w czasie podrozy w <b>km/h</b>.
     * @param time Czas liczony od poczatku podrozy w <b>ms</b>.
     */
    public Trip(Date date, double avgFuelConsumption, double avgVelocity, double maxVelocity, long time) {
        this.date = date;
        this.avgFuelConsumption = avgFuelConsumption;
        this.avgVelocity = avgVelocity;
        this.maxVelocity = maxVelocity;
        this.time = time;
    }

    /**
     * Konstruktor wykorzystywany do tworzenia wycieczki pobranej z bazy danych.
     *
     * @param id Wartosc atrybutu id w bazie danych.
     * @param date Data przekazywana jako instancja obiektu klasy Date.
     * @param avgFuelConsumption Srednie zuzycie paliwa liczone jako <b>l/km</b>.
     * @param avgVelocity Srednia predkosc osiągnieta w czasie podrozy w <b>km/h</b>.
     * @param maxVelocity Maksymalna predkosc osiagnieta w czasie podrozy w <b>km/h</b>.
     * @param time Czas liczony od poczatku podrozy w <b>ms</b>.
     */
    public Trip(int id, long date, double avgFuelConsumption, double avgVelocity, double maxVelocity, long time) {
        this.id = id;
        this.date = new Date(date);
        this.avgFuelConsumption = avgFuelConsumption;
        this.avgVelocity = avgVelocity;
        this.maxVelocity = maxVelocity;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public double getAvgFuelConsumption() {
        return avgFuelConsumption;
    }

    public double getAvgVelocity() {
        return avgVelocity;
    }

    public double getMaxVelocity() {
        return maxVelocity;
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        String str = "ID: " + id + '\n'
        + "Date: " + date + '\n'
        + "Average Fuel Consumption: " + avgFuelConsumption + '\n'
        + "Average Velocity: " + avgVelocity + '\n'
        + "Max Velocity: " + maxVelocity + '\n'
        + "Time: " + time + '\n';
        return str;
    }
}
