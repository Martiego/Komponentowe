package pl.martiego;

import java.io.Serializable;
import java.util.Date;


public class Trip implements Serializable {
    private Date date;
    private double avgFuelConsumption;
    private double avgVelocity;
    private double maxVelocity;
    private long time;

    public void setMaxVelocity(double maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    public Trip() {
        this.date = new Date();
    }

    /**
     *
     * @param date
     * @param avgFuelConsumption
     * @param avgVelocity
     * @param maxVelocity
     * @param time - parameter in seconds
     */
    public Trip(Date date, double avgFuelConsumption, double avgVelocity, double maxVelocity, long time) {
        this.date = date;
        this.avgFuelConsumption = avgFuelConsumption;
        this.avgVelocity = avgVelocity;
        this.maxVelocity = maxVelocity;
        this.time = time;
    }

    public Trip(Object o) {
        this.date = ((Trip) o).getDate();
        this.avgFuelConsumption = ((Trip) o).getAvgFuelConsumption();
        this.avgVelocity = ((Trip) o).getAvgVelocity();
        this.maxVelocity = ((Trip) o).getMaxVelocity();
        this.time = ((Trip) o).getTime();
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
        String str = "";
        str += "Data: " + date + '\n';
        str += "Średnie zużycie paliwa: " + avgFuelConsumption + '\n';
        str += "Średnia prędkość: " + avgVelocity + '\n';
        str += "Prędkość maksymalna: " + maxVelocity + '\n';
        str += "Czas podróży: " + time + '\n' + '\n';
        return str;
    }

}
