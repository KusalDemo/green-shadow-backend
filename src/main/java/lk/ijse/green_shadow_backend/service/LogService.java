package lk.ijse.green_shadow_backend.service;

import lk.ijse.green_shadow_backend.dto.impl.LogDTO;

import java.util.List;

public interface LogService {
    void saveLog(LogDTO logDto);
    void uploadObservedImage(String logCode,String observedImage);
    List<LogDTO> getAllLogs();
    void deleteLog(String logCode);
    void updateLog(String logCode,LogDTO logDTO);
    LogDTO findLog(String logCode);
}
