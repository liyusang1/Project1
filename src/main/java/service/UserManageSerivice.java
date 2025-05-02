package service;

import constants.ResultCode;
import model.dao.CheckMangerDao;
import model.dao.CheckUserExistDao;
import model.dao.UserManageDao;
import model.dto.UserDto;

import java.util.List;

public class UserManageSerivice {
    private final UserManageDao userManageDao;
    private final CheckMangerDao checkMangerDao;
    private final CheckUserExistDao checkUserExistDao;

    public UserManageSerivice() {
        this.checkUserExistDao = new CheckUserExistDao();
        this.userManageDao = new UserManageDao();
        this.checkMangerDao = new CheckMangerDao();
    }

    // 모든 유저 조회 - 관리자 여부 처리
    public List<UserDto> getAllUsers(int option, Long userId) {

        // 관리자 유저인지 체크
        if (!checkMangerDao.checkManager(userId)) {
            return null;
        }
        return userManageDao.getAllUsers(option);
    }

    // id로 유저 조회 - 관리자 여부 처리
    public List<UserDto> getUserById(Long targetId, Long userId) {

        // 관리자 유저인지 체크
        if (!checkMangerDao.checkManager(userId)) {
            return null;
        }

        return userManageDao.getUserById(targetId);
    }

    //회원 삭제 - 존재하는 Id여부 처리, 관리자 여부 처리
    public int deleteUser(Long inputId) {

        //유저 유효성 검사
        if (!checkUserExistDao.checkUserExist(inputId)) {
            return ResultCode.DELETE_USER_NOT_EXIST;
        }

        return userManageDao.deleteUser(inputId);
    }

    // 회원 수정
    public int updateUser(String name, String email, String phone_number, Long userId) {

        // 이름: 2~30자, 한글/영문/공백만 허용
        if (!name.matches("^[a-zA-Z가-힣\\s]{2,30}$")) {
            if (name.trim().isEmpty()) {
                System.out.print("[이름의 입력값이 없습니다]");
            } else if (name.length() > 30) {
                System.out.print("[이름이 너무 깁니다]");
            } else if (name.length() < 2) {
                System.out.print("[이름이 너무 짧습니다]");
            } else {
                System.out.print("[이름 형식이 올바르지 않습니다]");
            }
            return ResultCode.IS_FAIL;
        }

// 이메일: 5~100자, 이메일 형식
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$") || email.length() < 5 || email.length() > 100) {
            if (email.trim().isEmpty()) {
                System.out.print("[이메일 입력값이 없습니다]");
            } else if (email.length() > 100) {
                System.out.print("[이메일이 너무 깁니다]");
            } else if (email.length() < 5) {
                System.out.print("[이메일이 너무 짧습니다]");
            } else {
                System.out.print("[이메일 형식이 올바르지 않습니다]");
            }
            return ResultCode.IS_FAIL;
        }

// 전화번호: 9~20자, 숫자/하이픈/공백만 허용
        if (!phone_number.matches("^[0-9\\-\\s]{9,20}$")) {
            if (phone_number.trim().isEmpty()) {
                System.out.print("[전화번호 입력값이 없습니다]");
            } else if (phone_number.length() > 20) {
                System.out.print("[전화번호가 너무 깁니다]");
            } else if (phone_number.length() < 9) {
                System.out.print("[전화번호가 너무 짧습니다]");
            } else {
                System.out.print("[전화번호 형식이 올바르지 않습니다]");
            }
            return ResultCode.IS_FAIL;
        }
        return userManageDao.updateUser(name, email, phone_number, userId);
    }
}
