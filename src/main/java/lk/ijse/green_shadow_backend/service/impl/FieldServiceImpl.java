package lk.ijse.green_shadow_backend.service.impl;

import lk.ijse.green_shadow_backend.dao.FieldDAO;
import lk.ijse.green_shadow_backend.dto.impl.FieldDTO;
import lk.ijse.green_shadow_backend.entity.impl.Field;
import lk.ijse.green_shadow_backend.exception.EntryNotFoundException;
import lk.ijse.green_shadow_backend.service.FieldService;
import lk.ijse.green_shadow_backend.service.LogService;
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
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldDAO fieldDao;
    @Autowired
    private Mapping mapper;
    @Autowired
    private LogService logService;

    @Override
    public void saveField(FieldDTO fieldDTO) {
        fieldDTO.setFieldCode(AppUtil.generateFieldCode());
        fieldDao.save(mapper.mapToField(fieldDTO));
        log.info("Field saved successfully with code: {}", fieldDTO.getFieldCode());
    }

    @Override
    public List<FieldDTO> getAllFields() {
        return mapper.mapToFieldDTOList(fieldDao.findAll());
    }

    @Override
    public void updateField(String fieldCode, FieldDTO fieldDTO) {
        Optional<Field> fetchedField = fieldDao.findById(fieldCode);
        if (fetchedField.isPresent()) {
            Field field = fetchedField.get();
            field.setFieldName(fieldDTO.getFieldName());
            field.setFieldLocation(fieldDTO.getFieldLocation());
            field.setExtentSizeOfField(fieldDTO.getExtentSizeOfField());
            field.setLog(mapper.mapToLog(logService.findLog(fieldDTO.getLogCode())));
            fieldDao.save(field);
            log.info("Field updated successfully with code: {}", fieldDTO.getFieldCode());
        } else {
            throw new EntryNotFoundException("Field", fieldCode);
        }
    }

    @Override
    public void deleteField(String fieldCode) {
        fieldDao.deleteById(fieldCode);
        log.warn("Field deleted successfully with code: {}", fieldCode);
    }

    @Override
    public void uploadFieldImage(String fieldCode, String image1, String image2) {
            Optional<Field> fetchedField = fieldDao.findById(fieldCode);
            if (fetchedField.isPresent()) {
                Field field = fetchedField.get();
                if (image1 == null) {
                    field.setImage1(field.getImage1());
                } else {
                    field.setImage1(image1);
                }
                if (image2 == null) {
                    field.setImage2(field.getImage2());
                } else {
                    field.setImage2(image2);
                }
                fieldDao.save(field);
                log.info("Field image uploaded successfully with code: {}", fieldCode);
            }else {
                throw new EntryNotFoundException("Field", fieldCode);
            }
    }

    @Override
    public FieldDTO findField(String fieldCode) {
        Optional<Field> fetchedField = fieldDao.findById(fieldCode);
        if (fetchedField.isPresent()) {
            Field field = fetchedField.get();
            return mapper.mapToFieldDTO(field);
        }
        throw new EntryNotFoundException("Field", fieldCode);
    }

    @Override
    public List<FieldDTO> getFieldsByExtentSizeOfField(Double name) {
        return mapper.mapToFieldDTOList(fieldDao.findByExtentSizeOfField(name));
    }
}
