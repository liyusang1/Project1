package model.dao;

import model.sql.CheckExistSql;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 유저가 존재하는지 체크 재사용성을 위해 띠로 생성
 */
public class CheckUserExistDao {
    public boolean checkUserExist(Long userId) {
        String sql = CheckExistSql.CHECKUSER;
        boolean available = false;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // 먼저 파라미터 설정
            preparedStatement.setLong(1, userId);

            // 쿼리 실행
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next() && resultSet.getInt("check_user") == 1) {
                    available = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return available;
    }
}
