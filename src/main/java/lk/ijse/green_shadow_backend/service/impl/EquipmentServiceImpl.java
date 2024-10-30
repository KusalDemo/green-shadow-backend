package lk.ijse.green_shadow_backend.service.impl;

import lk.ijse.green_shadow_backend.dao.EquipmentDAO;
import lk.ijse.green_shadow_backend.dto.impl.EquipmentDTO;
import lk.ijse.green_shadow_backend.entity.impl.Equipment;
import lk.ijse.green_shadow_backend.exception.EntryNotFoundException;
import lk.ijse.green_shadow_backend.service.EquipmentService;
import lk.ijse.green_shadow_backend.service.FieldService;
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
    @Autowired
    private FieldService fieldService;
    @Autowired
    private StaffServiceImpl staffService;

    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        equipmentDTO.setEquipmentId(AppUtil.generateEquipmentId());
        equipmentDao.save(mapper.mapToEquipment(equipmentDTO));
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {
        return mapper.mapToEquipmentDTOList(equipmentDao.findAll());
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO) {
        Optional<Equipment> fetchedEquipment = equipmentDao.findById(equipmentId);
        if (fetchedEquipment.isPresent()) {
            Equipment equipment = fetchedEquipment.get();
            equipment.setName(equipmentDTO.getName());
            equipment.setType(equipmentDTO.getType());
            equipment.setStatus(equipmentDTO.getStatus());
            equipment.setStaff(mapper.mapToStaff(staffService.findStaff(equipmentDTO.getStaffId())));
            equipment.setField(mapper.mapToField(fieldService.findField(equipmentDTO.getFieldCode())));
            equipmentDao.save(equipment);
        } else {
            throw new EntryNotFoundException("Equipment", equipmentId);
        }
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        equipmentDao.deleteById(equipmentId);
    }

    @Override
    public EquipmentDTO findEquipment(String equipmentId) {
        return mapper.mapToEquipmentDTO(equipmentDao.findById(equipmentId).get());
    }
}
