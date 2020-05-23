package pl.komponentowe.logic;

import pl.komponentowe.data.IODataBase;
import pl.komponentowe.data.Trip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CommandLine {
    Scanner scanner;
    public CommandLine() {
        scanner = new Scanner(System.in);
        try {
            menu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void menu() throws IOException {
        boolean menuInput = false;
        while (!menuInput) {
            System.out.println("===MAIN MENU===");
            System.out.println("Type t to view trips menu");
            System.out.println("Type s to view settings menu");
            System.out.println("Type q to quit");
            String input = scanner.next();
            switch (input) {
                case "t":
                    trips();
                    break;
                case "s":
                    settings();
                    break;
                case "q":
                    menuInput = true;
                    break;
                default:
                    System.out.println("Wrong input");
            }
        }
        }

    public void trips() {
        boolean tripInput = false;
        ArrayList<Trip> trips;
        IODataBase ioDataBase = new IODataBase("root", "");
        trips = (ArrayList<Trip>) ioDataBase.load("trips");

        while (!tripInput) {
            System.out.println("===TRIP MENU===");
            System.out.println("Type v to view trips");
            System.out.println("Type d to delete trip");
            System.out.println("Type da to delete all trips");
            System.out.println("Type c to concat trips");
            System.out.println("Type s to sort trips by ID");
            System.out.println("Type sd to sort trips by date");
            System.out.println("Type st to sort trips by time");
            System.out.println("Type sv to sort trips by average velocity");
            System.out.println("Type r to return to main menu");
            String input = scanner.next();
            switch (input) {
                case "v":
                    System.out.println("Trips:");
                    for (Trip trip : trips) {
                        System.out.println(trip);
                    }
                    break;
                case "d":

                case "r":
                    tripInput = true;
                    break;
                default:
                    System.out.println("Wrong input");
            }
        }

    }

    public void settings() {
        System.out.println("settings");
    }
}
