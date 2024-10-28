package lk.ijse.green_shadow_backend.service.impl;

import lk.ijse.green_shadow_backend.dao.FieldDAO;
import lk.ijse.green_shadow_backend.dto.impl.FieldDTO;
import lk.ijse.green_shadow_backend.entity.impl.Field;
import lk.ijse.green_shadow_backend.service.FieldService;
import lk.ijse.green_shadow_backend.util.AppUtil;
import lk.ijse.green_shadow_backend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldDAO fieldDao;
    @Autowired
    private Mapping mapper;

    @Override
    public void saveField(FieldDTO fieldDTO) {
        try{
            fieldDTO.setFieldCode(AppUtil.generateFieldCode());
            fieldDao.save(mapper.mapToField(fieldDTO));
        }catch (Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    @Override
    public List<FieldDTO> getAllFields() {
        return mapper.mapToFieldDTOList(fieldDao.findAll());
    }

    @Override
    public void updateField(String fieldCode, FieldDTO fieldDTO) {
        try{
            Optional<Field> fetchedField = fieldDao.findById(fieldCode);
            if (fetchedField.isPresent()){
                Field field = fetchedField.get();
                field.setFieldName(fieldDTO.getFieldName());
                field.setFieldLocation(fieldDTO.getFieldLocation());
                field.setExtentSizeOfField(fieldDTO.getExtentSizeOfField());
                fieldDao.save(field);
            }
        }catch (Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    @Override
    public void deleteField(String fieldCode) {
        fieldDao.deleteById(fieldCode);
    }

    @Override
    public void uploadFieldImage(String fieldCode, String image1, String image2) {
        try{
            Optional<Field> fetchedField = fieldDao.findById(fieldCode);
            if (fetchedField.isPresent()){
                Field field = fetchedField.get();
                if (image1 == null) {field.setImage1(field.getImage1());} else {field.setImage1(image1);}
                if (image2 == null) {field.setImage2(field.getImage2());} else {field.setImage2(image2);}
                fieldDao.save(field);
            }
        }catch (Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }
}
