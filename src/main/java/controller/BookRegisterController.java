package controller;

import model.dto.BookDto;
import service.BookRegisterService;

import java.util.List;

public class BookRegisterController {

    private final BookRegisterService bookRegisterService;

    public BookRegisterController() {
        this.bookRegisterService = new BookRegisterService();
    }

    // 책 등록 처리
    public void resigterBook(BookDto bookDto, Long userId) {
        int result = bookRegisterService.registerBook(bookDto, userId);

        if (result == 1) {
            System.out.println("🎉 책이 성공적으로 등록되었습니다!");
        } else if (result == 0) {
            System.out.println("⚠️ 책 등록에 실패했습니다. 다시 시도해보세요.");
        } else if (result == -1) {
            System.out.println("❌ 오류가 발생했습니다. 관리자에게 문의하세요.");
        }
    }

    public void getAllBook() {
        List<BookDto> bookList = bookRegisterService.getAllBooks();
        bookList.forEach(g -> System.out.printf(
                "%d %s %s %d %d %s %d %s\n",
                g.getBookId(),        // %d
                g.getTitle(),         // %s
                g.getAuthor(),        // %s
                g.getPrice(),         // %d
                g.getCategoryId(),    // %d
                g.getPublisher(),     // %s
                g.getStatus(),        // %d
                g.getCreatedAt()      // %s (LocalDateTime → 문자열 출력)
        ));
    }

}
