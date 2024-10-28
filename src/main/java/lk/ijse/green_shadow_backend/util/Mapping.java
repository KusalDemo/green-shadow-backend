package lk.ijse.green_shadow_backend.util;

import lk.ijse.green_shadow_backend.dto.impl.*;
import lk.ijse.green_shadow_backend.entity.impl.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    public User mapToUser(UserDTO userDTO){return modelMapper.map(userDTO, User.class);}
    public UserDTO mapToUserDTO(User user){return modelMapper.map(user, UserDTO.class);}

    public Crop mapToCrop(CropDTO cropDto){return modelMapper.map(cropDto, Crop.class);}
    public CropDTO mapToCropDTO(Crop crop){return modelMapper.map(crop, CropDTO.class);}
    public List<CropDTO> mapToCropDTOList(List<Crop> cropList){return cropList.stream().map(this::mapToCropDTO).toList();}
    public List<Crop> mapToCropList(List<CropDTO> cropDTOList){return cropDTOList.stream().map(this::mapToCrop).toList();}

    public Field mapToField(FieldDTO fieldDTO){return modelMapper.map(fieldDTO, Field.class);}
    public FieldDTO mapToFieldDTO(Field field){return modelMapper.map(field, FieldDTO.class);}
    public List<FieldDTO> mapToFieldDTOList(List<Field> fieldList){return fieldList.stream().map(this::mapToFieldDTO).toList();}
    public List<Field> mapToFieldList(List<FieldDTO> fieldDTOList){return fieldDTOList.stream().map(this::mapToField).toList();}

    public Staff mapToStaff(StaffDTO staffDto){return modelMapper.map(staffDto, Staff.class);}
    public StaffDTO mapToStaffDTO(Staff staff){return modelMapper.map(staff, StaffDTO.class);}
    public List<StaffDTO> mapToStaffDTOList(List<Staff> staffList){return staffList.stream().map(this::mapToStaffDTO).toList();}
    public List<Staff> mapToStaffList(List<StaffDTO> staffDTOList){return staffDTOList.stream().map(this::mapToStaff).toList();}

    public Log mapToLog(LogDTO logDTO){return modelMapper.map(logDTO, Log.class);}
    public LogDTO mapToLogDTO(Log log){return modelMapper.map(log, LogDTO.class);}
    public List<LogDTO> mapToLogDTOList(List<Log> logList){return logList.stream().map(this::mapToLogDTO).toList();}
    public List<Log> mapToLogList(List<LogDTO> logDTOList){return logDTOList.stream().map(this::mapToLog).toList();}

    public Vehicle mapToVehicle(VehicleDTO vehicleDTO){return modelMapper.map(vehicleDTO, Vehicle.class);}
    public VehicleDTO mapToVehicleDTO(Vehicle vehicle){return modelMapper.map(vehicle, VehicleDTO.class);}
    public List<VehicleDTO> mapToVehicleDTOList(List<Vehicle> vehicleList){return vehicleList.stream().map(this::mapToVehicleDTO).toList();}
    public List<Vehicle> mapToVehicleList(List<VehicleDTO> vehicleDTOList){return vehicleDTOList.stream().map(this::mapToVehicle).toList();}
}
