package model.sql;

public class SearchBookSql {
    public static final String SELECT_ALL = "SELECT c.category category, b.*" +
                                            " FROM Books b JOIN Categorys c ON b.category_id = c.category_id";
    public static final String SELECT_ALL_LOAN = "SELECT c.category category, b.*" +
                                                " FROM Books b JOIN Categorys c ON b.category_id = c.category_id WHERE STATUS = 1";

    public static final String SELECT_BY_TITLE =  "SELECT c.category category,  b.*" +
                                                    " FROM Books b JOIN Categorys c ON b.category_id = c.category_id WHERE TITLE LIKE ?";
    public static final String SELECT_BY_TITLE_LOAN =  "SELECT c.category category,  b.*" +
                                                        " FROM Books b JOIN Categorys c ON b.category_id = c.category_id WHERE TITLE LIKE ? AND STATUS = 1";

    public static final String SELECT_BY_AUTHOR =  "SELECT c.category category,  b.*" +
                                                    " FROM Books b JOIN Categorys c ON b.category_id = c.category_id WHERE AUTHOR LIKE CONCAT('%', ?, '%')";
    public static final String SELECT_BY_AUTHOR_LOAN  =  "SELECT c.category category,  b.*" +
                                                        " FROM Books b JOIN Categorys c ON b.category_id = c.category_id WHERE AUTHOR LIKE CONCAT('%', ?, '%') AND STATUS = 1";

    public static final String SELECT_BY_CATEGORY =  "SELECT c.category category,  b.*" +
                                                     " FROM Books b JOIN Categorys c ON b.category_id = c.category_id WHERE LOWER(c.category) LIKE LOWER(CONCAT('%', ?, '%'));";
    public static final String SELECT_BY_CATEGORY_LOAN =  "SELECT c.category category,  b.*" +
                                                        " FROM Books b JOIN Categorys c ON b.category_id = c.category_id WHERE LOWER(c.category) LIKE LOWER(CONCAT('%', ?, '%')) AND STATUS = 1";
}
