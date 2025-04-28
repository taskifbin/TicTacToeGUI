import java.sql.*;

public class dbConnect {
    private static final String URL = "jdbc:mysql://localhost:3306/tictactoeGUI";
    private static final String USER = "root"; // change if needed
    private static final String PASS = "";     // change if needed

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
