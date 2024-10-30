package lk.ijse.green_shadow_backend.controller;

import lk.ijse.green_shadow_backend.dto.impl.LogDTO;
import lk.ijse.green_shadow_backend.service.LogService;
import lk.ijse.green_shadow_backend.util.AppUtil;
import lk.ijse.green_shadow_backend.util.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/log")
public class LogController {
    @Autowired
    private LogService logService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveLog(@RequestBody LogDTO logDTO) {
        logService.saveLog(logDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadObservedImage(
            @RequestPart("logCode") String logCode,
            @RequestPart("observedImage") String observedImage
    ) {
        if (Regex.LOG_CODE.validate(logCode)) {
            byte[] observedImageBytes = observedImage.getBytes();
            String convertedImageToBase64 = AppUtil.convertImageToBase64(observedImageBytes);
            logService.uploadObservedImage(logCode, convertedImageToBase64);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LogDTO> getAllLogs() {
        return logService.getAllLogs();
    }

    @DeleteMapping(value = "/{logCode}")
    public ResponseEntity<Void> deleteLog(@RequestParam("logCode") String logCode) {
        if (Regex.LOG_CODE.validate(logCode)) {
            logService.deleteLog(logCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{logCode}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateLog(@PathVariable("logCode") String logCode, @RequestBody LogDTO logDTO) {
        if (Regex.LOG_CODE.validate(logCode)) {
            logService.updateLog(logCode, logDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/dates", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LogDTO> getLogsBetweenDates(@RequestParam("start") String date1, @RequestParam("end") String  date2) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return logService.getLogsBetweenDates(formatter.parse(date1), formatter.parse(date2));
    }
}
