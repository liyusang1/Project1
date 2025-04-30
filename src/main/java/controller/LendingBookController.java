package controller;

import model.dto.LendingBookDto;
import service.LendingBookService;

import java.time.LocalDateTime;
import java.util.List;

public class LendingBookController {
    private final LendingBookService lendingBookService;

    public LendingBookController() {
        this.lendingBookService = new LendingBookService();
    }

    // 대출처리
    public void insertLending(Long bookId, Long userId, LocalDateTime dueDate) {
        int result = lendingBookService.insertLending(bookId, userId, dueDate);
        if (result == 1) {
            System.out.println("🎉 대출 성공");
        } else if (result == 0) {
            System.out.println("⚠️ 대출 실패");
        } else if (result == -1) {
            System.out.println("❌ 오류가 발생했습니다. 관리자에게 문의하세요.");
        }
    }

    // 반납 처리
    public void returnBook(Long lendingId, Long userId) {
        int result = lendingBookService.returnBook(lendingId, userId);
        if (result == 1) {
            System.out.println("🎉 반납 성공");
        } else if (result == 0) {
            System.out.println("⚠️ 반납 실패");
        } else if (result == -1) {
            System.out.println("❌ 오류가 발생했습니다. 관리자에게 문의하세요.");
        }
    }

    // 대출 목록 조회
    public void getAllLending(Long userId) {
        List<LendingBookDto> lendingBookDtoList = lendingBookService.getLendingList(userId);
        if (lendingBookDtoList.isEmpty()) {
            System.out.println("⚠️ 대출 목록이 없습니다!");
        }
        lendingBookDtoList.forEach(g -> System.out.printf("🎉 대출번호 : %d -> 책 이름 : %s, 저자 : %s, 출판사:  %s\n",
                g.getLendingId(), g.getTitle(), g.getAuthor(), g.getPublisher()));
    }
}
