package model.sql;

public class SampleGiftSql {
    public static final String INSERT = "INSERT INTO gift VALUES (?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE gift SET gname=?, g_start=?, g_end=? WHERE gno=?";
    public static final String SELECT_ALL = "SELECT * FROM gift";
}
