package lk.ijse.green_shadow_backend.dao;

import lk.ijse.green_shadow_backend.entity.impl.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleDAO extends JpaRepository<Vehicle,String> {

    @Query("SELECT v FROM Vehicle v WHERE v.vehicleCategory LIKE CONCAT(:vehicleCategory, '%')")
    List<Vehicle> sortByVehicleCategory(String vehicleCategory);

    @Query("SELECT v FROM Vehicle v WHERE v.status LIKE CONCAT(:status, '%')")
    List<Vehicle> findByStatus(String status);
}
