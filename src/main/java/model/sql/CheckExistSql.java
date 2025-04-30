package model.sql;

public class CheckExistSql {
    public static final String CHECKUSER = "SELECT COUNT(1) AS check_user FROM Users WHERE user_id = ?";

    public static final String CHECKBOOK = "SELECT COUNT(1) AS check_book FROM Books WHERE book_id = ? and status=1";
}
