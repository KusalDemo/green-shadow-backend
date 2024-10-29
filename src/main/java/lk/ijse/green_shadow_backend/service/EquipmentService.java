package lk.ijse.green_shadow_backend.service;

import lk.ijse.green_shadow_backend.dto.impl.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO);
    List<EquipmentDTO> getAllEquipment();
    void updateEquipment(String equipmentId,EquipmentDTO equipmentDTO);
    void deleteEquipment(String equipmentId);
    EquipmentDTO findEquipment(String equipmentId);
}