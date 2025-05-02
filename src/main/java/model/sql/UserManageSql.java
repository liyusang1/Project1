package model.sql;

public class UserManageSql {
    /**
     * parameter없는 query
     */
    public static final String SELECT_ALL_USER = "SELECT * FROM Users";
    public static final String SELECT_ALL_MANAGER = "SELECT * FROM Users WHERE membership_type = 1";
    public static final String SELECT_ALL_NORMAL_USER = "SELECT * FROM Users WHERE membership_type = 0";
    public static final String SELECT_ALL_USER_BY_CARETE_DATE = "SELECT * FROM Users ORDER BY created_at DESC";

    /**
     *  parameter 포함 query
     */
    public static final String SELECT_USER_BY_ID = "SELECT * FROM Users WHERE user_id = ?";
    public static final String UPDATE_USER = "UPDATE Users SET name = ?, email = ?, phone_number = ? WHERE user_id = ?";
    public static final String DELETE_USER = "DELETE FROM Users WHERE user_id = ?";
}
