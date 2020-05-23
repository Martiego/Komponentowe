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
    private Date date;

    /** Srednie zuzycie paliwa w <b>l/km</b> */
    private double avgFuelConsumption;

    /** Sredni predkosc podczas podrozy w <b>km/h</b> */
    private double avgVelocity;

    /** Maksymalna predkosc podczas podrozy w  <b>km/h</b> */
    private double maxVelocity;

    /** Czas, ktory uplynal od poczatku podrozy w <b>ms</b> */
    private long time;

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

    public void setId(int id) {
        this.id = id;
    }

    public void setAvgFuelConsumption(double avgFuelConsumption) {
        this.avgFuelConsumption = avgFuelConsumption;
    }

    public void setAvgVelocity(double avgVelocity) {
        this.avgVelocity = avgVelocity;
    }

    public void setMaxVelocity(double maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        String str = "";
        str += "ID: " + id + '\n';
        str += "Date: " + date + '\n';
        str += "Average Fuel Consumption: " + avgFuelConsumption + '\n';
        str += "Average Velocity: " + avgVelocity + '\n';
        str += "Max Velocity: " + maxVelocity + '\n';
        str += "Time: " + time + '\n';
        return str;
    }

}
