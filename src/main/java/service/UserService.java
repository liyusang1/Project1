package service;

import model.dao.UserDao;
import model.dto.PasswordUpdateDto;
import model.dto.UserDto;
import model.dto.UserLoginDto;
import model.dto.UserSignUpDto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final UserDao userDao = new UserDao();
    private final Map<String, Integer> loginFailCountMap = new HashMap<>();

    // 회원가입 처리
    public boolean signUp(UserSignUpDto dto) {

        // 이메일 형식 검사
        if (!isValidEmail(dto.getEmail())) {
            System.out.println("⚠️ 이메일 형식이 올바르지 않습니다.");
            return false;
        }

        //이메일 중복 여부
        if (userDao.isEmailDuplicate(dto.getEmail())) {
            System.out.println("⚠️ 이미 사용 중인 이메일입니다.");
            return false;
        }

        // 비밀번호 해시 처리
        String hashedPassword = hashPassword(dto.getPassword());
        dto.setPassword(hashedPassword); // 암호화된 값으로 덮어쓰기

        int result = userDao.insertUser(dto);
        return result > 0;
    }

    // 이메일 형식 검사 메서드
    public boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    // 사용자의 평문 비밀번호를 SHA-256 해시 알고리즘으로 암호화하는 메서드
    // 리턴값은 16진수 문자열 형태의 해시값이며, DB에 저장되는 최종 형태다
    private String hashPassword(String password) {
        try {
            // SHA-256 해시 알고리즘을 사용하는 MessageDigest 인스턴스 생성
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // 문자열은 직접 해시 처리할 수 없기 때문에 먼저 byte[]로 변환
            // 해시 함수는 내부적으로 이진 데이터(byte 배열)를 기반으로 계산함
            byte[] bytes = md.digest(password.getBytes());

            // 해시 결과(byte 배열)를 사람이 읽을 수 있는 16진수 문자열로 변환
            // 이유: DB 저장 및 비교를 위해 문자열 형태가 더 적절하고 직관적임
            // 1바이트 = 2자리 16진수 → 최종 결과는 64자리 문자열
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // SHA-256 알고리즘이 없을 경우 예외 발생
            // (실제로는 거의 발생하지 않지만, 예외 처리를 강제하기 때문에 RuntimeException으로 감싸서 던짐)
            throw new RuntimeException(e);
        }
    }

    // 로그인 처리
    public boolean login(UserLoginDto dto) {
        String email = dto.getEmail();

        // 🔒 1. 이메일 형식 유효성 검사
        if (!isValidEmail(email)) {
            System.out.println("⚠️ 유효하지 않은 이메일 형식입니다.");
            return false; // 실패 카운트는 증가시키지 않음
        }

        // 🔐 2. 로그인 차단 여부 검사
        if (loginFailCountMap.getOrDefault(email, 0) >= 5) {
            System.out.println("⛔ 비밀번호를 5회 이상 틀리셨습니다. 로그인 제한됩니다.");
            return false;
        }

        // 🔐 3. 비밀번호 암호화
        String hashedPassword = hashPassword(dto.getPassword());
        dto.setPassword(hashedPassword);

        // 🔐 4. 로그인 시도
        boolean success = userDao.loginUser(dto);

        if (success) {
            loginFailCountMap.remove(email); // 성공 시 기록 제거
        } else {
            int failCount = loginFailCountMap.getOrDefault(email, 0) + 1;
            loginFailCountMap.put(email, failCount);

            System.out.println("❌ 로그인 실패 (" + failCount + "회)");

            if (failCount == 5) {
                System.out.println("⛔ 5회 이상 틀리셨습니다. 다음부터 입력이 제한됩니다.");
            }
        }

        return success;
    }

    public boolean isLoginBlocked(String email) {
        return loginFailCountMap.getOrDefault(email, 0) >= 5;
    }

    // 비밀번호 변경
    public boolean resetPassword(PasswordUpdateDto dto) {
        if (!userDao.verifyUserByEmailAndPhone(dto.getEmail(), dto.getPhoneNumber())) {

            System.out.println("⚠️ 이메일이나 전화번호가 일치하지 않습니다.");
            return false;
        }

        String hashedPassword = hashPassword(dto.getNewPassword()); // 암호화된 비밀번호로 덮어쓰기
        return userDao.updatePassword(dto.getEmail(), hashedPassword);
    }

    public UserDto getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

}
