package lk.ijse.green_shadow_backend.service.impl;

import lk.ijse.green_shadow_backend.dao.CropDAO;
import lk.ijse.green_shadow_backend.dto.impl.CropDTO;
import lk.ijse.green_shadow_backend.entity.impl.Crop;
import lk.ijse.green_shadow_backend.exception.EntryNotFoundException;
import lk.ijse.green_shadow_backend.service.CropService;
import lk.ijse.green_shadow_backend.service.FieldService;
import lk.ijse.green_shadow_backend.util.AppUtil;
import lk.ijse.green_shadow_backend.util.Mapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class CropServiceImpl implements CropService {
    @Autowired
    private CropDAO cropDAO;
    @Autowired
    private Mapping mapper;
    @Autowired
    private FieldService fieldService;

    @Override
    public void saveCrop(CropDTO cropDTO) {
            cropDTO.setCropCode(AppUtil.generateCropCode());
            cropDAO.save(mapper.mapToCrop(cropDTO));
            log.info("Crop saved successfully with code: {}", cropDTO.getCropCode());
    }

    @Override
    public List<CropDTO> getAllCrops() {
        return mapper.mapToCropDTOList(cropDAO.findAll());
    }

    @Override
    public void updateCrop(CropDTO cropDTO) {
        Optional<Crop> fetchedCrop = cropDAO.findById(cropDTO.getCropCode());
        if (fetchedCrop.isPresent()) {
            Crop crop = fetchedCrop.get();
            crop.setCropCommonName(cropDTO.getCropCommonName());
            crop.setCropScientificName(cropDTO.getCropScientificName());
            crop.setCategory(cropDTO.getCategory());
            crop.setCropSeason(cropDTO.getCropSeason());
            crop.setField(mapper.mapToField(fieldService.findField(cropDTO.getFieldCode())));
            cropDAO.save(crop);
            log.info("Crop updated successfully with code: {}", cropDTO.getCropCode());
        }else{
            throw new EntryNotFoundException("Crop",cropDTO.getCropCode());
        }
    }

    @Override
    public void deleteCrop(String cropCode) {
        cropDAO.deleteById(cropCode);
        log.warn("Crop deleted successfully with code: {}", cropCode);
    }

    @Override
    public CropDTO findCrop(String cropCode) {
        Optional<Crop> fetchedCrop = cropDAO.findById(cropCode);
        if (fetchedCrop.isPresent()) {
            Crop crop = fetchedCrop.get();
            log.info("Crop found successfully with code: {}", cropCode);
            return mapper.mapToCropDTO(crop);
        }
        throw new EntryNotFoundException("Crop", cropCode);
    }

    @Override
    public void saveImage(String cropCode,String image) {
        Optional<Crop> fetchedCrop = cropDAO.findById(cropCode);
        if(fetchedCrop.isPresent()){
            Crop crop = fetchedCrop.get();
            crop.setCropImage(image);
            cropDAO.save(crop);
            log.info("Crop image saved successfully with code: {}", cropCode);
        }else{
            throw new EntryNotFoundException("Crop",cropCode);
        }
    }
}
