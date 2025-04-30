package model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookDto {
    private Long bookId;       // 도서 고유 ID
    private String title;      // 도서 제목
    private String author;     // 저자
    private Long categoryId;   // 카테고리
    private String publisher;  // 출판사
    private int price;         // 가격
    private int status;        // 대출 가능 여부 (1: 대출 가능, 0: 대출 중)
    private LocalDateTime createdAt;

    public BookDto() {
    }

    // BookId와 createdAt은 Insert할때 자동생성되므로 이를 제외한 Constructor
    public BookDto(String title, String author, Long categoryId, String publisher, int price, int status) {
        this.title = title;
        this.author = author;
        this.categoryId = categoryId;
        this.publisher = publisher;
        this.price = price;
        this.status = status;
    }

    public BookDto(Long bookId, String title, String author, Long categoryId, String publisher, int price, int status
            , LocalDateTime createdAt) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.categoryId = categoryId;
        this.publisher = publisher;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
    }
}
