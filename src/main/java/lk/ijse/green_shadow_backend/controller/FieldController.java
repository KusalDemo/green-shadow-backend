package lk.ijse.green_shadow_backend.controller;

import lk.ijse.green_shadow_backend.dto.impl.FieldDTO;
import lk.ijse.green_shadow_backend.service.FieldService;
import lk.ijse.green_shadow_backend.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/field")
public class FieldController {
    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> saveField(@RequestBody FieldDTO fieldDTO){
        try{
            fieldService.saveField(fieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<FieldDTO> getAllFields(){
        return fieldService.getAllFields();
    }

    @PutMapping(value = "/{fieldCode}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> updateField(@PathVariable("fieldCode") String fieldCode,@RequestBody FieldDTO fieldDTO){
        try{
            fieldService.updateField(fieldCode,fieldDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{fieldCode}")
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> deleteField(@PathVariable("fieldCode") String fieldCode){
        try{
            fieldService.deleteField(fieldCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> uploadFieldImage(
            @RequestPart("fieldCode") String fieldCode,
            @RequestPart("image1") MultipartFile image1,
            @RequestPart("image2") MultipartFile image2
    ){
        try{
            byte[] image1Bytes = image1.getBytes();
            byte[] image2Bytes = image2.getBytes();
            fieldService.uploadFieldImage(fieldCode, AppUtil.convertImageToBase64(image1Bytes), AppUtil.convertImageToBase64(image2Bytes));
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
