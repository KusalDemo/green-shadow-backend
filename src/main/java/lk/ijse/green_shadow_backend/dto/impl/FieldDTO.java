package lk.ijse.green_shadow_backend.dto.impl;

import lk.ijse.green_shadow_backend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FieldDTO implements SuperDTO {
    private String fieldCode;
    private String fieldName;
    private Point fieldLocation;
    private Double extentSizeOfField;
    private List<CropDTO> crops;
    private List<StaffDTO> staff;
    private String image1;
    private String image2;
    private List<EquipmentDTO> equipments;
    private String logCode;
}
