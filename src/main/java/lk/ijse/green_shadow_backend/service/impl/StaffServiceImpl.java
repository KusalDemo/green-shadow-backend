package lk.ijse.green_shadow_backend.service.impl;

import lk.ijse.green_shadow_backend.dao.StaffDAO;
import lk.ijse.green_shadow_backend.dto.impl.StaffDTO;
import lk.ijse.green_shadow_backend.entity.impl.Staff;
import lk.ijse.green_shadow_backend.service.LogService;
import lk.ijse.green_shadow_backend.service.StaffService;
import lk.ijse.green_shadow_backend.util.AppUtil;
import lk.ijse.green_shadow_backend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDAO staffDao;
    @Autowired
    private Mapping mapper;
    @Autowired
    private LogService logService;

    @Override
    public void saveStaff(StaffDTO staffDTO) {
        try{
            staffDTO.setId(AppUtil.generateStaffId());
            staffDao.save(mapper.mapToStaff(staffDTO));
        }catch (Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    @Override
    public List<StaffDTO> getAllStaffs() {
        return mapper.mapToStaffDTOList(staffDao.findAll());
    }

    @Override
    public void updateStaff(String id, StaffDTO staffDTO) {
        try{
            Optional<Staff> fetchedStaff = staffDao.findById(id);
            if(fetchedStaff.isPresent()){
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
                staff.setDOB(staffDTO.getDOB());
                staff.setLog(mapper.mapToLog(logService.findLog(staffDTO.getLogCode())));
                staffDao.save(staff);
            }
        }catch (Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    @Override
    public void deleteStaff(String id) {
        staffDao.deleteById(id);
    }

    @Override
    public StaffDTO findStaff(String id) {
        return mapper.mapToStaffDTO(staffDao.findById(id).get());
    }
}
