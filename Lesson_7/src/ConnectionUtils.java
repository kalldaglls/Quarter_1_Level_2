import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/stepik", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //connection.close();
    }

    public static Connection getConnetction2(){
        try {
            return DriverManager.getConnection("jdbc:h2:mem:://localhost:5555/core/api/v1/products", "sa", " ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //connection.close();
    }
}
