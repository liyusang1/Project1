package view;

import common.SessionStorage;
import controller.*;

import java.util.Scanner;

public class LibraryApplication {
    public static void main(String[] args) {
        // 각 컨트롤러 인스턴스 생성
        UserController userController = new UserController();
        UserManageController userManageController = new UserManageController();
        BookRegisterController bookRegisterController = new BookRegisterController();
        LendingBookController lendingBookController = new LendingBookController();
        RequestBookController requestBookController = new RequestBookController();
        SearchBookController searchBookController = new SearchBookController();

        Scanner scanner = new Scanner(System.in);

        Long loginUserId = null;

        while (true) {
            System.out.println("\n================= 📚 메인 메뉴 =================");
            System.out.println("1. 🔐 회원 기능 (회원가입/로그인/비밀번호 변경/로그아웃)");
            System.out.println("2. 👤 회원 관리 시스템 (관리자)");
            System.out.println("3. 📚 도서 등록/조회 시스템");
            System.out.println("4. 📖 도서 대출/반납 시스템");
            System.out.println("5. 📝 희망도서 신청/관리");
            System.out.println("6. 🔍 도서 검색 시스템");
            System.out.println("0. 🚪 종료");
            System.out.print("메뉴를 선택하세요: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1" -> {
                    userController.userAuthConsoleView();
                    // 로그인 성공 시 세션에서 유저 ID 받아오기
                    if (SessionStorage.isLoggedIn()) {
                        loginUserId = SessionStorage.getCurrentUser().getUserId();
                    } else {
                        loginUserId = null;
                    }
                }
                case "2" -> {
                    if (loginUserId == null) {
                        System.out.println("⚠️ 관리자 로그인이 필요합니다.");
                    } else {
                        // 관리자 권한 체크 필요시 추가
                        userManageController.userManageSystem(loginUserId);
                    }
                }
                case "3" -> {
                    if (loginUserId == null) {
                        System.out.println("⚠️ 로그인이 필요합니다.");
                    } else {
                        bookRegisterController.bookRegisterSystem(loginUserId);
                    }
                }
                case "4" -> {
                    if (loginUserId == null) {
                        System.out.println("⚠️ 로그인이 필요합니다.");
                    } else {
                        lendingBookController.lendBookSystem(loginUserId);
                    }
                }
                case "5" -> {
                    if (loginUserId == null) {
                        System.out.println("⚠️ 로그인이 필요합니다.");
                    } else {
                        System.out.println("1. 📝 희망도서 신청/조회 (회원)");
                        System.out.println("2. 🛠️ 희망도서 관리 (관리자)");
                        System.out.print("선택: ");
                        String sub = scanner.nextLine();
                        if (sub.equals("1")) {
                            requestBookController.requestBookSystem();
                        } else if (sub.equals("2")) {
                            requestBookController.requestBookProcessSystem();
                        }
                    }
                }
                case "6" -> {
                    searchBookController.searchBookSystem();
                }
                case "0" -> {
                    System.out.println("👋 프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("⚠️ 올바른 메뉴 번호를 입력하세요.");
            }
        }
    }
}
