package lk.ijse.green_shadow_backend.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lk.ijse.green_shadow_backend.entity.Role;
import lk.ijse.green_shadow_backend.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class User implements SuperEntity {
    @Id
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}

