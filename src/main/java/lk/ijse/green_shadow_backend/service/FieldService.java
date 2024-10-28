package lk.ijse.green_shadow_backend.service;

import lk.ijse.green_shadow_backend.dto.impl.FieldDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO fieldDTO);
    List<FieldDTO> getAllFields();
    void updateField(String fieldCode,FieldDTO fieldDTO);
    void deleteField(String fieldCode);
    void uploadFieldImage(String fieldCode, String image1, String image2);
    FieldDTO findField(String fieldCode);
}
