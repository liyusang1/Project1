package controller;

import model.dto.UserLoginDto;
import model.dto.UserSignUpDto;
import service.UserService;

public class UserController {

    private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    // 회원가입
    public void signUp(UserSignUpDto dto) {
        boolean success = userService.signUp(dto);
        if (success) {
            System.out.println("🎉 회원가입 성공");
        } else {
            System.out.println("❌ 회원가입 실패: 다시 시도해주세요.");
        }
    }

    // 로그인
    public void login(UserLoginDto dto) {
        boolean success = userService.login(dto);
        if (success) {
            System.out.println("🎉 로그인 성공");
        } else {
            System.out.println("❌ 로그인 실패: 이메일 또는 비밀번호를 확인하세요.");
        }
    }

    // 비밀번호 변경
    public void resetPassword(String email, String phoneNumber, String newPassword) {
        boolean success = userService.resetPassword(email, phoneNumber, newPassword);
        if (success) {
            System.out.println("🔐 비밀번호가 성공적으로 변경되었습니다.");
        } else {
            System.out.println("❌ 비밀번호 변경에 실패했습니다.");
        }
    }


}
