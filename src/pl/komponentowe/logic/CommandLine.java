package pl.komponentowe.logic;

import pl.komponentowe.data.IODataBase;
import pl.komponentowe.data.Trip;
import pl.komponentowe.logic.exceptions.IDNotFoundException;
import pl.komponentowe.logic.sorting.SortByAvgVelocity;
import pl.komponentowe.logic.sorting.SortByDate;
import pl.komponentowe.logic.sorting.SortByID;
import pl.komponentowe.logic.sorting.SortByTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
            System.out.println("Enter t to view trips menu");
            System.out.println("Enter s to view settings menu");
            System.out.println("Enter q to quit");
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
            System.out.println("Enter v to view trips");
            System.out.println("Enter d to delete trip");
            System.out.println("Enter da to delete all trips");
            System.out.println("Enter c to combine trips");
            System.out.println("Enter s to sort trips by ID");
            System.out.println("Enter sd to sort trips by date");
            System.out.println("Enter st to sort trips by time");
            System.out.println("Enter sv to sort trips by average velocity");
            System.out.println("Enter r to return to main menu");
            String input = scanner.next();
            switch (input) {
                case "v":
                    System.out.println("Trips:");
                    for (Trip trip : trips) {
                        System.out.println(trip);
                    }
                    break;
                case "d":
                    System.out.println("Enter ID of trip to delete or q to exit");
                    String id = scanner.next();
                    if ("q".equals(id)) {
                        break;
                    }
                    try {
                        ioDataBase.delete("trips", Integer.parseInt(id));
                        trips = (ArrayList<Trip>) ioDataBase.load("trips");
                        System.out.println("Trip deleted");
                    } catch (NumberFormatException e) {
                        System.out.println("Positive integer were expected");
                    } catch (IDNotFoundException e) {
                        System.out.println("ID not found");
                    }
                    break;
                case "da":
                    System.out.println("Are you sure? (y/n)");
                    String decision = scanner.next();
                    if ("y".equals(decision)) {
                        ioDataBase.deleteAll("trips");
                        trips = (ArrayList<Trip>) ioDataBase.load("trips");
                        System.out.println("All trips have been deleted");
                    }
                    break;
                case "c":
                    System.out.println("Enter ID of trips to combine or q to exit");
                    System.out.print("ID 1: ");
                    String id1 = scanner.next();
                    System.out.print("ID 2: ");
                    String id2 = scanner.next();
                    try {
                        ioDataBase.concatRows("trips", Integer.parseInt(id1), Integer.parseInt(id2));
                        trips = (ArrayList<Trip>) ioDataBase.load("trips");
                        System.out.println("Rows combined");
                    } catch (IDNotFoundException e) {
                        System.out.println("ID not found");
                    } catch (NumberFormatException e) {
                        System.out.println("Positive integer were expected");
                    }
                    break;
                case "s":
                    trips.sort(new SortByID());
                    break;
                case "sd":
                    trips.sort(new SortByDate());
                    break;
                case "st":
                    trips.sort(new SortByTime());
                    break;
                case "sv":
                    trips.sort(new SortByAvgVelocity());
                    break;
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
