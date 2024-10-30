package lk.ijse.green_shadow_backend.dao;

import lk.ijse.green_shadow_backend.entity.impl.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropDAO extends JpaRepository<Crop,String> {
    @Query("SELECT e FROM Crop e WHERE e.cropCommonName LIKE CONCAT(:name, '%') OR e.cropScientificName LIKE CONCAT(:name, '%')")
    List<Crop> findByName(String name);

}
