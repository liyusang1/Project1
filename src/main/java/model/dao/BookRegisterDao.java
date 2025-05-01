package model.dao;

import model.dto.BookDto;
import model.dto.CategoryDto;
import model.dto.SelectBookDto;
import model.dto.UserDto;
import model.sql.BookRegisterSql;
import model.sql.UserManageSql;
import util.DBUtil;
import util.MapResultSetToDto;

import java.awt.print.Book;
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

    // 모든 책 출력
    public List<SelectBookDto> getAllBooks() {
        String sql = BookRegisterSql.SELECT_ALL_BOOKS;
        List<SelectBookDto> bookList = new ArrayList<>();

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
    public List<SelectBookDto> getAvailableBooks() {
        String sql = BookRegisterSql.SELECT_AVAILABLE_BOOKS;
        List<SelectBookDto> bookList = new ArrayList<>();

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

    /**
     * Select문이면서 parameter존재할 때 Connection 및 Statement이후 ResultSet은 별도로 처리
     */
    public List<SelectBookDto> getBookById(Long targetId) {
        String sql = BookRegisterSql.SELECT_BOOK_BY_ID;
        List<SelectBookDto> bookList = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, targetId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    bookList.add(MapResultSetToDto.mapResultSetToBookDto(rs));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public List<SelectBookDto> getBookByCategoryId(Long targetId) {
        String sql = BookRegisterSql.SELECT_BOOK_BY_CATEGORY;
        List<SelectBookDto> bookList = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, targetId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    bookList.add(MapResultSetToDto.mapResultSetToBookDto(rs));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public List<CategoryDto> getAllCategory() {
        String sql = BookRegisterSql.SELECT_ALL_CATEGORY;
        List<CategoryDto> categoryList = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setCategory_id(rs.getLong("category_id"));
                categoryDto.setCategory(rs.getString("category"));

                categoryList.add(categoryDto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryList;
    }


}

