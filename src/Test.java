import java.sql.*;

public class Test {
    public static void main(String[] args) {

        // 환경 변수에서 DB_HOST 값을 읽어오기
        String dbHost = System.getenv("DB_HOST");
        String url = dbHost;
        String dbUserId = System.getenv("DB_NAME");
        String dbUserPassword = System.getenv("DB_PW");

        String sql = "SELECT * FROM student WHERE name = ? AND id = ?";
        String sql2 = "SELECT * FROM student";
        String sql3 = "insert into gift values (?,?,?,?)";

        // try-with-resources 구문을 사용하여 자원 자동 관리
        try (
                Connection connection = DriverManager.getConnection(url, dbUserId, dbUserPassword);
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
                PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                PreparedStatement preparedStatement3 = connection.prepareStatement(sql3)
        ) {
            // 첫 번째 쿼리 실행
            preparedStatement1.setString(1, "허우");
            preparedStatement1.setString(2, "wooya2702");
            try (ResultSet resultSet = preparedStatement1.executeQuery()) {
                while (resultSet.next()) {
                    String jumin = resultSet.getString("jumin");
                    String name = resultSet.getString("name");
                    System.out.printf("%s : %s%n", name, jumin);
                }
            }

            // 두 번째 쿼리 실행
            try (ResultSet resultSet = preparedStatement2.executeQuery()) {
                while (resultSet.next()) {
                    System.out.printf("%s %s %s%n",
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3));
                }
            }

            //세 번째 쿼리 실행
            // ? 자리에 값 설정
            preparedStatement3.setString(1, "11");  // 첫 번째 ? 자리에 값 설정
            preparedStatement3.setString(2, "사탕세트");  // 두 번째 ? 자리에 값 설정
            preparedStatement3.setInt(3, 777);           // 세 번째 ? 자리에 값 설정 (예시로 정수 값 설정)
            preparedStatement3.setInt(4, 777); // 네 번째 ? 자리에 날짜 값 설정

            // 쿼리 실행
            preparedStatement3.executeUpdate(); // executeUpdate() 사용

        } catch (SQLException e) {
            // 예외 발생 시 처리
            e.printStackTrace();
        }
    }
}