package lk.ijse.green_shadow_backend.service;

import lk.ijse.green_shadow_backend.dto.impl.CropDTO;
import lk.ijse.green_shadow_backend.entity.impl.Crop;

import java.util.List;

public interface CropService {
    void saveCrop(CropDTO cropDTO);
    List<CropDTO> getAllCrops();
    void saveImage(String cropCode,String image);
    void updateCrop(CropDTO cropDTO);
    void deleteCrop(String cropCode);
    CropDTO findCrop(String cropCode);
    List<CropDTO> getCropsByName(String name);
}
