package model.sql;

public class LendingBookSql {
    public static final String INSERT =
            "insert into Lendings(book_id, user_id, due_date, status) values (?,?,?,?)";

    public static final String CHECK_USER_LENDING =
            "SELECT COUNT(1) AS check_lending FROM Lendings WHERE user_id = ? and status = 0";

    public static final String CHECK_USER_LATE_FEE =
            "SELECT COUNT(1) AS check_fee FROM LateFees WHERE user_id = ?";
}
