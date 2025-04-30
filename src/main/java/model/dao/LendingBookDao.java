package model.dao;

import model.sql.LendingBookSql;
import util.DBUtil;

import java.sql.*;
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

    public boolean checkUserLendingExist(Long userId) {
        String sql = LendingBookSql.CHECK_USER_LENDING;
        boolean available = false;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // 먼저 파라미터 설정
            preparedStatement.setLong(1, userId);

            // 쿼리 실행
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next() && resultSet.getInt("check_lending") < 1) {
                    available = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return available;
    }

    public boolean checkUsersLateFee(Long userId) {
        String sql = LendingBookSql.CHECK_USER_LATE_FEE;
        boolean available = false;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // 먼저 파라미터 설정
            preparedStatement.setLong(1, userId);

            // 쿼리 실행
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next() && resultSet.getInt("check_fee") == 0) {
                    available = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return available;
    }

    public int returnBooks(Long lendingId) {
        String sql = LendingBookSql.RETURN_BOOK;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, isReturned);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setLong(3, lendingId);

            //영향을 받은 행의 갯수를 출력하게 된다.
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean checkLendingExist(Long userId, Long lendingId) {
        String sql = LendingBookSql.CHECK_LENDING_EXIST;
        boolean available = false;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // 먼저 파라미터 설정
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, lendingId);

            // 쿼리 실행
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next() && resultSet.getInt("check_lending") == 1) {
                    available = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return available;
    }
}
