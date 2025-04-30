package util;

import model.dto.BookDto;
import model.dto.UserDto;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class MapResultSetToDto {
    /**
     * ResultSet에서 Dto 객체로 mapping
     * 쿼리문에서 가져오고자 하는 컬럼에 맞춰서 자동 mapping
     */

    // Book
    public static BookDto mapResultSetToBookDto(ResultSet rs) throws SQLException {
        BookDto book = new BookDto();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        Set<String> columnNames = new HashSet<>();
        for (int i = 1; i <= columnCount; i++) {
            columnNames.add(metaData.getColumnName(i).toLowerCase());
        }

        if (columnNames.contains("book_id")) {
            book.setBookId(rs.getLong("book_id"));
        }
        if (columnNames.contains("title")) {
            book.setTitle(rs.getString("title"));
        }
        if (columnNames.contains("author")) {
            book.setAuthor(rs.getString("author"));
        }
        if (columnNames.contains("category_id")) {
            book.setCategoryId(rs.getLong("category_id"));
        }
        if (columnNames.contains("publisher")) {
            book.setPublisher(rs.getString("publisher"));
        }
        if (columnNames.contains("price")) {
            book.setPrice(rs.getInt("price"));
        }
        if (columnNames.contains("status")) {
            book.setStatus(rs.getInt("status"));
        }
        if (columnNames.contains("created_at")) {
            Timestamp createdAt = rs.getTimestamp("created_at");
            if (createdAt != null) {
                book.setCreatedAt(createdAt.toLocalDateTime());
            }
        }

        return book;
    }

    // User
    public static UserDto mapResultSetToUserDto(ResultSet rs) throws SQLException {
        UserDto user = new UserDto();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        Set<String> columnNames = new HashSet<>();
        for (int i = 1; i <= columnCount; i++) {
            columnNames.add(metaData.getColumnName(i).toLowerCase());
        }
        if (columnNames.contains("user_id")) {
            user.setUserId(rs.getLong("user_id"));
        }
        if (columnNames.contains("name")) {
            user.setName(rs.getString("name"));
        }
        if (columnNames.contains("email")) {
            user.setEmail(rs.getString("email"));
        }
        if (columnNames.contains("phone_number")) {
            user.setPhoneNumber(rs.getString("phone_number"));
        }
        if (columnNames.contains("membership_type")) {
            user.setMembershipType(rs.getInt("membership_type"));
        }
        if (columnNames.contains("created_at")) {
            Timestamp createdAt = rs.getTimestamp("created_at");
            if (createdAt != null) {
                user.setCreatedAt(createdAt.toLocalDateTime());
            }
        }
        if (columnNames.contains("updated_at")) {
            Timestamp updatedAt = rs.getTimestamp("updated_at");
            if (updatedAt != null) {
                user.setUpdatedAt(updatedAt.toLocalDateTime());
            }
        }
        if (columnNames.contains("password")) {
            user.setPassword(rs.getString("password"));
        }

        return user;
    }
}
