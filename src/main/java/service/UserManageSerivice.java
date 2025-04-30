package service;

import model.dao.CheckMangerDao;
import model.dao.UserManageDao;
import model.dto.UserDto;

import java.util.List;

public class UserManageSerivice {
    private final UserManageDao userManageDao;
    private final CheckMangerDao checkMangerDao;

    public UserManageSerivice() {
        this.userManageDao = new UserManageDao();
        this.checkMangerDao = new CheckMangerDao();
    }

    // 모든 유저 조회 - 관리자 여부 처리
    public List<UserDto> getAllUsers(Long managerId) {

        // 관리자 유저인지 체크
        if (!checkMangerDao.checkManager(managerId)) {
            return null;
        }
        return userManageDao.getAllUsers();
    }

    // 모든 유저 조회 - 관리자 여부 처리
    public List<UserDto> getUserById(Long userId, Long managerId) {

        // 관리자 유저인지 체크
        if (!checkMangerDao.checkManager(managerId)) {
            return null;
        }
        return userManageDao.getUserById(userId);
    }


    //회원 삭제 - 존재하는 Id여부 처리, 관리자 여부 처리
    public int deleteUser(Long userId, Long managerId) {

        // 관리자 유저인지 체크
        if (!checkMangerDao.checkManager(managerId)) {
            throw new NullPointerException();
        }
        return userManageDao.deleteUser(userId);
    }

    // 회원 수정
    public int updateUser(String name, String email, String phone_number, Long userId) {

        if (!name.matches("^.{0,255}$")) {
            System.out.print("[이름이 너무 깁니다]");
            return 0;
        } else if (name.trim().isEmpty()) {
            System.out.print("[이름의 입력값이 없습니다]");
            return 0;
        }

        if (!email.matches("^.{0,255}$")) {
            System.out.print("[이메일이 너무 깁니다]");
            return 0;
        } else if (email.trim().isEmpty()) {
            System.out.print("[이메일 입력값이 없습니다]");
            return 0;
        }

        if (!phone_number.matches("^.{0,255}$")) {
            System.out.print("[전화번호가 너무 깁니다]");
            return 0;
        } else if (phone_number.trim().isEmpty()) {
            System.out.print("[전화번호 입력값이 없습니다]");
            return 0;
        }
        return userManageDao.updateUser(name, email, phone_number, userId);
    }
}
