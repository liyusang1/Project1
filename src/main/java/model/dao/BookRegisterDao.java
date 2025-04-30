package model.dao;

import model.dto.BookDto;
import model.sql.BookRegisterSql;
import util.DBUtil;
import util.MapResultSetToDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookRegisterDao {

    // 도서 등록 (Insert)
    public int registerBook(BookDto bookDto) {
        String sql = BookRegisterSql.REGISTER_BOOK;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, bookDto.getTitle());
            preparedStatement.setString(2, bookDto.getAuthor());
            preparedStatement.setLong(3, bookDto.getCategoryId());
            preparedStatement.setString(4, bookDto.getPublisher());
            preparedStatement.setInt(5, bookDto.getPrice());
            preparedStatement.setInt(6, bookDto.getStatus());

            return preparedStatement.executeUpdate();  // 성공 시 영향받은 행 수 반환

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;  // 실패 시 -1 반환
        }
    }

    public boolean checkBookAvailability(Long bookId) {
        String sql = BookRegisterSql.CHECKBOOK;
        boolean available = false;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // 먼저 파라미터 설정
            preparedStatement.setLong(1, bookId);

            // 쿼리 실행
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next() && resultSet.getInt("check_book") == 1) {
                    available = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return available;
    }

    // 모든 책 출력
    public List<BookDto> getAllBooks() {
        String sql = BookRegisterSql.SELECT_ALL_BOOKS;
        List<BookDto> bookList = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                bookList.add(MapResultSetToDto.mapResultSetToBookDto(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }

    //     대출가능한 status 도서만 조회
    public List<BookDto> getAvailableBooks() {
        String sql = BookRegisterSql.SELECT_AVAILABLE_BOOKS;
        List<BookDto> bookList = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                bookList.add(MapResultSetToDto.mapResultSetToBookDto(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }
}

