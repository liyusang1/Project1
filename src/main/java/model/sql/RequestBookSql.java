package model.sql;

public class RequestBookSql {
    public static final String SELECT_BOOK_TITLE = "SELECT count(*) FROM Books WHERE title = ?";
    public static final String INSERT = "INSERT INTO Requests(user_id, request_book, request_author, request_publisher, created_at, status) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String SELECT_ALL = "SELECT * FROM Requests WHERE status = 2";
    public static final String SELECT_BY_USER = "SELECT * FROM Requests WHERE user_id = ?";
    public static final String UPDATE = "UPDATE Requests SET status = ? WHERE request_id = ?";
}
