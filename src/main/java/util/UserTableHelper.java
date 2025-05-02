package util;

import model.dto.UserDto;

import java.util.List;

public class UserTableHelper {

    public static void displayUsers(List<UserDto>  userList) {
        // 컬럼 폭: ID, 이름, 이메일, 전화번호, 타입, 가입일, 수정일
        int[] colWidths = {6, 8, 16, 16, 11, 15, 15};
        String[] headers = {"ID", "이름", "이메일", "전화번호", "타입", "가입일", "수정일"};

        // 라인 생성
        StringBuilder line = new StringBuilder("+");
        for (int w : colWidths) {
            for (int i = 0; i < w + 2; i++) line.append("-");
            line.append("+");
        }
        System.out.println(line);

        // 헤더 출력
        System.out.print("|");
        for (int i = 0; i < headers.length; i++) {
            System.out.print(" " + padRight(headers[i], colWidths[i]) + " |");
        }
        System.out.println();
        System.out.println(line);

        // 데이터 출력
        for (UserDto user : userList) {
            String typeStr = (user.getMembershipType() == 1) ? "관리자" : "일반회원";
            String createdAtStr = user.getCreatedAt() != null ? user.getCreatedAt().toString() : "";
            String updatedAtStr = user.getUpdatedAt() != null ? user.getUpdatedAt().toString() : "";
            System.out.print("| " + cutAndPad(user.getUserId() != null ? user.getUserId().toString() : "", colWidths[0]) + " ");
            System.out.print("| " + cutAndPad(user.getName() != null ? user.getName() : "", colWidths[1]) + " ");
            System.out.print("| " + cutAndPad(user.getEmail() != null ? user.getEmail() : "", colWidths[2]) + " ");
            System.out.print("| " + cutAndPad(user.getPhoneNumber() != null ? user.getPhoneNumber() : "", colWidths[3]) + " ");
            System.out.print("| " + cutAndPad(typeStr, colWidths[4]) + " ");
            System.out.print("| " + cutAndPad(createdAtStr, colWidths[5]) + " ");
            System.out.print("| " + cutAndPad(updatedAtStr, colWidths[6]) + " |\n");
        }
        System.out.println(line);
    }

    // 한글/영문 혼합 폭 계산
    public static int getDisplayWidth(String s) {
        int width = 0;
        for (char c : s.toCharArray()) {
            if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.HANGUL_SYLLABLES ||
                    Character.UnicodeBlock.of(c) == Character.UnicodeBlock.HANGUL_JAMO ||
                    Character.UnicodeBlock.of(c) == Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO) {
                width += 2;
            } else {
                width += 1;
            }
        }
        return width;
    }

    // 오른쪽 패딩
    public static String padRight(String s, int n) {
        int padSize = n - getDisplayWidth(s);
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < padSize; i++) sb.append(" ");
        return sb.toString();
    }

    // 글자수 제한+패딩 (넘치면 ...으로 표시)
    public static String cutAndPad(String s, int maxWidth) {
        if (s == null) return padRight("", maxWidth);
        int width = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            int charWidth = (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.HANGUL_SYLLABLES ||
                    Character.UnicodeBlock.of(c) == Character.UnicodeBlock.HANGUL_JAMO ||
                    Character.UnicodeBlock.of(c) == Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO) ? 2 : 1;
            if (width + charWidth > maxWidth - 3) {
                sb.append("...");
                return padRight(sb.toString(), maxWidth);
            }
            sb.append(c);
            width += charWidth;
        }
        return padRight(sb.toString(), maxWidth);
    }


}
