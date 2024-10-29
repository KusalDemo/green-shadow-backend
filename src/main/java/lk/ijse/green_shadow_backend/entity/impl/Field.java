package lk.ijse.green_shadow_backend.entity.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lk.ijse.green_shadow_backend.entity.SuperEntity;
import lombok.*;
import org.springframework.data.geo.Point;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Field implements SuperEntity {
    @Id
    private String fieldCode;
    private String fieldName;
    private Point fieldLocation;
    private Double extentSizeOfField;

    @JsonIgnore
    @OneToMany(mappedBy = "field",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Crop> crops;

    @JsonIgnore
    @ManyToMany(mappedBy = "fields",fetch = FetchType.LAZY)
    private List<Staff> staff;

    @Column(columnDefinition = "LONGTEXT")
    private String image1;
    @Column(columnDefinition = "LONGTEXT")
    private String image2;

    @JsonIgnore
    @OneToMany(mappedBy = "field",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Equipment> equipments;

    @ManyToOne
    @JoinColumn(name = "logCode")
    private Log log;

    public void addStaff(Staff staff){
        this.staff.add(staff);
        staff.getFields().add(this);
    }

    public void removeStaff(Staff staff){
        this.staff.remove(staff);
        staff.getFields().remove(this);
    }
}
