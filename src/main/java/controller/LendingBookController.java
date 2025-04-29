package controller;

import service.LendingBookService;

import java.time.LocalDateTime;

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
}
