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

        while (true) {
            System.out.println("\n========================================");
            System.out.println("🔐  회원 기능 메뉴");
            System.out.println("----------------------------------------");
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("3. 비밀번호 변경");
            System.out.println("4. 로그아웃");
            System.out.println("exit. 종료");
            System.out.println("========================================");
            System.out.print("메뉴를 선택하세요: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("👋 회원 기능을 종료합니다.");
                break;
            } else if (input.equals("1")) {
                System.out.println("📝 이름: ");
                String name = scanner.nextLine();

                System.out.println("📧 이메일: ");
                String email = scanner.nextLine();

                System.out.println("📱 전화번호: ");
                String phone = scanner.nextLine();

                int type = 0; // 자동으로 일반유저 설정

                System.out.println("🔒 비밀번호: ");
                String pw = scanner.nextLine();

                UserSignUpDto signUpDto = new UserSignUpDto(name, email, phone, type, pw);
                signUp(signUpDto);

            } else if (input.equals("2")) {
                System.out.println("📧 이메일: ");
                String email = scanner.nextLine();

                System.out.println("🔒 비밀번호: ");
                String pw = scanner.nextLine();

                UserLoginDto loginDto = new UserLoginDto(email, pw);
                login(loginDto);
            } else if (input.equals("3")) {
                System.out.println("📧 이메일을 입력하세요:");
                String email = scanner.nextLine();

                System.out.println("📱 등록된 전화번호를 입력하세요:");
                String phone = scanner.nextLine();

                System.out.println("🔐 새 비밀번호를 입력하세요:");
                String newPw = scanner.nextLine();

                PasswordUpdateDto updateDto = new PasswordUpdateDto(email, phone, newPw);
                resetPassword(updateDto);
            }else if (input.equals("4")) {
                logout();
            }else {
                System.out.println("⚠️ 잘못된 입력입니다. 다시 시도해주세요.");
            }
        }
    }




}
