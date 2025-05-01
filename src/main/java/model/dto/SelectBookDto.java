package model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SelectBookDto {
    private Long bookId;       // 도서 고유 ID
    private String title;      // 도서 제목
    private String author;     // 저자
    private Long category_id; // 카테고리 id
    private String category;   // 카테고리
    private String publisher;  // 출판사
    private int price;         // 가격
    private int status;        // 대출 가능 여부 (1: 대출 가능, 0: 대출 중)
    private LocalDateTime createdAt;

    public SelectBookDto() {
    }

    // BookId와 createdAt은 Insert할때 자동생성되므로 이를 제외한 Constructor
    public SelectBookDto(String title, String author, Long category_id, String category, String publisher, int price, int status) {
        this.title = title;
        this.author = author;
        this.category_id = category_id;
        this.category = category;
        this.publisher = publisher;
        this.price = price;
        this.status = status;
    }

    public SelectBookDto(Long bookId, String title, String author, Long category_id, String category, String publisher, int price, int status
            , LocalDateTime createdAt) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category_id = category_id;
        this.category = category;
        this.publisher = publisher;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
    }
}
