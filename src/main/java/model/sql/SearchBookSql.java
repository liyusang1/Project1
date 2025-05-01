package model.sql;

public class SearchBookSql {
    public static final String SELECT_ALL = "SELECT c.category category, b.title title, b.author author, b.publisher, b.status status" +
                                            " FROM Books b JOIN Categorys c ON b.category_id = c.category_id";
    public static final String SELECT_ALL_LOAN = "SELECT c.category category, b.title title, b.author author, b.publisher, b.status status" +
                                                " FROM Books b JOIN Categorys c ON b.category_id = c.category_id WHERE STATUS = 1";

    public static final String SELECT_BY_TITLE =  "SELECT c.category category, b.title title, b.author author, b.publisher, b.status status" +
                                                    " FROM Books b JOIN Categorys c ON b.category_id = c.category_id WHERE TITLE LIKE ?";
    public static final String SELECT_BY_TITLE_LOAN =  "SELECT c.category category, b.title title, b.author author, b.publisher, b.status status" +
                                                        " FROM Books b JOIN Categorys c ON b.category_id = c.category_id WHERE TITLE LIKE ? AND STATUS = 1";

    public static final String SELECT_BY_AUTHOR =  "SELECT c.category category, b.title title, b.author author, b.publisher, b.status status" +
                                                    " FROM Books b JOIN Categorys c ON b.category_id = c.category_id WHERE AUTHOR = ?";
    public static final String SELECT_BY_AUTHOR_LOAN  =  "SELECT c.category category, b.title title, b.author author, b.publisher, b.status status" +
                                                        " FROM Books b JOIN Categorys c ON b.category_id = c.category_id WHERE AUTHOR = ? AND STATUS = 1";

    public static final String SELECT_BY_CATEGORY =  "SELECT c.category category, b.title title, b.author author, b.publisher, b.status status" +
                                                     " FROM Books b JOIN Categorys c ON b.category_id = c.category_id WHERE LOWER(c.category) = LOWER(?);";
    public static final String SELECT_BY_CATEGORY_LOAN =  "SELECT c.category category, b.title title, b.author author, b.publisher, b.status status" +
                                                        " FROM Books b JOIN Categorys c ON b.category_id = c.category_id WHERE LOWER(c.category) = LOWER(?) AND STATUS = 1";
}
