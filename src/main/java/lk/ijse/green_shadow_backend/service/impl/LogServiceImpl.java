package lk.ijse.green_shadow_backend.service.impl;

import lk.ijse.green_shadow_backend.dao.LogDAO;
import lk.ijse.green_shadow_backend.dto.impl.LogDTO;
import lk.ijse.green_shadow_backend.entity.impl.Log;
import lk.ijse.green_shadow_backend.service.LogService;
import lk.ijse.green_shadow_backend.util.AppUtil;
import lk.ijse.green_shadow_backend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDAO logDao;
    @Autowired
    private Mapping mapper;

    @Override
    public void saveLog(LogDTO logDto) {
        try{
            logDto.setLogCode(AppUtil.generateLogCode());
            logDao.save(mapper.mapToLog(logDto));
        }catch (Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    @Override
    public void uploadObservedImage(String logCode,String observedImage) {
        try{
            Optional<Log> fetchedLog = logDao.findById(logCode);
            if(fetchedLog.isPresent()){
                Log log = fetchedLog.get();
                log.setObservedImage(observedImage);
                logDao.save(log);
            }
        }catch (Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    @Override
    public List<LogDTO> getAllLogs() {
        return mapper.mapToLogDTOList(logDao.findAll());
    }

    @Override
    public void deleteLog(String logCode) {
        try{
            logDao.deleteById(logCode);
        }catch (Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    @Override
    public void updateLog(String logCode, LogDTO logDTO) {
        try{
            Optional<Log> fetchedLog = logDao.findById(logCode);
            if(fetchedLog.isPresent()){
                Log log = fetchedLog.get();
                log.setLogDate(logDTO.getLogDate());
                log.setLogDetails(logDTO.getLogDetails());
                logDao.save(log);
            }
        }catch (Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }
}
