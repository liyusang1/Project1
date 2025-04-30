package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [PasswordUpdateDto]
 * 비밀번호 변경 요청 시 사용되는 DTO
 *
 * 기존에는 email, phoneNumber, newPassword를 각각 파라미터로 받았지만,
 * 유지보수성과 확장성을 고려해 하나의 객체로 전달하도록 개선
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordUpdateDto {
    private String email;
    private String phoneNumber;
    private String newPassword;
}
