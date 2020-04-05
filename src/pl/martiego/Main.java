package pl.martiego;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import pl.martiego.exceptions.WrongDateException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * In order to run this program you need to add XStream library to your project. You can find it here: "http://x-stream.github.io/"
 */

public class Main {
    private static String PATH_TO_FILE_TXT = "C:\\Users\\Patryk\\Desktop\\Semestr 4 2020\\Kompo\\pliczek1.txt";
    private static String PATH_TO_FILE_XML = "C:\\Users\\Patryk\\Desktop\\Semestr 4 2020\\Kompo\\pliczek1.xml";

    public static void main(String[] args) {
        ArrayList<Trip> trips = new ArrayList<>();
        try {
            trips.add(new Trip(new Date((new Date()).getTime() - 10000000000L), 7.5, 55, 20, 22000020));
            trips.add(new Trip(new Date((new Date()).getTime() - 2000000000), 9.5, 55, 80, 42030000));
            trips.add(new Trip(new Date((new Date()).getTime() - 30000000000L), 2.5, 15, 30, 12032000));
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        } finally {
            System.out.println("End of adding trips");
        }

        // Zapis obiektów do pliku
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(PATH_TO_FILE_TXT))) {
            outputStream.writeObject(trips);
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        ArrayList<Trip> trips1 = new ArrayList<>();

        // Odczyt obiektów z pliku
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PATH_TO_FILE_TXT))) {
            trips1 = (ArrayList<Trip>) inputStream.readObject();
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        System.out.println(trips1);

        Collections.sort(trips1, new SortByMaxVelocity());

        // Serializacja obiektów do xml
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("trip", Trip.class);

        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(PATH_TO_FILE_XML))) {
            dataOutputStream.writeChars(xStream.toXML(trips1));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        // Deserializacja obiektów z xml
        ArrayList<Trip> trips2 = new ArrayList<>();

        try {
            String content = Files.readString( Paths.get(PATH_TO_FILE_XML), StandardCharsets.UTF_16);
            trips2 = (ArrayList<Trip>) xStream.fromXML(content);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println(trips2);
    }
}
