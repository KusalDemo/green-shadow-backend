package lk.ijse.green_shadow_backend.dto.impl;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.ijse.green_shadow_backend.dto.SuperDTO;
import lk.ijse.green_shadow_backend.entity.Gender;
import lk.ijse.green_shadow_backend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StaffDTO implements SuperDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String designation;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Date joinedDate;
    private Date dob;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String contactNumber;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    private List<FieldDTO> fields;
    private List<VehicleDTO> vehicles;
    private List<EquipmentDTO> equipments;
    private String logCode;
}
