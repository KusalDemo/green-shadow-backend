package lk.ijse.green_shadow_backend.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String generateCropCode() {return "CROP-"+ UUID.randomUUID().toString();}

    public static String convertImageToBase64(byte[] image) {return Base64.getEncoder().encodeToString(image);}
}
