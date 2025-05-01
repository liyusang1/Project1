package util;

import model.dto.SelectBookDto;

import java.util.List;

public class BookTableHelper {

    public static void displayBooks(List<SelectBookDto> bookList) {
        // 컬럼 폭: 책ID, 제목, 저자, 가격, 카테고리, 출판사, 상태, 등록일자
        int[] colWidths = {6, 20, 10, 7, 10, 10, 12, 13};
        String[] headers = {"책ID", "제목", "저자", "가격", "카테고리", "출판사", "상태", "등록일자"};

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
        for (SelectBookDto book : bookList) {
            System.out.print("| " + cutAndPad(book.getBookId() != null ? book.getBookId().toString() : "", colWidths[0]) + " ");
            System.out.print("| " + cutAndPad(book.getTitle() != null ? book.getTitle() : "", colWidths[1]) + " ");
            System.out.print("| " + cutAndPad(book.getAuthor() != null ? book.getAuthor() : "", colWidths[2]) + " ");
            System.out.print("| " + cutAndPad(book.getPrice() > 0 ? String.valueOf(book.getPrice()) : "", colWidths[3]) + " ");
            System.out.print("| " + cutAndPad(book.getCategory() != null ? book.getCategory() : "", colWidths[4]) + " ");
            System.out.print("| " + cutAndPad(book.getPublisher() != null ? book.getPublisher() : "", colWidths[5]) + " ");
            System.out.print("| " + cutAndPad(book.getStatus() == 1 ? "대출가능" : "대출 중", colWidths[6]) + " ");
            System.out.print("| " + cutAndPad(book.getCreatedAt() != null ? book.getCreatedAt().toString() : "", colWidths[7]) + " |\n");
        }
        System.out.println(line);
    }

    // 한글/영문 혼합 폭 계산
    public static int getDisplayWidth(String s) {
        if (s == null) return 0;
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
        if (s == null) s = "";
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