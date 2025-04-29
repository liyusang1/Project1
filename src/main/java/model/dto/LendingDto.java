package model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LendingDto {
    private int lending_id;
    private int book_id;
    private int user_id;
    private LocalDateTime created_at;
    private LocalDateTime due_date; //반납예정일
    private LocalDateTime return_date; //실제 반납일
    private int status; //0:대출중, 1:반납완료

    public LendingDto() {
    }

    public LendingDto(int book_id, int user_id, LocalDateTime created_at, LocalDateTime due_date, LocalDateTime return_date, int status) {
        this.book_id = book_id;
        this.user_id = user_id;
        this.created_at = created_at;
        this.due_date = due_date;
        this.return_date = return_date;
        this.status = status;
    }
}
