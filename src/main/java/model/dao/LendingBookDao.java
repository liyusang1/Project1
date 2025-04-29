package model.dao;

import model.sql.LendingBookSql;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LendingBookDao {
    private final static int isBorrowed = 0;
    private final static int isReturned = 1;

    public int insertLending(Long bookId, Long userId, LocalDateTime dueDate) {
        String sql = LendingBookSql.INSERT;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, bookId);
            preparedStatement.setLong(2, userId);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(dueDate));
            preparedStatement.setInt(4, isBorrowed);

            //영향을 받은 행의 갯수를 출력하게 된다.
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
