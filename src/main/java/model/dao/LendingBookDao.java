package model.dao;

import constants.LendingStatus;
import model.dto.LendingBookDto;
import model.sql.LendingBookSql;
import util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LendingBookDao {

    public int insertLending(Long bookId, Long userId, LocalDateTime dueDate) {
        String sql = LendingBookSql.INSERT;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, bookId);
            preparedStatement.setLong(2, userId);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(dueDate));
            preparedStatement.setInt(4, LendingStatus.IS_BORROWED);

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
            // 책은 최대 3개 까지 대출 가능 ->
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next() && resultSet.getInt("check_lending") < 4) {
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

            preparedStatement.setLong(1, LendingStatus.IS_RETURNED);
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

    public List<LendingBookDto> getLendingList(Long userId) {
        String sql = LendingBookSql.SELECT_LENDING_LIST;
        List<LendingBookDto> lendingBookDtos = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LendingBookDto lendingBookDto = new LendingBookDto();
                lendingBookDto.setLendingId(resultSet.getInt("lending_id"));
                lendingBookDto.setTitle(resultSet.getString("title"));
                lendingBookDto.setAuthor(resultSet.getString("author"));
                lendingBookDto.setPublisher(resultSet.getString("publisher"));
                lendingBookDtos.add(lendingBookDto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lendingBookDtos;
    }

    public int updateBookStatus(Long bookId, int status) {
        String sql = LendingBookSql.UPDATE_BOOK_STATUS;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, status);
            preparedStatement.setLong(2, bookId);

            //영향을 받은 행의 갯수를 출력하게 된다.
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Long getBookIdFromLendingId(Long lendingId) {
        String sql = LendingBookSql.GET_BOOK_ID_FROM_LENDING_ID;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, lendingId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getLong("book_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1L;
    }

    public int isPenaltyRequired(Long lendingId) {
        String sql = LendingBookSql.CHECK_PENALTY_REQUIRED;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, lendingId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("days_overdue");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int applyPenalty(Long lendingId, int penaltyRequired) {
        String sql = LendingBookSql.APPLY_PENALTY;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, lendingId);
            preparedStatement.setInt(2, penaltyRequired * LendingStatus.LATE_FEE);

            //영향을 받은 행의 갯수를 출력하게 된다.
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
