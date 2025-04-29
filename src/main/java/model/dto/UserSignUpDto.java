package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpDto {
    private String name;
    private String email;
    private String phoneNumber;
    private int membershipType; // 0: 일반 회원, 1: 관리자
    private String password;
}
