package lk.ijse.green_shadow_backend.entity.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(columnDefinition = "LONGTEXT")
    private String observedImage;

    @JsonIgnore
    @OneToMany(mappedBy = "log",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Field> fields;

//    @JsonIgnore
//    @OneToMany(mappedBy = "log",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
//    private List<Crop> crops;
    @JsonIgnore
    @ManyToMany(mappedBy = "logs",fetch = FetchType.EAGER)
    private List<Crop> crops;

    @JsonIgnore
    @OneToMany(mappedBy = "log",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Staff> staff;

    public void addCrop(Crop crop){
        crops.add(crop);
        crop.getLogs().add(this);
    }

    public void removeCrop(Crop crop){
        crops.remove(crop);
        crop.getLogs().remove(this);
    }
}
