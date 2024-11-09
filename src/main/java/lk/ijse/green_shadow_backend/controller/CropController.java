package lk.ijse.green_shadow_backend.controller;

import lk.ijse.green_shadow_backend.dto.impl.CropDTO;
import lk.ijse.green_shadow_backend.service.CropService;
import lk.ijse.green_shadow_backend.util.AppUtil;
import lk.ijse.green_shadow_backend.util.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/crop")
@CrossOrigin
public class CropController {
    @Autowired
    private CropService cropService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> saveCrop(@RequestBody CropDTO cropDTO) {
        cropService.saveCrop(cropDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getAllCrops() {
        return cropService.getAllCrops();
    }

    @PutMapping(value = "/{cropCode}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> updateCrop(@PathVariable("cropCode") String cropCode, @RequestBody CropDTO cropDTO) {
        if (Regex.CROP_CODE.validate(cropCode)) {
            cropDTO.setCropCode(cropCode);
            cropService.updateCrop(cropDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{cropCode}")
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> deleteCrop(@PathVariable("cropCode") String cropCode) {
        if (Regex.CROP_CODE.validate(cropCode)) {
            cropService.deleteCrop(cropCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> uploadCropImage(
            @RequestPart("cropCode") String cropCode,
            @RequestPart("image") MultipartFile image) throws IOException {

        if (Regex.CROP_CODE.validate(cropCode)) {
            byte[] imageBytes = image.getBytes();
            String cropImageBase64 = AppUtil.convertImageToBase64(imageBytes);
            cropService.saveImage(cropCode, cropImageBase64);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getCropsByName(@PathVariable("name") String name) {
        return cropService.getCropsByName(name);
    }

    @PostMapping(value = "/log", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> createLogForCrop(
            @RequestPart("cropCode") String cropCode,
            @RequestPart("logCode") String logCode
    ) {
        cropService.createLogForCrop(cropCode,logCode);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/log", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> deleteLogForCrop(
            @RequestPart("cropCode") String cropCode,
            @RequestPart("logCode") String logCode){
        cropService.deleteLogForCrop(cropCode,logCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
