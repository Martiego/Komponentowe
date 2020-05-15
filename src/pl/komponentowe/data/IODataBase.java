package pl.komponentowe.data;

import java.sql.*;
import java.util.ArrayList;

public class IODataBase implements Preservation<Trip> {
    private String url       = "jdbc:mysql://localhost:3306/";
    private String user;
    private String password;


    public IODataBase(String user, String password) {
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(String path, ArrayList<Trip> object) {
        try (Connection conn = DriverManager.getConnection(url + path, user, password)) {
            if (conn.isValid(5)) {
                Statement statement = conn.createStatement();

                for (Trip t: object) {
                    statement.execute("INSERT INTO trips (date, avgFuelConsumption, avgVelocity, maxVelocity, time) " +
                            "VALUES ('" + t.getDate().getTime() + "', " + t.getAvgFuelConsumption() + ", "
                            + t.getAvgVelocity() + ", " + t.getMaxVelocity() + ", " + t.getTime() + ");");
                }

            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public ArrayList<Trip> load(String path) {
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

    public void delete(String path, int id) {
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
                    System.out.println("Tu mozna wstawic exception");
                }

            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void deleteAll(String path) {
        try (Connection conn = DriverManager.getConnection(url + path, user, password); Statement statement = conn.createStatement()) {
            if (conn.isValid(5)) {
                statement.execute("DELETE FROM " + path);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void concatRows(String path, int id1, int id2) {
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
                        System.out.println(trips.get(0).getTime() + trips.get(1).getTime());
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
