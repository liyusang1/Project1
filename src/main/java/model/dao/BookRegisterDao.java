package model.dao;

import model.dto.BookDto;
import model.sql.BookRegisterSql;
import util.DBUtil;

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

    // select
    public List<BookDto> getAllBooks() {
        String sql = BookRegisterSql.SELECT_ALL_BOOKS;
        List<BookDto> bookList = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                BookDto bookDto = new BookDto();

                bookDto.setBookId(rs.getLong("book_id"));
                bookDto.setTitle(rs.getString("title"));
                bookDto.setAuthor(rs.getString("author"));
                bookDto.setCategoryId(rs.getLong("category_id"));
                bookDto.setPublisher(rs.getString("publisher"));
                bookDto.setPrice(rs.getInt("price"));
                bookDto.setStatus(rs.getInt("status"));
                bookDto.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

                bookList.add(bookDto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }
}

