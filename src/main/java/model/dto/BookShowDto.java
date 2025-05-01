package model.dto;

import lombok.Data;

@Data
public class BookShowDto {
    private String title;
    private String author;
    private String publisher;
    private String category;
    private int status;

    public BookShowDto() {}

    public BookShowDto(int status, String category, String publisher, String author, String title) {
        this.status = status;
        this.category = category;
        this.publisher = publisher;
        this.author = author;
        this.title = title;
    }
}
