package lk.ijse.green_shadow_backend.controller;

import lk.ijse.green_shadow_backend.dto.impl.StaffDTO;
import lk.ijse.green_shadow_backend.entity.Gender;
import lk.ijse.green_shadow_backend.service.StaffService;
import lk.ijse.green_shadow_backend.util.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/staff")
@CrossOrigin
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    public ResponseEntity<Void> saveStaff(@RequestBody StaffDTO staffDTO) {
        staffService.saveStaff(staffDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO> getAllStaffs() {
        return staffService.getAllStaffs();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    public ResponseEntity<Void> updateStaff(@PathVariable("id") String id, @RequestBody StaffDTO staffDTO) {
        if (Regex.STAFF_ID.validate(id)) {
            staffService.updateStaff(id, staffDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    public ResponseEntity<Void> deleteStaff(@PathVariable("id") String id) {
        if (Regex.STAFF_ID.validate(id)) {
            staffService.deleteStaff(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "staffName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO> getStaffsByName(@PathVariable("name") String name) {
        return staffService.findByStaffName(name);
    }
    @GetMapping(value = "designation/{designation}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO> findByDesignation(@PathVariable("designation") String designation) {
        return staffService.findByDesignation(designation);
    }
    @GetMapping(value = "gender/{gender}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO> sortByGender(@PathVariable("gender") Gender gender) {
        return staffService.sortByGender(gender);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    public ResponseEntity<Void> assignFieldToStaff(
            @RequestPart("staffId") String staffId,
            @RequestPart("fieldCode") String fieldCode
    ) {
        if (Regex.STAFF_ID.validate(staffId) && Regex.FIELD_CODE.validate(fieldCode)) {
            staffService.assignFieldToStaff(staffId, fieldCode);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    public ResponseEntity<Void> removeFieldFromStaff(
            @RequestPart("staffId") String staffId,
            @RequestPart("fieldCode") String fieldCode
    ) {
        if (Regex.STAFF_ID.validate(staffId) && Regex.FIELD_CODE.validate(fieldCode)) {
            staffService.removeFieldFromStaff(staffId, fieldCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
