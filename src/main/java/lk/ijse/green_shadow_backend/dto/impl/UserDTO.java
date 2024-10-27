package lk.ijse.green_shadow_backend.dto.impl;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.ijse.green_shadow_backend.dto.SuperDTO;
import lk.ijse.green_shadow_backend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDTO implements SuperDTO {
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String roleClarificationCode;
}
