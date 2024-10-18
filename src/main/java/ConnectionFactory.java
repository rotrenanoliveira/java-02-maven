import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static Connection connection;

    public static void connect() {
        try {
            if (connection == null) {
                String url = "jdbc:postgresql://localhost:5432/maven-01-app";
                Properties props = new Properties();
                props.setProperty("user", "maven");
                props.setProperty("password", "maven");
                connection = DriverManager.getConnection(url, props);
                System.out.println("Connection Successful");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
