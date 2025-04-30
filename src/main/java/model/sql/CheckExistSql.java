package model.sql;

public class CheckExistSql {
    public static final String CHECKUSER = "SELECT COUNT(1) AS check_user FROM Users WHERE user_id = ?";

    public static final String CHECKBOOK = "SELECT COUNT(1) AS check_book FROM Books WHERE book_id = ? and status=1";

    public static final String CHECKCATEGORY = "SELECT COUNT(1) AS check_book FROM Categorys WHERE category_id = ?";

    public static final String CHECKMANGER = "SELECT membership_type FROM Users WHERE user_id = ?";
}
