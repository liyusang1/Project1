package model.dto;

import lombok.Data;

@Data
public class BookShowDto {
    private Long bookId;
    private String title;
    private String author;
    private String publisher;
    private String category;
    private int status;

    public BookShowDto() {}

    public BookShowDto(Long bookId, int status, String category, String publisher, String author, String title) {
        this.bookId = bookId;
        this.status = status;
        this.category = category;
        this.publisher = publisher;
        this.author = author;
        this.title = title;
    }
}
