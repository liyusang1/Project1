package model.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class RequestsDto {
    private Long requestId;
    private Long userId;
    private String requestBook;
    private String requestAuthor;
    private String requestPublisher;
    private Date createAt;
    private int status;

    public RequestsDto() {}
    public RequestsDto(Long requestId, Long userId, String requestBook,
                       String requestAuthor, String requestPublisher,
                       Date createAt, int status) {
        this.requestId = requestId;
        this.userId = userId;
        this.requestBook = requestBook;
        this.requestAuthor = requestAuthor;
        this.requestPublisher = requestPublisher;
        this.createAt = createAt;
        this.status = status;
    }
}
