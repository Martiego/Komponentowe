package pl.martiego;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Nazwa", 5, "sf", 5000));
        movies.add(new Movie("Nazwa2", 15, "sf", 500));
        movies.add(new Movie("Nazwa3", 3, "sf", 54000));
        movies.add(new Movie("Nazwa4", 8, "sf", 66000));

        System.out.println(movies.get(0) + "\n");
//        System.out.println(movies);

//        Collections.sort(movies, new SortByBudget());
//        System.out.println(movies);
//
//        Collections.sort(movies, new SortByRate());
//        System.out.println(movies);
//
//        Collections.sort(movies, new SortByGenre());
//        System.out.println(movies);

        // Zapis obiektów do pliku
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Patryk\\Desktop\\Kompo\\pliczek1.txt"))) {
            outputStream.writeObject(movies);
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        ArrayList<Movie> movies1 = new ArrayList<>();

        // Odczyt obiektów z pliku
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("C:\\Users\\Patryk\\Desktop\\Kompo\\pliczek1.txt"))) {
            movies1 = (ArrayList<Movie>) inputStream.readObject();
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        System.out.println(movies1);
    }
}
