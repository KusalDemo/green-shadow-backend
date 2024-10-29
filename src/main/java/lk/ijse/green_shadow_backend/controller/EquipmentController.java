package lk.ijse.green_shadow_backend.controller;

import lk.ijse.green_shadow_backend.dto.impl.EquipmentDTO;
import lk.ijse.green_shadow_backend.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    public ResponseEntity<Void> saveEquipment(@RequestBody EquipmentDTO equipmentDTO) {
        try {
            equipmentService.saveEquipment(equipmentDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EquipmentDTO> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }

    @PutMapping(value = "/{equipmentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    public ResponseEntity<Void> updateEquipment(@PathVariable("equipmentId") String equipmentId,@RequestBody EquipmentDTO equipmentDTO){
        try{
            equipmentService.updateEquipment(equipmentId,equipmentDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{equipmentId}")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    public ResponseEntity<Void> deleteEquipment(@PathVariable("equipmentId") String equipmentId){
        try{
            equipmentService.deleteEquipment(equipmentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
