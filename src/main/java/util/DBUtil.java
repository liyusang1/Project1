package util;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String URL = dotenv.get("DB_HOST");
    private static final String DB_USER = dotenv.get("DB_NAME");
    private static final String DB_PASSWORD = dotenv.get("DB_PW");

    // DB 연결 메서드
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
    }
}
