package lk.ijse.green_shadow_backend.service.impl;

import lk.ijse.green_shadow_backend.dao.EquipmentDAO;
import lk.ijse.green_shadow_backend.dto.impl.EquipmentDTO;
import lk.ijse.green_shadow_backend.entity.impl.Equipment;
import lk.ijse.green_shadow_backend.service.EquipmentService;
import lk.ijse.green_shadow_backend.util.AppUtil;
import lk.ijse.green_shadow_backend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentDAO equipmentDao;

    @Autowired
    private Mapping mapper;

    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        try{
            equipmentDTO.setEquipmentId(AppUtil.generateEquipmentId());
            equipmentDao.save(mapper.mapToEquipment(equipmentDTO));
        }catch (Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {
        return mapper.mapToEquipmentDTOList(equipmentDao.findAll());
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO) {
        try {
            Optional<Equipment> fetchedEquipment = equipmentDao.findById(equipmentId);
            if (fetchedEquipment.isPresent()) {
                Equipment equipment = fetchedEquipment.get();
                equipment.setName(equipmentDTO.getName());
                equipment.setType(equipmentDTO.getType());
                equipment.setStatus(equipmentDTO.getStatus());
                //equipment.setStaffId(equipmentDTO.getStaffId());
                //equipment.setFieldCode(equipmentDTO.getFieldCode());
                equipmentDao.save(equipment);
            }
        }catch (Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        try{
            equipmentDao.deleteById(equipmentId);
        }catch (Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }


}
