package model.dao;

import model.dto.RequestsDto;
import model.sql.RequestBookSql;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestBookDao {

    // 도서 존재 확인
    public int confirmBook(String title) {
        String sql = RequestBookSql.SELECT_BOOK_TITLE;

        try(Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, title);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        }  catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // 희망도서 신청
    public int requestBook(RequestsDto requestsDto) {  // 희망 도서 신청(회원)
        String sql = RequestBookSql.INSERT;

        try (Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))  {

            preparedStatement.setLong(1, requestsDto.getUserId());
            preparedStatement.setString(2, requestsDto.getRequestBook());
            preparedStatement.setString(3, requestsDto.getRequestAuthor());
            preparedStatement.setString(4, requestsDto.getRequestPublisher());
            preparedStatement.setDate(5, requestsDto.getCreateAt());
            preparedStatement.setInt(6, requestsDto.getStatus());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 희망도서 신청 조회(회원)
    public List<RequestsDto> getMyRequestedBooks(Long userId) {  // 신청 목록 조회(회원)
        String sql = RequestBookSql.SELECT_BY_USER;
        List<RequestsDto> requestsDtos = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ) {

            preparedStatement.setLong(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
             while (resultSet.next()) {
                RequestsDto requestsDto = new RequestsDto();
                requestsDto.setUserId(resultSet.getLong("user_id"));
                requestsDto.setRequestBook(resultSet.getString("request_book"));
                requestsDto.setRequestAuthor(resultSet.getString("request_author"));
                requestsDto.setRequestPublisher(resultSet.getString("request_publisher"));
                requestsDto.setCreateAt(resultSet.getDate("created_at"));
                requestsDto.setStatus(resultSet.getInt("status"));
                requestsDtos.add(requestsDto);
             }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requestsDtos;
    }

    // 희망도서 신청 조회(관리자)
    public List<RequestsDto> getAllRequestedBooks() {  // 희망 도서 목록 조회(관리자)
        String sql = RequestBookSql.SELECT_ALL;
        List<RequestsDto> requestsDtos = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                RequestsDto requestsDto = new RequestsDto();
                requestsDto.setRequestId(resultSet.getLong("request_id"));
                requestsDto.setUserId(resultSet.getLong("user_id"));
                requestsDto.setRequestBook(resultSet.getString("request_book"));
                requestsDto.setRequestAuthor(resultSet.getString("request_author"));
                requestsDto.setRequestPublisher(resultSet.getString("request_publisher"));
                requestsDto.setCreateAt(resultSet.getDate("created_at"));
                requestsDto.setStatus(resultSet.getInt("status"));
                requestsDtos.add(requestsDto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requestsDtos;
    }

    // 희망도서 상태 처리
    public int updateRequestedBookStatus(int updateRequestId, int  updateStatus) {  // 희망 도서 상태 변경(관리자)
        String sql = RequestBookSql.UPDATE;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))  {

            preparedStatement.setLong(2, updateRequestId);
            preparedStatement.setInt(1, updateStatus);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
