package controller;

import model.dto.UserDto;
import service.UserManageSerivice;

import java.util.List;
import java.util.Scanner;

import static util.UserTableHelper.displayUsers;

public class UserManageController {

    private final UserManageSerivice userManageSerivice;

    public UserManageController() {
        this.userManageSerivice = new UserManageSerivice();
    }

    public void getAllUsers(int option, Long managerId) {
        try {
            List<UserDto> userList = userManageSerivice.getAllUsers(option, managerId);
            displayUsers(userList);

        } catch (NullPointerException e) {
            System.out.println("❌ 관리자만 접근 가능합니다.");
        }
    }

    public void getUserById(Long userId, Long managerId) {
        try {
            List<UserDto> userList = userManageSerivice.getUserById(userId, managerId);

            for (UserDto user : userList) {
                String typeStr = user.getMembershipType() == 1 ? "관리자" : "일반회원";
                System.out.printf(
                        "[회원 상세 정보]\n" +
                                "ID : %s\n이름 : %s\n이메일 : %s\n전화번호 : %s\n타입 : %s\n가입일 : %s\n수정일 : %s\n\n",
                        user.getUserId(),
                        user.getName(),
                        user.getEmail(),
                        user.getPhoneNumber(),
                        typeStr,
                        user.getCreatedAt(),
                        user.getUpdatedAt()
                );
            }
        } catch (NullPointerException e) {
            System.out.println("❌관리자만 접근 가능합니다.");
        }
    }

    public void updateUser(String name, String email, String phone_number, Long userId) {
        int result = userManageSerivice.updateUser(name, email, phone_number, userId);
        if (result == 1) {
            System.out.println("✅ 회원정보가 성공적으로 수정 되었습니다!");
        } else if (result == 0) {
            System.out.println("⚠️ 회원정보 수정에 실패했습니다. 다시 시도해보세요.");
        } else if (result == -1) {
            System.out.println("❌ 오류가 발생했습니다. 관리자에게 문의하세요.");
        }
    }

    // 유저 삭제
    public void deleteUser(Long userId, Long managerId) {

        int result = userManageSerivice.deleteUser(userId, managerId);
        if (result == 1) {
            System.out.println("\uD83D\uDDD1\uFE0F" + userId + "번 유저 삭제성공");
        } else if (result == 0) {
            System.out.println("❌관리자만 접근 가능합니다.");
        } else if (result == -1) {
            System.out.println("⚠️ 존재하지 않는 유저 입니다.");
        }
    }

    // User 관리 View
    public void userManageSystem() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== 👤 회원 관리 시스템 ===");
            System.out.println("1. 전체 회원 목록 조회");
            System.out.println("2. 회원 상세 조회");
            System.out.println("3. 회원 정보 수정");
            System.out.println("4. 회원 삭제");
            System.out.println("0. 종료");
            System.out.print("메뉴를 선택하세요: ");

            String input = sc.nextLine();

            // 전역변수로 관리자 아이디 받아야함
            Long managerId = 1L;
            switch (input) {
                case "1" -> { // 전체 회원 목록
                    System.out.println("1: 전체 회원 조회, 2: 관리자 조회, 3: 일반회원 조회, 4: 가입 기준 정렬");
                    int option = sc.nextInt();
                    sc.nextLine();
                    getAllUsers(option, managerId); // 표 형태로 출력
                }
                case "2" -> { // 회원 상세 조회
                    System.out.print("조회할 회원 ID: ");
                    Long userId;
                    try {
                        userId = Long.parseLong(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("회원 ID는 숫자로 입력하세요.");
                        break;
                    }
                    System.out.print("관리자 ID: ");
                    getUserById(userId, managerId); // 표 형태로 출력
                }
                case "3" -> { // 회원 정보 수정
                    System.out.print("수정할 회원 ID: ");
                    Long userId;
                    try {
                        userId = Long.parseLong(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("회원 ID는 숫자로 입력하세요.");
                        break;
                    }
                    System.out.print("이름: ");
                    String name = sc.nextLine();
                    System.out.print("이메일: ");
                    String email = sc.nextLine();
                    System.out.print("전화번호: ");
                    String phone = sc.nextLine();

                    updateUser(name, email, phone, userId);
                }
                case "4" -> { // 회원 삭제
                    System.out.print("삭제할 회원 ID: ");
                    Long userId;
                    try {
                        userId = Long.parseLong(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("회원 ID는 숫자로 입력하세요.");
                        break;
                    }
                    deleteUser(userId, managerId);
                }
                case "0" -> {
                    System.out.println("👋 회원 관리 시스템을 종료합니다.");
                    return;
                }
                default -> System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }

}





