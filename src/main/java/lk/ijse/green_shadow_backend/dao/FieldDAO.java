package lk.ijse.green_shadow_backend.dao;

import lk.ijse.green_shadow_backend.entity.impl.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldDAO extends JpaRepository<Field, String> {
}
