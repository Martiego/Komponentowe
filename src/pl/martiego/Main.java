package pl.martiego;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

/**
 * In order to run this program you need to add XString library to your project. You can find it here: "http://x-stream.github.io/"
 */

public class Main {
    private static String PATH_TO_FILE_TXT = "C:\\Users\\Patryk\\Desktop\\Kompo\\pliczek1.txt";
    private static String PATH_TO_FILE_XML = "C:\\Users\\Patryk\\Desktop\\Kompo\\pliczek1.xml";

    public static void main(String[] args) {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Nazwa", 5, "sf", 5000));
        movies.add(new Movie("Nazwa2", 15, "sf", 500));
        movies.add(new Movie("Nazwa3", 3, "sf", 54000));
        movies.add(new Movie("Nazwa4", 8, "sf", 66000));

        System.out.println(movies.get(0) + "\n");
        System.out.println(movies);

        Collections.sort(movies, new SortByBudget());
        System.out.println(movies);

        Collections.sort(movies, new SortByRate());
        System.out.println(movies);

        Collections.sort(movies, new SortByGenre());
        System.out.println(movies);

        // Zapis obiektów do pliku
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(PATH_TO_FILE_TXT))) {
            outputStream.writeObject(movies);
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        ArrayList<Movie> movies1 = new ArrayList<>();

        // Odczyt obiektów z pliku
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PATH_TO_FILE_TXT))) {
            movies1 = (ArrayList<Movie>) inputStream.readObject();
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        System.out.println(movies1);

        Collections.sort(movies1, new SortByTitle());

        // Serializacja obiektów do xml
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("movie", Movie.class);

        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(PATH_TO_FILE_XML))) {
            dataOutputStream.writeChars(xStream.toXML(movies1));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        // Deserializacja obiektów z xml

        ArrayList<Movie> movies2 = new ArrayList<>();

        try {
            String content = Files.readString( Paths.get(PATH_TO_FILE_XML), StandardCharsets.UTF_16);
            movies2 = (ArrayList<Movie>) xStream.fromXML(content);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println(movies2);
    }
}
