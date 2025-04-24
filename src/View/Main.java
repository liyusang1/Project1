package View;

import dao.StudentDao;

public class Main {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();

        // 학생 조회
        studentDao.getAllStudents().forEach(student -> {
            System.out.println(student.getName());
        });


    }
}
