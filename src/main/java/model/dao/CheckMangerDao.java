package model.dao;

import model.sql.CheckExistSql;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckMangerDao {
    public boolean checkManager(Long userId) {
        String sql = CheckExistSql.CHECKMANGER;
        boolean managerType = false;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // 먼저 파라미터 설정
            preparedStatement.setLong(1, userId);

            // 쿼리 실행
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next() && resultSet.getInt("membership_type") == 1) {
                    managerType = true;
                    return managerType;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return managerType;
    }
}
