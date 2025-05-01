package model.dao;

import model.dto.UserDto;
import model.sql.UserManageSql;
import util.DBUtil;
import util.MapResultSetToDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManageDao {
    // 모든 유저 목록 조회
    public List<UserDto> getAllUsers(int option) {
        String sql = "";
        switch (option) {
            case 1 -> {
                sql = UserManageSql.SELECT_ALL_USER;
            }
            case 2 -> {
                sql = UserManageSql.SELECT_ALL_MANAGER;
            }
            case 3 -> {
                sql = UserManageSql.SELECT_ALL_NORMAL_USER;
            }
            case 4 -> {
                sql = UserManageSql.SELECT_ALL_USER_BY_CARETE_DATE;
            }
        }

        List<UserDto> users = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                users.add(MapResultSetToDto.mapResultSetToUserDto(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Select문이면서 parameter존재할 때 Connection 및 Statement이후 ResultSet은 별도로 처리
     */
    public List<UserDto> getUserById(Long targetId) {
        String sql = UserManageSql.SELECT_USER_BY_ID;
        List<UserDto> users = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, targetId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    users.add(MapResultSetToDto.mapResultSetToUserDto(rs));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // 유저 정보 업데이트
    public int updateUser(String name, String email, String phone_number, Long user_id) {
        String sql = UserManageSql.UPDATE_USER;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone_number);
            pstmt.setLong(4, user_id);

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int deleteUser(Long userId) {
        String sql = UserManageSql.DELETE_USER;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, userId);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
