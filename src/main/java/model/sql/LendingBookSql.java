package model.sql;

public class LendingBookSql {
    public static final String INSERT =
            "insert into Lendings(book_id, user_id, due_date, status) values (?,?,?,?)";
}
