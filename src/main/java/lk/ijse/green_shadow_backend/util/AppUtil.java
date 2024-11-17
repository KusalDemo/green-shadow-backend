package lk.ijse.green_shadow_backend.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String generateCropCode(String cropCategory) {return "CROP-"+cropCategory+"-"+ UUID.randomUUID().toString();}
    public static String generateFieldCode() {return "FIELD-"+ UUID.randomUUID().toString();}
    public static String generateStaffId() {return "STAFF-"+ UUID.randomUUID().toString();}
    public static String generateLogCode() {return "LOG-"+ UUID.randomUUID().toString();}
    public static String generateVehicleCode() {return "VHCL-"+ UUID.randomUUID().toString();}
    public static String generateEquipmentId() {return "EQPT-"+ UUID.randomUUID().toString();}

    public static String convertImageToBase64(byte[] image) {return Base64.getEncoder().encodeToString(image);}

    public static boolean isImageValidType(MultipartFile image) {
        String fileType = image.getContentType();
        if (fileType == null ||
                !(fileType.equals("image/jpeg") ||
                        fileType.equals("image/jpg") ||
                        fileType.equals("image/png"))) {
            return false;
        } else {
            return true;
        }
    }
}