package lk.ijse.green_shadow_backend.entity.impl;

import jakarta.persistence.*;
import lk.ijse.green_shadow_backend.entity.EquipmentStatus;
import lk.ijse.green_shadow_backend.entity.EquipmentType;
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
public class Equipment implements SuperEntity {
    @Id
    private String equipmentId;
    private String name;
    @Enumerated(EnumType.STRING)
    private EquipmentType type;
    @Enumerated(EnumType.STRING)
    private EquipmentStatus status;
    @ManyToOne
    @JoinColumn(name = "staffId")
    private Staff staff;
    @ManyToOne
    @JoinColumn(name = "fieldCode")
    private Field field;
}
