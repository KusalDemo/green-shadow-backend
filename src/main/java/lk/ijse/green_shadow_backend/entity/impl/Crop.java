package lk.ijse.green_shadow_backend.entity.impl;

import jakarta.persistence.*;
import lk.ijse.green_shadow_backend.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Crop implements SuperEntity {
    @Id
    private String cropCode;
    private String cropCommonName;
    private String cropScientificName;
    @Column(columnDefinition = "LONGTEXT")
    private String cropImage;
    private String category;
    private String cropSeason;
    @ManyToOne
    @JoinColumn(name = "fieldCode")
    private Field field;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "crop_log",
            joinColumns = @JoinColumn(name = "crop_code"),
            inverseJoinColumns = @JoinColumn(name = "log_code")
    )
    private List<Log> logs;

    public void addLog(Log log){
        logs.add(log);
        log.getCrops().add(this);
    }
    public void removeLog(Log log){
        logs.remove(log);
        log.getCrops().remove(this);
    }
}
