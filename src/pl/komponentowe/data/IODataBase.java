package pl.komponentowe.data;

import pl.komponentowe.logic.exceptions.IDNotFoundException;

import java.sql.*;
import java.util.ArrayList;

/**
 * Klasa odpowiedzialna za zapisywanie wycieczek do bazy danych.
 * Do realizacji tego zadania wykorzystywany jest MySql.
 * Do polaczenia z baza danych potrzebny jest kontroler JDBC.
 *
 * @author Patryk Kolanek
 * @author Kacper Swiercz
 *
 * @see <a href="https://dev.mysql.com/downloads/connector/j/">JDBC</a>
 */
public class IODataBase implements Preservation {
    /** Url do bazy danych. */
    private String url = "jdbc:mysql://localhost:3306/";

    /** Login uzytkownika bazy danych. */
    private String user;

    /** Haslo uzytkownika bazy danych. */
    private String password;

    /**
     * Konstruktor przyjmujacy dane uwierzytalniajace do bazy danych od uzytkownika.
     *
     * @param user Login uzytkownika bazy danych.
     * @param password Haslo uzytkownika bazy danych.
     */
    public IODataBase(String user, String password) {
        this.user = user;
        this.password = password;
    }

    /**
     * Metoda zapisujaca wycieczke do bazy danych.
     *
     * @param path Nazwa bazy danych.
     * @param object Wycieczki, ktore maja zostac zapisane do bazy danych.
     */
    @Override
    public void save(String path, Object object) {
        try (Connection conn = DriverManager.getConnection(url + path, user, password)) {
            if (conn.isValid(5)) {
                Statement statement = conn.createStatement();

                for (Trip t: (ArrayList<Trip>)object) {
                    statement.execute("INSERT INTO trips (date, avgFuelConsumption, avgVelocity, maxVelocity, time) " +
                            "VALUES ('" + t.getDate().getTime() + "', " + t.getAvgFuelConsumption() + ", "
                            + t.getAvgVelocity() + ", " + t.getMaxVelocity() + ", " + t.getTime() + ");");
                }

            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * Metoda odpowiedzilna za odczyt wszystkich rekorodow
     *
     * @param path Nazwa tabeli w bazie danych.
     * @return Lista wycieczek.
     */
    @Override
    public Object load(String path) {
        ArrayList result = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url + path, user, password); Statement statement = conn.createStatement()) {
            if (conn.isValid(5)) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + path);
                while (resultSet.next()) {
                    result.add(new Trip(resultSet.getInt(1), resultSet.getLong(2), resultSet.getDouble(3), resultSet.getDouble(4), resultSet.getDouble(5), resultSet.getLong(6)));
                }
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return result;
    }

    /**
     * Metoda odpowiedzialna za usuniecie rekordu o podanym id.
     *
     * @param path Nazwa tabeli w bazie danych.
     * @param id Id rekordu do usuniecia.
     */
    public void delete(String path, int id) throws IDNotFoundException {
        try (Connection conn = DriverManager.getConnection(url + path, user, password); Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            if (conn.isValid(5)) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + path + " WHERE id >= " + id);

                if (resultSet.next()) {
                    if (id == resultSet.getInt(1)) {
                        resultSet.deleteRow();

                        while (resultSet.next())  {
                            resultSet.updateInt(1, resultSet.getInt(1) - 1);
                            resultSet.updateRow();
                        }
                    }
                } else {
                    throw new IDNotFoundException();
                }

            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * Metoda odpowiedzialna za usuniecie wszystkich rekordow w tabeli.
     *
     * @param path Nazwa tabeli w bazie danych.
     */
    public void deleteAll(String path) {
        try (Connection conn = DriverManager.getConnection(url + path, user, password); Statement statement = conn.createStatement()) {
            if (conn.isValid(5)) {
                statement.execute("DELETE FROM " + path);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * Metoda odpowiedzialna za polaczenie dwoch rekordow w jeden.
     *
     * @param path Nazwa tabeli w bazie danych.
     * @param id1 Id pierwszego rekordu do polaczenia.
     * @param id2 Id drugiego rekordu do polaczenia.
     */
    public void concatRows(String path, int id1, int id2) throws IDNotFoundException{
        try (Connection conn = DriverManager.getConnection(url + path, user, password); Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            if (conn.isValid(5)) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + path + " WHERE id = " + id1 + " OR id = " + id2);
                ArrayList<Trip> trips = new ArrayList<>();

                if (id1 < id2) {
                    while (resultSet.next()) {
                        trips.add(new Trip(resultSet.getInt(1), resultSet.getLong(2), resultSet.getDouble(3), resultSet.getDouble(4), resultSet.getDouble(5), resultSet.getLong(6)));
                    }

                    if (2 == trips.size()) {
                        delete(path, id2);
                        delete(path, id1);

                        resultSet.moveToInsertRow();
                        resultSet.updateLong(2, trips.get(0).getDate().getTime());
                        resultSet.updateDouble(3, (trips.get(0).getAvgFuelConsumption() * trips.get(0).getTime() + trips.get(1).getAvgFuelConsumption() * trips.get(1).getTime()) / (trips.get(0).getTime() + trips.get(1).getTime()));
                        resultSet.updateDouble(4, (trips.get(0).getAvgVelocity() * trips.get(0).getTime() + trips.get(1).getAvgVelocity() * trips.get(1).getTime()) / (trips.get(0).getTime() + trips.get(1).getTime()));
                        resultSet.updateDouble(5, Math.max(trips.get(0).getMaxVelocity(), trips.get(1).getMaxVelocity()));
                        resultSet.updateLong(6, trips.get(0).getTime() + trips.get(1).getTime());
                        resultSet.insertRow();
                    } else {
                        System.out.println("Wyjatek");
                    }
                }
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

}
