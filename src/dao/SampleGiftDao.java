package dao;

import model.SampleGift;
import sql.SampleGiftSql;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SampleGiftDao {

    /*
    close 구문을 사용하지 않아도되는 이유? -> try-with-resources 사용

    try-with-resources 구문의 특징
    try 키워드 바로 뒤에 괄호 ()가 있다.
    그 괄호 안에 AutoCloseable을 구현한 객체의 선언과 초기화가 있다.
    try 블록이 끝나면 자동으로 close()가 호출된다.
    */

    public List<SampleGift> getAllGifts() {
        String sql = SampleGiftSql.SELECT_ALL;
        List<SampleGift> sampleGifts = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                SampleGift sampleGift = new SampleGift();
                sampleGift.setGno(resultSet.getInt("gno"));
                sampleGift.setName(resultSet.getString("gname"));
                sampleGift.setG_start(resultSet.getInt("g_start"));
                sampleGift.setG_end(resultSet.getInt("g_end"));
                sampleGifts.add(sampleGift);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sampleGifts;
    }

    public int updateGift(String updateName, int updateGStart, int updateGEnd, int Gno) {
        String sql = SampleGiftSql.UPDATE;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, updateName);
            preparedStatement.setInt(2, updateGStart);
            preparedStatement.setInt(3, updateGEnd);
            preparedStatement.setInt(4, Gno);

            //영향을 받은 행의 갯수를 출력하게 된다.
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int insertGift(SampleGift sampleGift) {
        String sql = SampleGiftSql.INSERT;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, sampleGift.getGno());
            preparedStatement.setString(2, sampleGift.getName());
            preparedStatement.setInt(3, sampleGift.getG_start());
            preparedStatement.setInt(4, sampleGift.getG_end());

            //영향을 받은 행의 갯수를 출력하게 된다.
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
