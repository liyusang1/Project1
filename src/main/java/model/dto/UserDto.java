package model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long userId;
    private String name;
    private String email;
    private String phoneNumber;
    private int membershipType; //0:일반유저 , 1:관리자
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String password;

    public UserDto() {
    }

    public UserDto(Long userId, String name, String email, String phoneNumber, int membershipType, LocalDateTime createdAt, LocalDateTime updatedAt, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.membershipType = membershipType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.password = password;
    }
}