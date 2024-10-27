package lk.ijse.green_shadow_backend.dto.impl;

import lk.ijse.green_shadow_backend.dto.SuperDTO;
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
    private String gender;
    private Date joinedDate;
    private Date dOB;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String contactNumber;
    private String email;
    private String role;
    private List<String> fieldCodes;
    private List<String> vehicleIds;
    private List<String> equipmentIds;
}
