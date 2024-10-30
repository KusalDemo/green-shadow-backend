package lk.ijse.green_shadow_backend.service.impl;

import lk.ijse.green_shadow_backend.dao.LogDAO;
import lk.ijse.green_shadow_backend.dto.impl.LogDTO;
import lk.ijse.green_shadow_backend.entity.impl.Log;
import lk.ijse.green_shadow_backend.exception.EntryNotFoundException;
import lk.ijse.green_shadow_backend.service.LogService;
import lk.ijse.green_shadow_backend.util.AppUtil;
import lk.ijse.green_shadow_backend.util.Mapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDAO logDao;
    @Autowired
    private Mapping mapper;

    @Override
    public void saveLog(LogDTO logDto) {
        logDto.setLogCode(AppUtil.generateLogCode());
        logDao.save(mapper.mapToLog(logDto));
        log.info("Log saved successfully with code: {}", logDto.getLogCode());
    }

    @Override
    public void uploadObservedImage(String logCode, String observedImage) {
        Optional<Log> fetchedLog = logDao.findById(logCode);
        if (fetchedLog.isPresent()) {
            Log logEntity = fetchedLog.get();
            logEntity.setObservedImage(observedImage);
            logDao.save(logEntity);
            log.info("Observed image uploaded successfully with code: {}", logCode);
        } else {
            throw new EntryNotFoundException("Log", logCode);
        }
    }

    @Override
    public List<LogDTO> getAllLogs() {
        return mapper.mapToLogDTOList(logDao.findAll());
    }

    @Override
    public void deleteLog(String logCode) {
        logDao.deleteById(logCode);
        log.warn("Log deleted successfully with code: {}", logCode);
    }

    @Override
    public void updateLog(String logCode, LogDTO logDTO) {
        Optional<Log> fetchedLog = logDao.findById(logCode);
        if (fetchedLog.isPresent()) {
            Log logEntity = fetchedLog.get();
            logEntity.setLogDate(logDTO.getLogDate());
            logEntity.setLogDetails(logDTO.getLogDetails());
            logDao.save(logEntity);
            log.info("Log updated successfully with code: {}", logCode);
        } else {
            throw new EntryNotFoundException("Log", logCode);
        }
    }

    @Override
    public LogDTO findLog(String logCode) {
        Optional<Log> fetchedLog = logDao.findById(logCode);
        if (fetchedLog.isPresent()) {
            Log logEntity = fetchedLog.get();
            log.info("Log found successfully with code: {}", logCode);
            return mapper.mapToLogDTO(logEntity);
        } else {
            throw new EntryNotFoundException("Log", logCode);
        }
    }
}
