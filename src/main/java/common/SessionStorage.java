package common;

import model.dto.UserDto;

/**
 * [SessionStorage]
 * 로그인한 사용자 정보를 전역으로 저장하는 클래스
 *
 * 콘솔이나 GUI 환경에서는 웹처럼 HttpSession 같은 내장 세션 관리가 없기 때문에,
 * 직접 전역 변수를 통해 로그인 상태를 공통적으로 저장/관리할 수 있는 구조 필요
 *
 * 로그인 상태 확인, 관리자 권한 분기, 로그아웃 처리 등에 활용
 */
public class SessionStorage {

    // 현재 로그인된 사용자 정보를 담는 변수 (null이면 로그인 안 된 상태)
    private static UserDto currentUser = null;

    // 세션에 사용자 정보를 저장
    public static void setSession(UserDto user) {
        currentUser = user;
    }

    // 현재 로그인된 사용자 반환
    public static UserDto getCurrentUser() {
        return currentUser;
    }

    // 로그인 여부 확인
    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    // 관리자 여부 확인 (0: 일반, 1: 관리자)
    public static boolean isAdmin() {
        return currentUser != null && currentUser.getMembershipType() == 1;
    }

    // 로그아웃
    public static void logout() {
        currentUser = null;
    }
}
