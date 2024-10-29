package lk.ijse.green_shadow_backend.entity.impl;

import jakarta.persistence.*;
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
public class Vehicle implements SuperEntity {
    @Id
    private String vehicleCode;
    @Column(unique = true)
    private String licensePlateNumber;
    private String vehicleCategory;
    private String fuelType;
    private String status;
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "staffId")
    private Staff staff;
}
