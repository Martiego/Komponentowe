package pl.komponentowe.data;

import java.io.Serializable;
import java.util.Date;


public class Trip implements Serializable {
    private Date date;
    private double avgFuelConsumption;
    private double avgVelocity;
    private double maxVelocity;
    private long time;

    public Trip() {
        this.date = new Date();
    }

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
        str += "Data: " + date + '\n';
        str += "Average Fuel Consumption: " + avgFuelConsumption + '\n';
        str += "Average Velocity: " + avgVelocity + '\n';
        str += "Max Velocity: " + maxVelocity + '\n';
        str += "Time: " + time + '\n' + '\n';
        return str;
    }

}
