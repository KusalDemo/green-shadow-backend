package lk.ijse.green_shadow_backend.service.impl;

import lk.ijse.green_shadow_backend.dao.VehicleDAO;
import lk.ijse.green_shadow_backend.dto.impl.VehicleDTO;
import lk.ijse.green_shadow_backend.entity.impl.Vehicle;
import lk.ijse.green_shadow_backend.service.VehicleService;
import lk.ijse.green_shadow_backend.util.AppUtil;
import lk.ijse.green_shadow_backend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDAO vehicleDao;
    @Autowired
    private Mapping mapper;

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        try {
            vehicleDTO.setVehicleCode(AppUtil.generateVehicleCode());
            vehicleDao.save(mapper.mapToVehicle(vehicleDTO));
        }catch (Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return mapper.mapToVehicleDTOList(vehicleDao.findAll());
    }

    @Override
    public void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO) {
        try{
            Optional<Vehicle> fetchedVehicle = vehicleDao.findById(vehicleCode);
            if (fetchedVehicle.isPresent()){
                Vehicle vehicle = fetchedVehicle.get();
                vehicle.setLicensePlateNumber(vehicleDTO.getLicensePlateNumber());
                vehicle.setVehicleCategory(vehicleDTO.getVehicleCategory());
                vehicle.setFuelType(vehicleDTO.getFuelType());
                vehicle.setStatus(vehicleDTO.getStatus());
                vehicle.setRemarks(vehicleDTO.getRemarks());
                //vehicle.setAllocatedStaffMemberDetails(vehicleDTO.getAllocatedStaffMemberDetails());
                vehicleDao.save(vehicle);
            }
        }catch (Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    @Override
    public void deleteVehicle(String vehicleCode) {
        try {
            vehicleDao.deleteById(vehicleCode);
        }catch (Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    @Override
    public VehicleDTO findVehicle(String vehicleCode) {
        return mapper.mapToVehicleDTO(vehicleDao.findById(vehicleCode).get());
    }
}
