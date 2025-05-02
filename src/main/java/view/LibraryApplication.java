package view;

import common.SessionStorage;
import controller.*;
import model.dto.UserDto;
import model.dto.UserLoginDto;

import java.util.Scanner;

public class LibraryApplication {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserController userController = new UserController();
    private static final LendingBookController lendingController = new LendingBookController();
    private static final RequestBookController requestController = new RequestBookController();
    private static final SearchBookController searchController = new SearchBookController();
    private static final UserManageController userManageController = new UserManageController();
    private static final BookRegisterController bookRegisterController = new BookRegisterController();

    public static void main(String[] args) {
        while (true) {
            printMainMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> handleLogin();
                case "2" -> userController.userAuthConsoleView();
                case "3" -> searchController.searchBookSystem();
                case "4" -> {
                    System.out.println("📕 시스템을 종료합니다.");
                    return;
                }
                default -> System.out.println("⚠️ 올바른 번호를 입력하세요");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n========= 📚 도서관 시스템 메인 메뉴 =========");
        System.out.println("1. 🔑 로그인하기");
        System.out.println("2. 📝 회원가입");
        System.out.println("3. 🔍 도서검색시스템");
        System.out.println("4. 🚪 종료");
        System.out.print("▶ 메뉴 선택: ");
    }

    private static void handleLogin() {
        System.out.print("📧 이메일: ");
        String email = scanner.nextLine();
        System.out.print("🔒 비밀번호: ");
        String pw = scanner.nextLine();

        userController.login(new UserLoginDto(email, pw));

        if (SessionStorage.isLoggedIn()) {
            UserDto user = SessionStorage.getCurrentUser();
            if (user.getMembershipType() == 1) {
                showAdminMenu(user.getUserId());
            } else {
                showUserMenu(user.getUserId());
            }
        }
    }

    private static void showUserMenu(Long userId) {
        while (true) {
            System.out.println("\n========= 👤 회원 전용 메뉴 =========");
            System.out.println("1. 🔐 계정 관리");
            System.out.println("2. 📖 대출/반납 시스템");
            System.out.println("3. 📝 희망도서 신청");
            System.out.println("4. 🔍 도서 검색");
            System.out.println("5. ↩ 로그아웃");
            System.out.print("▶ 메뉴 선택: ");

            switch (scanner.nextLine()) {
                case "1" -> userController.userAuthConsoleView();
                case "2" -> lendingController.lendBookSystem(userId);
                case "3" -> requestController.requestBookSystem();
                case "4" -> searchController.searchBookSystem();
                case "5" -> {
                    userController.logout();
                    return;
                }
                default -> System.out.println("⚠️ 올바른 번호를 입력하세요");
            }
        }
    }

    private static void showAdminMenu(Long adminId) {
        while (true) {
            System.out.println("\n========= 👑 관리자 전용 메뉴 =========");
            System.out.println("1. 🔐 계정 관리");
            System.out.println("2. 👥 회원 관리");
            System.out.println("3. 📚 도서 등록/조회");
            System.out.println("4. 📋 희망도서 승인");
            System.out.println("5. ↩ 로그아웃");
            System.out.print("▶ 메뉴 선택: ");

            switch (scanner.nextLine()) {
                case "1" -> userController.userAuthConsoleView();
                case "2" -> userManageController.userManageSystem(adminId);
                case "3" -> bookRegisterController.bookRegisterSystem(adminId);
                case "4" -> requestController.requestBookProcessSystem();
                case "5" -> {
                    userController.logout();
                    return;
                }
                default -> System.out.println("⚠️ 올바른 번호를 입력하세요");
            }
        }
    }
}

