package model.sql;

public class UserManageSql {
    // membership_type은 꼭 불러올 것
    public static final String SELECT_ALL_USER = "SELECT user_id, name, email, membership_type FROM Users";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM Users WHERE user_id = ?";
    public static final String UPDATE_USER = "UPDATE Users SET name = ?, email = ?, phone_number = ? WHERE user_id = ?";
    public static final String DELETE_USER = "DELETE FROM Users WHERE user_id = ?";
}
