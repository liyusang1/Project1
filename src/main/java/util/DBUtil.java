package util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {

    private static final HikariDataSource dataSource;
    private static final Dotenv dotenv = Dotenv.load();

    // HikariCP 설정
    static {
        // Dotenv 라이브러리로 환경 변수 로드
        Dotenv dotenv = Dotenv.load();

        // HikariCP 설정
        HikariConfig config = new HikariConfig();

        // .env 파일에서 DB 연결 정보 가져오기
        config.setJdbcUrl(dotenv.get("DB_HOST"));
        config.setUsername(dotenv.get("DB_NAME"));
        config.setPassword(dotenv.get("DB_PW"));

        // 커넥션 풀 사이즈 설정
        config.setMaximumPoolSize(10);

        // 설정된 데이터소스를 이용하여 커넥션 풀 초기화
        dataSource = new HikariDataSource(config);
    }

    // DB 연결 메서드
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
