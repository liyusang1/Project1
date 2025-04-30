package controller;

import model.dto.UserDto;
import service.UserManageSerivice;
import java.util.ArrayList;
import java.util.List;

public class UserManageController {

    private final UserManageSerivice userManageSerivice;

    public UserManageController() {
        this.userManageSerivice = new UserManageSerivice();
    }

    public void getAllUsers(Long managerId) {
        try {
            List<UserDto> userList = userManageSerivice.getAllUsers(managerId);
            for (UserDto user : userList) {
                StringBuilder format = new StringBuilder();
                List<Object> values = new ArrayList<>();

                if (user.getUserId() != null) {
                    format.append("ID: %d ");
                    values.add(user.getUserId());
                }
                if (user.getName() != null) {
                    format.append("Name: %s ");
                    values.add(user.getName());
                }
                if (user.getEmail() != null) {
                    format.append("Email: %s ");
                    values.add(user.getEmail());
                }
                if (user.getPhoneNumber() != null) {
                    format.append("Phone: %s ");
                    values.add(user.getPhoneNumber());
                }

                if (user.getMembershipType() == 0) {
                    format.append("Type: 일반회원 ");
                } else if (user.getMembershipType() == 1) {
                    format.append("Type: 관리자 ");
                }
//                if (user.getCreatedAt() != null) {
//                    format.append("Created: %s ");
//                    values.add(user.getCreatedAt().toString());
//                }
//                if (user.getUpdatedAt() != null) {
//                    format.append("Updated: %s ");
//                    values.add(user.getUpdatedAt().toString());
//                }

                if (user.getPassword() != null) {
                    format.append("Password: %s ");
                    values.add(user.getPassword());
                }

                // 출력
                System.out.printf(format.toString().trim() + "%n", values.toArray());
            }

        } catch (NullPointerException e) {
            System.out.println("❌ 관리자만 접근 가능합니다.");
        }
    }

    public void getUserById(Long userId, Long managerId) {

        try {
            List<UserDto> userList = userManageSerivice.getUserById(userId, managerId);
            userList.forEach(user -> System.out.printf(
                    "%d %s %s %s %d %s %s %s\n",
                    user.getUserId(),        // %d
                    user.getName(),         // %s
                    user.getEmail(),        // %s
                    user.getPhoneNumber(),       // %s
                    user.getMembershipType(),    // %d
                    user.getCreatedAt(),     // %s (LocalDateTime → 문자열 출력)
                    user.getUpdatedAt(),        // %s (LocalDateTime → 문자열 출력)
                    user.getPassword()      // %s
            ));
        } catch (NullPointerException e) {
            e.getMessage();
            System.out.println("❌관리자만 접근 가능합니다.");
        }
    }

    public void updateUser(String name, String email, String phone_number, Long userId) {
        int result = userManageSerivice.updateUser(name, email, phone_number, userId);
        if (result == 1) {
            System.out.println("🎉 회원정보가 성공적으로 수정 되었습니다!");
        } else if (result == 0) {
            System.out.println("⚠️ 회원정보 수정에 실패했습니다. 다시 시도해보세요.");
        } else if (result == -1) {
            System.out.println("❌ 오류가 발생했습니다. 관리자에게 문의하세요.");
        }
    }

    // 유저 삭제
    public void deleteUser(Long userId, Long managerId) {
        try {
            int result = userManageSerivice.deleteUser(userId, managerId);
            if (result == 1) {
                System.out.println("🎉" + userId + "번 유저 삭제성공");
            } else if (result == 0) {
                System.out.println("⚠️삭제 실패!");
            } else if (result == -1) {
                System.out.println("❌삭제실패 - 오류 발생!");
            }
        } catch (NullPointerException e) {
            System.out.println("❌관리자만 접근 가능합니다.");
        }
    }

}
