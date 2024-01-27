import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private static JDBC jdbc;
    private JDBC() { }
    public static JDBC getInstance() {
        if (jdbc==null){
            jdbc=new JDBC();
        }
        return jdbc;
    }
    protected static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/university_coordination", "root", "Root123*");
            return connection;
    }
}
