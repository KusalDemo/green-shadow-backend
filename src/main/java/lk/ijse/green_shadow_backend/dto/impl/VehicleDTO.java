package lk.ijse.green_shadow_backend.dto.impl;

import lk.ijse.green_shadow_backend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VehicleDTO implements SuperDTO {
    private String vehicleCode;
    private String licensePlateNumber;
    private String vehicleCategory;
    private String fuelType;
    private String status;
    private String allocatedStaffMemberDetails;
    private String remarks;
    private String staffId;
    private String logCode;
}
