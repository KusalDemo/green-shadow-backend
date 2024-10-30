package lk.ijse.green_shadow_backend.service;

import lk.ijse.green_shadow_backend.dto.impl.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);
    List<VehicleDTO> getAllVehicles();
    void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO);
    void deleteVehicle(String vehicleCode);
    VehicleDTO findVehicle(String vehicleCode);
    List<VehicleDTO> sortByVehicleCategory(String name);
    List<VehicleDTO> findByStatus(String status);
}
