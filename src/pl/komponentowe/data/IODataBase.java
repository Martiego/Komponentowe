package pl.komponentowe.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class IODataBase<T> implements Preservation<T> {
    private String url       = "jdbc:mysql://localhost:3306/trips";
    private String user      = "root";
    private String password  = "";

    private Connection conn;

    public IODataBase() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Hurra");
            } else {
                System.out.println("Przykro mi, włącz najpierw xamppa, potem mysql, następnie stwórz bazę dancyh trips");
                System.out.println("...");
                System.out.println("Hurra");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void save(String path, T object) {
        // Method responsible for save to database
    }

    @Override
    public T load(String path) {
        // Method responsible for save to database
        return null;
    }
}
