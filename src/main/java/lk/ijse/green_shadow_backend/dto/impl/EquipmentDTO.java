package lk.ijse.green_shadow_backend.dto.impl;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.ijse.green_shadow_backend.dto.SuperDTO;
import lk.ijse.green_shadow_backend.entity.EquipmentStatus;
import lk.ijse.green_shadow_backend.entity.EquipmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EquipmentDTO implements SuperDTO {
    private String equipmentId;
    private String name;
    @Enumerated(EnumType.STRING)
    private EquipmentType type;
    @Enumerated(EnumType.STRING)
    private EquipmentStatus status;
    private String staffId;
    private String fieldCode;
}