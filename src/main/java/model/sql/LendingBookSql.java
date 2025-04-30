package model.sql;

public class LendingBookSql {
    public static final String INSERT =
            "insert into Lendings(book_id, user_id, due_date, status) values (?,?,?,?)";

    public static final String CHECK_USER_LENDING =
            "SELECT COUNT(1) AS check_lending FROM Lendings WHERE user_id = ? and status = 0";

    public static final String CHECK_USER_LATE_FEE =
            "SELECT COUNT(1) AS check_fee FROM LateFees WHERE user_id = ?";

    public static final String RETURN_BOOK =
            "update Lendings set status = ?,return_date=? where lending_id = ?";

    public static final String CHECK_LENDING_EXIST =
            "SELECT COUNT(1) AS check_lending FROM Lendings WHERE user_id = ? and lending_id = ? and status = 0";

    public static final String SELECT_LENDING_LIST =
            "select lending_id,title,author,publisher from Books join Lendings on Lendings.book_id = Books.book_id\n" +
                    "where Lendings.user_id = ? and Lendings.status = 0";

    public static final String UPDATE_BOOK_STATUS =
            "update Books set status = ? where book_id = ?";

    public static final String GET_BOOK_ID_FROM_LENDING_ID =
            "select book_id from Lendings where lending_id = ?";

}
