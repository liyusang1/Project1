package common;

import model.dto.BookShowDto;
import util.PaginationUtil;

public class BookListFormatter implements PaginationUtil.Formatter<BookShowDto> {
    private final String format = "| %-22s | %-16s | %-18s | %-11s | %-10s |\n";
    private final String line   = "+------------------------+------------------+--------------------+-------------+------------+";

    @Override
    public void printHeader() {
        System.out.println(line);
        System.out.printf(format,
                "도서명",
                "저    자",  // 너비 맞춤을 위해 공백 추가
                "출   판   사",
                "카 테 고 리",
                "대 출 상 태"
        );
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
                truncate(b.getTitle().trim(), 24),
                truncate(b.getAuthor().trim(), 14),
                truncate(b.getPublisher().trim(), 16),
                truncate(b.getCategory().trim(), 12),
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


