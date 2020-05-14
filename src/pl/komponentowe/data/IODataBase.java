package pl.komponentowe.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class IODataBase<T> implements Preservation<T> {
    private String url       = "jdbc:mysql://localhost:3306/";
    private String user      = "root";
    private String password  = "";


    public IODataBase(String user, String password) {
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(String path, T object) {
        try (Connection conn = DriverManager.getConnection(url + path, user, password);){
            if (conn.isValid(5)) {
                Statement statement = conn.createStatement();
                Trip trip = (Trip) object;
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                statement.execute("INSERT INTO trips (date, avgFuelConsumption, avgVelocity, maxVelocity, time) " +
                        "VALUES ('" + dateFormat.format(trip.getDate()) + "', " + trip.getAvgFuelConsumption() + ", "
                        + trip.getAvgVelocity() + ", " + trip.getMaxVelocity() + ", " + trip.getTime() + ");");
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public T load(String path) {
        // Method responsible for save to database
        return null;
    }


}
