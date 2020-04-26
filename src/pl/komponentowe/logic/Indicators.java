package pl.komponentowe.logic;

import pl.komponentowe.logic.Lights;

public enum Indicators implements Lights {
    LEFT,
    RIGHT;

    @Override
    public void turnOn() {
        // Methods responsible for turning on lights in GUI
        System.out.println(this.toString() + " indicator turnOn");
    }

    @Override
    public void turnOff() {
        // Methods responsible for turning off light in GUI
        System.out.println(this.toString() + " indicator turnOff");
    }
}
