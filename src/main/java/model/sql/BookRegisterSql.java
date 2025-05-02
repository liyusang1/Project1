package model.sql;

public class BookRegisterSql {

    public static final String REGISTER_BOOK = "INSERT INTO Books  " +
            "(title, author, category_id, publisher, price, status) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    public static final String SELECT_ALL_BOOKS = "SELECT b.book_id, b.title, b.author, " +
        "b.category_id, c.category AS category, b.publisher, b.price, b.status, b.created_at " +
        "FROM Books b INNER JOIN Categorys c ON b.category_id = c.category_id ORDER BY b.created_at DESC LIMIT 15" ;


    public static final String SELECT_AVAILABLE_BOOKS =
            "SELECT b.book_id, b.title, b.author, b.category_id, c.category AS category, " +
                    "b.publisher, b.price, b.status, b.created_at " +
                    "FROM Books b INNER JOIN Categorys c ON b.category_id = c.category_id " +
                    "WHERE b.status = 1 ORDER BY b.created_at DESC LIMIT 15";

    public static final String SELECT_BOOK_BY_ID = "SELECT b.book_id, b.title, b.author, " +
            "b.category_id, c.category AS category, b.publisher, b.price, b.status, b.created_at " +
            "FROM Books b INNER JOIN Categorys c ON b.category_id = c.category_id WHERE book_id = ?";

    public static final String SELECT_BOOK_BY_CATEGORY = "SELECT b.book_id, b.title, b.author, " +
            "b.category_id, c.category AS category, b.publisher, b.price, b.status, b.created_at " +
            "FROM Books b INNER JOIN Categorys c ON b.category_id = c.category_id WHERE b.category_id = ?";


    public static final String SELECT_ALL_CATEGORY = "SELECT * FROM Categorys";

    public static final String CHECKBOOK = "SELECT COUNT(1) AS check_book FROM Books WHERE book_id = ?";

    public static final String CATEGORY_SELECT_ALL = "SELECT * FROM Categorys";

}



