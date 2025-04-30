package model.sql;

public class UserSql {
    public static final String INSERT_USER =
            "INSERT INTO Users (name, email, phone_number, membership_type, password) VALUES (?, ?, ?, ?, ?)";

    public static final String CHECK_EMAIL_DUPLICATE =
            "SELECT 1 FROM Users WHERE email = ?"; // SELECT 1 = 존재 여부만 체크 (빠르고 가벼움)

    public static final String LOGIN_USER =
            "SELECT * FROM Users WHERE email = ? AND password = ?";

    public static final String VERIFY_USER =
            "SELECT 1 FROM Users WHERE email = ? AND phone_number = ?"; // SELECT 1 = 존재 여부만 체크 (빠르고 가벼움)

    public static final String UPDATE_PASSWORD =
            "UPDATE Users SET password = ?, updated_at = now() WHERE email = ?";
}
