package lk.ijse.green_shadow_backend.entity.impl;

import jakarta.persistence.*;
import lk.ijse.green_shadow_backend.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Field implements SuperEntity {
    @Id
    private String fieldCode;
    private String fieldName;
    private Point fieldLocation;
    private Double extentSizeOfField;
    @OneToMany(mappedBy = "field",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Crop> crops;
    @ManyToMany(mappedBy = "fields",fetch = FetchType.EAGER)
    private List<Staff> staff;
    private String image1;
    private String image2;
    @OneToMany(mappedBy = "field",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Equipment> equipments;
    @ManyToOne
    @JoinColumn(name = "logCode")
    private Log log;
}
