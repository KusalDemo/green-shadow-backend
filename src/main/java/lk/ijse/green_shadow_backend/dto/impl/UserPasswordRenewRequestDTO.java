package lk.ijse.green_shadow_backend.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserPasswordRenewRequestDTO {
    private UserDTO userDTO;
    private String newPassword;
}
