package model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RequestsDto {
    private int request_id;
    private int user_id;
    private String request_book;
    private String request_author;
    private String request_publisher;
    private Date create_at;
    private int status;

    public RequestsDto() {}
    public RequestsDto(int request_id, int user_id, String request_book,
                       String request_author, String request_publisher,
                       Date create_at, int status) {
        this.request_id = request_id;
        this.user_id = user_id;
        this.request_book = request_book;
        this.request_author = request_author;
        this.request_publisher = request_publisher;
        this.create_at = create_at;
        this.status = status;
    }
}
