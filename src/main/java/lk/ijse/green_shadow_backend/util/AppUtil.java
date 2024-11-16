package lk.ijse.green_shadow_backend.util;

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
}
