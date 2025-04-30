package model.dao;

import model.dto.UserLoginDto;
import model.dto.UserSignUpDto;
import model.sql.UserSql;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class UserDao {

    // 회원가입
    public int insertUser(UserSignUpDto userSignUpDto) {
        String sql = UserSql.INSERT_USER;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, userSignUpDto.getName());
            preparedStatement.setString(2, userSignUpDto.getEmail());
            preparedStatement.setString(3, userSignUpDto.getPhoneNumber());
            preparedStatement.setInt(4, userSignUpDto.getMembershipType());
            preparedStatement.setString(5, userSignUpDto.getPassword());

            return preparedStatement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    // 이메일 중복 여부
    public boolean isEmailDuplicate(String email) {
        String sql = UserSql.CHECK_EMAIL_DUPLICATE;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // 결과가 있으면 true (중복)
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return true; // 예외 발생 시 중복된 것으로 처리
        }
    }

    // 로그인
    public boolean loginUser(UserLoginDto userLoginDto) {
        String sql = UserSql.LOGIN_USER;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, userLoginDto.getEmail());
            preparedStatement.setString(2, userLoginDto.getPassword());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 사용자 존재 확인
    public boolean verifyUserByEmailAndPhone(String email, String phoneNumber) {
        String sql = UserSql.VERIFY_USER;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, phoneNumber);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // 사용자 존재 여부 확인
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 비밀번호 업데이트
    public boolean updatePassword(String email, String hashedPassword) {
        String sql = UserSql.UPDATE_PASSWORD;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hashedPassword);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
