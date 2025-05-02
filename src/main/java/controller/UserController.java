package controller;

import common.SessionStorage;
import model.dto.PasswordUpdateDto;
import model.dto.UserDto;
import model.dto.UserLoginDto;
import model.dto.UserSignUpDto;
import service.UserService;

import java.util.Scanner;

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
            // 로그인 성공 시 사용자 정보 가져오기
            UserDto user = userService.getUserByEmail(dto.getEmail());

            // 세션에 저장
            SessionStorage.setSession(user);

            System.out.println("🎉 로그인 성공! " + user.getName() + "님 환영합니다.");

        } else {
            System.out.println("❌ 로그인 실패: 이메일 또는 비밀번호를 확인하세요.");
        }
    }

    // 로그아웃
    public void logout() {
        if (SessionStorage.isLoggedIn()) {
            System.out.println("👋 " + SessionStorage.getCurrentUser().getName() + "님, 로그아웃 되었습니다.");
            SessionStorage.logout();
        } else {
            System.out.println("⚠️ 현재 로그인한 사용자가 없습니다.");
        }
    }

    // 비밀번호 변경
    public void resetPassword(PasswordUpdateDto dto) {
        boolean success = userService.resetPassword(dto);

        if (success) {
            System.out.println("🔐 비밀번호가 성공적으로 변경되었습니다.");
        } else {
            System.out.println("❌ 비밀번호 변경에 실패했습니다.");
        }
    }

    // 회원 기능 뷰
    public void userAuthConsoleView() {
        Scanner scanner = new Scanner(System.in);
        UserDto user = SessionStorage.getCurrentUser();

        if (user == null) {
            System.out.println("⚠️ 로그인 후 이용 가능한 메뉴입니다.");
            return;
        }

        boolean isAdmin = user.getMembershipType() == 1;

        while (true) {
            System.out.println("\n========= 🔐 계정 관리 메뉴 (" + (isAdmin ? "관리자" : "회원") + ") =========");
            System.out.println("1. 비밀번호 변경");
            System.out.println("2. 로그아웃");
            System.out.println("exit. 종료");
            System.out.print("▶ 메뉴 선택: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> {
                    System.out.print("📧 이메일: ");
                    String email = scanner.nextLine();
                    System.out.print("📱 전화번호: ");
                    String phone = scanner.nextLine();
                    System.out.print("🔐 새 비밀번호: ");
                    String newPw = scanner.nextLine();
                    resetPassword(new PasswordUpdateDto(email, phone, newPw));
                }
                case "2" -> {
                    logout();
                    return;
                }
                case "exit" -> {
                    System.out.println("👋 계정 관리 메뉴를 종료합니다.");
                    return;
                }
                default -> System.out.println("⚠️ 올바른 메뉴 번호를 입력해주세요.");
            }
        }
    }

    // 회원가입용 입력 메서드
    public void signUpConsoleView() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("📝 이름: ");
        String name = scanner.nextLine();

        System.out.print("📧 이메일: ");
        String email = scanner.nextLine();

        System.out.print("📱 전화번호: ");
        String phone = scanner.nextLine();

        System.out.print("🔒 비밀번호: ");
        String pw = scanner.nextLine();

        int type = 0; // 일반 회원

        UserSignUpDto signUpDto = new UserSignUpDto(name, email, phone, type, pw);
        signUp(signUpDto);
    }



}
