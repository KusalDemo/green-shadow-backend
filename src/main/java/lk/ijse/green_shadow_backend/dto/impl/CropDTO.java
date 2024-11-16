package lk.ijse.green_shadow_backend.dto.impl;

import lk.ijse.green_shadow_backend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CropDTO implements SuperDTO {
    private String cropCode;
    private String cropCommonName;
    private String cropScientificName;
    private String cropImage;
    private String category;
    private String cropSeason;
    private String fieldCode;
    private List<LogDTO> logCode;
}
