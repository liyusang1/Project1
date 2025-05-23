package common;

import model.dto.BookShowDto;
import util.PaginationUtil;

public class BookListFormatter implements PaginationUtil.Formatter<BookShowDto> {

    private final String format = "| %-6s | %-22s | %-16s | %-18s | %-11s | %-10s |\n";
    private final String line = "+--------+------------------------+------------------+--------------------+-------------+------------+";

    @Override
    public void printHeader() {
        System.out.println(line);
        System.out.printf(format, "ID", "도서명", "저자", "출판사", "카테고리", "대출상태");
        System.out.println(line);
    }

    @Override
    public void printRow(BookShowDto b) {
        String statusText = switch (b.getStatus()) {
            case 1 -> "대출가능";
            case 0 -> "대출중";
            default -> "알 수 없음";
        };
        System.out.printf(format,
                b.getBookId(),
                truncate(b.getTitle().trim(), 22),      // 도서명 고정 너비 처리
                truncate(b.getAuthor().trim(), 16),
                truncate(b.getPublisher().trim(), 18),
                b.getCategory(),
                statusText
        );
    }

    @Override
    public void printFooter() {
        System.out.println(line);
    }

    private String truncate(String text, int maxLength) {
        if (text == null) return "";
        return text.length() <= maxLength ? text : text.substring(0, maxLength - 1) + "…";
    }
}



