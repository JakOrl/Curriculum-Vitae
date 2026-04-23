package Pong_Source.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton service to manage the MySQL database connection.
 * This ensures only one connection is active at a time.
 * @author Jakub Orlowski
 * @version 1.0
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    // Database Credentials --- PLEASE EDIT THIS TO MAKE IT WORK FOR YOU. ---
    private final String url = "jdbc:mysql://localhost:3307/pong_db";
    private final String user = "root";
    private final String password = "root";

    /**
     * Private constructor for singleton
     */
    private DatabaseConnection() throws SQLException {
        try {
            //Loading jbdc driver that was added to pom.xml
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JBDC Driver not found:" + e.getMessage());
        }
    }

    /**
     * Gets the single instance of the database connection.
     * @return The DatabaseConnection instance.
     * @throws SQLException if connection fails.
     */
    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if(instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    /**
     * Returns the active SQL Connection object.
     */
    public Connection getConnection() {
        return connection;
    }

}
