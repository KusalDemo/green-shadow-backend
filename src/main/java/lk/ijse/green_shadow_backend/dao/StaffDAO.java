package lk.ijse.green_shadow_backend.dao;

import lk.ijse.green_shadow_backend.entity.Gender;
import lk.ijse.green_shadow_backend.entity.impl.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffDAO extends JpaRepository<Staff, String> {
    @Query("SELECT s FROM Staff s WHERE s.firstName = :staffName OR s.lastName = :staffName")
    List<Staff> findByStaffName(String staffName);

    @Query("SELECT s FROM Staff s WHERE s.designation LIKE CONCAT(:designation, '%')")
    List<Staff> findByDesignation(String designation);

    @Query("SELECT s FROM Staff s WHERE s.gender = :gender")
    List<Staff> sortByGender(Gender gender);

    /*@Query("SELECT s FROM Staff s WHERE s.firstName LIKE CONCAT(:name, '%') OR s.lastName LIKE CONCAT(:name, '%')")
    List<Staff> findByStaffName(String staffName);*/
}
