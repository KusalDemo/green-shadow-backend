package lk.ijse.green_shadow_backend.entity.impl;

import jakarta.persistence.*;
import lk.ijse.green_shadow_backend.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Log implements SuperEntity {
    @Id
    private String logCode;
    private Date logDate;
    private String logDetails;
    private String observedImage;
    @OneToMany(mappedBy = "log",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Field> fields;
    @OneToMany(mappedBy = "log",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Crop> crops;
    @OneToMany(mappedBy = "log",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Staff> staff;
}
