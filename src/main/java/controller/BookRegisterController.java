package controller;

import model.dto.BookDto;
import service.BookRegisterService;

import java.util.ArrayList;
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
        for (BookDto book : bookList) {
            StringBuilder format = new StringBuilder();
            List<Object> values = new ArrayList<>();

            if (book.getBookId() != null) {
                format.append("ID: %d ");
                values.add(book.getBookId());
            }
            if (book.getTitle() != null) {
                format.append("Title: %s ");
                values.add(book.getTitle());
            }
            if (book.getAuthor() != null) {
                format.append("Author: %s ");
                values.add(book.getAuthor());
            }
            if (book.getPrice() > 0) {
                format.append("Price: %d ");
                values.add(book.getPrice());
            }
            if (book.getCategoryId() != null) {
                format.append("Category: %d ");
                values.add(book.getCategoryId());
            }
            if (book.getPublisher() != null) {
                format.append("Publisher: %s ");
                values.add(book.getPublisher());
            }
            if (book.getStatus() == 1) {
                format.append("Status: 대출가능 ");
            } else if (book.getStatus() == 0) {
                format.append("Status: 대출 중");
            }
            if (book.getCreatedAt() != null) {
                format.append("Created: %s ");
                values.add(book.getCreatedAt().toString());
            }

            System.out.printf(format.toString().trim() + "%n", values.toArray());
        }
    }

    public void getAvailableBooks() {
        List<BookDto> bookList = bookRegisterService.getAvailableBooks();
        for (BookDto book : bookList) {
            StringBuilder format = new StringBuilder();
            List<Object> values = new ArrayList<>();

            if (book.getBookId() != null) {
                format.append("ID: %d ");
                values.add(book.getBookId());
            }
            if (book.getTitle() != null) {
                format.append("Title: %s ");
                values.add(book.getTitle());
            }
            if (book.getAuthor() != null) {
                format.append("Author: %s ");
                values.add(book.getAuthor());
            }
            if (book.getPrice() > 0) {
                format.append("Price: %d ");
                values.add(book.getPrice());
            }
            if (book.getCategoryId() != null) {
                format.append("Category: %d ");
                values.add(book.getCategoryId());
            }
            if (book.getPublisher() != null) {
                format.append("Publisher: %s ");
                values.add(book.getPublisher());
            }
            if (book.getStatus() == 1) {
                format.append("Status: 대출가능 ");
            } else if (book.getStatus() == 0) {
                format.append("Status: 대출 중");
            }
            if (book.getCreatedAt() != null) {
                format.append("Created: %s ");
                values.add(book.getCreatedAt().toString());
            }

            System.out.printf(format.toString().trim() + "%n", values.toArray());
        }
    }


}
