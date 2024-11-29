package lk.ijse.green_shadow_backend.service.impl;

import lk.ijse.green_shadow_backend.dao.FieldDAO;
import lk.ijse.green_shadow_backend.dao.StaffDAO;
import lk.ijse.green_shadow_backend.dto.impl.StaffDTO;
import lk.ijse.green_shadow_backend.entity.Gender;
import lk.ijse.green_shadow_backend.entity.impl.Field;
import lk.ijse.green_shadow_backend.entity.impl.Staff;
import lk.ijse.green_shadow_backend.exception.EntryNotFoundException;
import lk.ijse.green_shadow_backend.service.LogService;
import lk.ijse.green_shadow_backend.service.StaffService;
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
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDAO staffDao;
    @Autowired
    private Mapping mapper;
    @Autowired
    private LogService logService;
    @Autowired
    private FieldDAO fieldDao;

    @Override
    public void saveStaff(StaffDTO staffDTO) {
        staffDTO.setId(AppUtil.generateStaffId());
        staffDao.save(mapper.mapToStaff(staffDTO));
        log.info("Staff saved successfully with id: {}", staffDTO.getId());
    }

    @Override
    public List<StaffDTO> getAllStaffs() {
        return mapper.mapToStaffDTOList(staffDao.findAll());
    }

    @Override
    public void updateStaff(String id, StaffDTO staffDTO) {
        Optional<Staff> fetchedStaff = staffDao.findById(id);
        if (fetchedStaff.isPresent()) {
            Staff staff = fetchedStaff.get();
            staff.setFirstName(staffDTO.getFirstName());
            staff.setLastName(staffDTO.getLastName());
            staff.setDesignation(staffDTO.getDesignation());
            staff.setGender(staffDTO.getGender());
            staff.setJoinedDate(staffDTO.getJoinedDate());
            staff.setAddressLine1(staffDTO.getAddressLine1());
            staff.setAddressLine2(staffDTO.getAddressLine2());
            staff.setAddressLine3(staffDTO.getAddressLine3());
            staff.setAddressLine4(staffDTO.getAddressLine4());
            staff.setAddressLine5(staffDTO.getAddressLine5());
            staff.setContactNumber(staffDTO.getContactNumber());
            staff.setEmail(staffDTO.getEmail());
            staff.setRole(staffDTO.getRole());
            staff.setDob(staffDTO.getDob());
            staff.setLog(mapper.mapToLog(logService.findLog(staffDTO.getLogCode())));
            staffDao.save(staff);
            log.info("Staff updated successfully with id: {}", id);
        } else {
            throw new EntryNotFoundException("Staff", id);
        }
    }

    @Override
    public void deleteStaff(String id) {
        staffDao.deleteById(id);
        log.warn("Staff deleted successfully with id: {}", id);
    }

    @Override
    public StaffDTO findStaff(String id) {
        Optional<Staff> fetchedStaff = staffDao.findById(id);
        if (fetchedStaff.isPresent()) {
            Staff staff = fetchedStaff.get();
            log.info("Staff found successfully with id: {}", id);
            return mapper.mapToStaffDTO(staff);
        } else {
            throw new EntryNotFoundException("Staff", id);
        }
    }

    @Override
    public List<StaffDTO> findByStaffName(String staffName) {
        return mapper.mapToStaffDTOList(staffDao.findByStaffName(staffName));
    }

    @Override
    public List<StaffDTO> findByDesignation(String designation) {
        return mapper.mapToStaffDTOList(staffDao.findByDesignation(designation));
    }

    @Override
    public List<StaffDTO> sortByGender(Gender gender) {
        return mapper.mapToStaffDTOList(staffDao.sortByGender(gender));
    }


    @Override
    public void assignFieldToStaff(String staffId, String fieldCode) {
        Staff fetchedStaff = staffDao.findById(staffId).orElseThrow(() -> new EntryNotFoundException("Staff", staffId));
        Field fetchedField = fieldDao.findById(fieldCode).orElseThrow(() -> new EntryNotFoundException("Field", fieldCode));
        fetchedStaff.addField(fetchedField);
        staffDao.save(fetchedStaff);
        log.info("Field assigned to staff successfully with id: {} and field code: {}", staffId, fieldCode);
    }

    @Override
    public void removeFieldFromStaff(String staffId, String fieldCode) {
        Staff fetchedStaff = staffDao.findById(staffId).orElseThrow(() -> new EntryNotFoundException("Staff", staffId));
        Field fetchedField = fieldDao.findById(fieldCode).orElseThrow(() -> new EntryNotFoundException("Field", fieldCode));
        fetchedStaff.removeField(fetchedField);
        staffDao.save(fetchedStaff);
        log.warn("Field removed from staff successfully with id: {} and field code: {}", staffId, fieldCode);
    }

    @Override
    public List<StaffDTO> findStaffByFieldCode(String fieldCode) {
        return mapper.mapToStaffDTOList(staffDao.findStaffByFieldCode(fieldCode));
    }
}
