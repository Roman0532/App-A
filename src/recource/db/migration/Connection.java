package recource.db.migration;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
   public static java.sql.Connection getDbConnection() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        try {
            java.sql.Connection dbConn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            return dbConn;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }
}
