package model.sql;

public class BookRegisterSql {

    public static final String REGISTER_BOOK = "INSERT INTO Books  " +
            "(title, author, category_id, publisher, price, status) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    public static final String SELECT_ALL_BOOKS = "SELECT * FROM Books";

    public static final String CHECKBOOK = "SELECT COUNT(1) AS check_book FROM Books WHERE book_id = ?";

    public static final String CARTEGORY_SELECTALL = "SELECT * FROM Categorys";

}



