package model.dao;

import model.dto.BookShowDto;
import model.sql.SearchBookSql;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchBookDao {
    // 도서 전체 목록 조회
    public List<BookShowDto> getAllBooks(boolean status) {
        String sql = status ? SearchBookSql.SELECT_ALL_LOAN : SearchBookSql.SELECT_ALL;
        List<BookShowDto> booksDtos = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                BookShowDto booksDto = new BookShowDto();
                booksDto.setTitle(resultSet.getString("title"));
                booksDto.setAuthor(resultSet.getString("author"));
                booksDto.setPublisher(resultSet.getString("publisher"));
                booksDto.setCategory(resultSet.getString("category"));
                booksDto.setStatus(resultSet.getInt("status"));
                booksDtos.add(booksDto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booksDtos;
    }

    // 도서 검색(제목)
    public List<BookShowDto> findBooksByTitle(String title, boolean status) {
        String sql = status ? SearchBookSql.SELECT_BY_TITLE_LOAN : SearchBookSql.SELECT_BY_TITLE;
        List<BookShowDto> booksDtos = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

             preparedStatement.setString(1, '%' + title + '%');

             ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BookShowDto booksDto = new BookShowDto();
                booksDto.setTitle(resultSet.getString("title"));
                booksDto.setAuthor(resultSet.getString("author"));
                booksDto.setPublisher(resultSet.getString("publisher"));
                booksDto.setCategory(resultSet.getString("category"));
                booksDto.setStatus(resultSet.getInt("status"));
                booksDtos.add(booksDto);
            }
        } catch (SQLException e) {
             e.printStackTrace();
        }
        return booksDtos;
    }

    // 도서 검색(작가)
    public List<BookShowDto> findBooksByAuthor(String author, boolean status) {
        String sql = status ? SearchBookSql.SELECT_BY_AUTHOR_LOAN : SearchBookSql.SELECT_BY_AUTHOR;
        List<BookShowDto> booksDtos = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setString(1, author);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BookShowDto booksDto = new BookShowDto();
                booksDto.setTitle(resultSet.getString("title"));
                booksDto.setAuthor(resultSet.getString("author"));
                booksDto.setPublisher(resultSet.getString("publisher"));
                booksDto.setCategory(resultSet.getString("category"));
                booksDto.setStatus(resultSet.getInt("status"));
                booksDtos.add(booksDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booksDtos;
    }
    // 도서 검색(카테고리)
    public List<BookShowDto> findBooksByCategory(String category, boolean status) {
        String sql = status ? SearchBookSql.SELECT_BY_CATEGORY_LOAN : SearchBookSql.SELECT_BY_CATEGORY;
        List<BookShowDto> booksDtos = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setString(1, category);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BookShowDto booksDto = new BookShowDto();
                booksDto.setTitle(resultSet.getString("title"));
                booksDto.setAuthor(resultSet.getString("author"));
                booksDto.setPublisher(resultSet.getString("publisher"));
                booksDto.setCategory(resultSet.getString("category"));
                booksDto.setStatus(resultSet.getInt("status"));
                booksDtos.add(booksDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booksDtos;
    }
}
