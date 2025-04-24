package util;
import java.sql.*;

public class DBUtil {
    static String URL = System.getenv("DB_HOST");
    static String DB_USER = System.getenv("DB_NAME");
    static String DB_PASSWORD = System.getenv("DB_PW");

    // DB 연결 메서드
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
    }
}
