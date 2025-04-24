package dao;

import model.Student;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM student";
        List<Student> students = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getString("id"));
                student.setName(resultSet.getString("name"));
                student.setJumin(resultSet.getString("jumin"));
                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public void insertStudent(Student student) {
        String sql = "INSERT INTO student (id, name, jumin) VALUES (?, ?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getJumin());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

