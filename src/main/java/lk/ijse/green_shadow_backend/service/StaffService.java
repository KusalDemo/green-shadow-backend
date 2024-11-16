package lk.ijse.green_shadow_backend.service;

import lk.ijse.green_shadow_backend.dto.impl.StaffDTO;
import lk.ijse.green_shadow_backend.entity.Gender;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO);
    List<StaffDTO> getAllStaffs();
    void updateStaff(String id, StaffDTO staffDTO);
    void deleteStaff(String id);
    StaffDTO findStaff(String id);
    List<StaffDTO> findByStaffName(String staffName);
    List<StaffDTO> findByDesignation(String designation);
    List<StaffDTO> sortByGender(Gender gender);

    void assignFieldToStaff(String staffId, String fieldCode);
    void removeFieldFromStaff(String staffId, String fieldCode);
    List<StaffDTO> findStaffByFieldCode(String fieldCode);
}
