package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LendingBookDto {
    private long lendingId;
    private String title;
    private String author;
    private String publisher;
    private LocalDateTime dueDate;
}
