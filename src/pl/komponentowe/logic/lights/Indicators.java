package pl.komponentowe.logic.lights;

public enum Indicators {
    LEFT,
    RIGHT;

    public void turnOn() {
        // Methods responsible for turning on lights in GUI
        System.out.println(this.toString() + " indicator turnOn");
    }

    public void turnOff() {
        // Methods responsible for turning off light in GUI
        System.out.println(this.toString() + " indicator turnOff");
    }
}
