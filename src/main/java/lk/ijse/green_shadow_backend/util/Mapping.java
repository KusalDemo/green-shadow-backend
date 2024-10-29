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

    public User mapToUser(UserDTO userDTO) {return modelMapper.map(userDTO, User.class);}
    public UserDTO mapToUserDTO(User user) {return modelMapper.map(user, UserDTO.class);}

    public Crop mapToCrop(CropDTO cropDto) {return modelMapper.map(cropDto, Crop.class);}
    public CropDTO mapToCropDTO(Crop crop) {return modelMapper.map(crop, CropDTO.class);}
    public List<CropDTO> mapToCropDTOList(List<Crop> cropList) {return cropList.stream().map(this::mapToCropDTO).toList();}
    public List<Crop> mapToCropList(List<CropDTO> cropDTOList) {return cropDTOList.stream().map(this::mapToCrop).toList();}

    public Field mapToField(FieldDTO fieldDTO) {return modelMapper.map(fieldDTO, Field.class);}
    public FieldDTO mapToFieldDTO(Field field) {return modelMapper.map(field, FieldDTO.class);}
    public List<FieldDTO> mapToFieldDTOList(List<Field> fieldList) {
        return fieldList.stream().map(field -> {
            FieldDTO fieldDTO = new FieldDTO();
            fieldDTO.setFieldCode(field.getFieldCode());
            fieldDTO.setFieldName(field.getFieldName());
            fieldDTO.setFieldLocation(field.getFieldLocation());
            fieldDTO.setExtentSizeOfField(field.getExtentSizeOfField());
            fieldDTO.setImage1(field.getImage1());
            fieldDTO.setImage2(field.getImage2());
            fieldDTO.setCrops(
                    field.getCrops().stream().map(crop -> CropDTO.builder()
                            .cropCode(crop.getCropCode())
                            .cropCommonName(crop.getCropCommonName())
                            .cropScientificName(crop.getCropScientificName())
                            .cropImage(crop.getCropImage())
                            .category(crop.getCategory())
                            .cropSeason(crop.getCropSeason())
                            .fieldCode(crop.getField().getFieldCode())
                            .logCode(crop.getLog().getLogCode())
                            .build()).toList()
            );
            fieldDTO.setStaff(
                    field.getStaff().stream().map(staff -> StaffDTO.builder()
                            .id(staff.getId())
                            .firstName(staff.getFirstName())
                            .lastName(staff.getLastName())
                            .joinedDate(staff.getJoinedDate())
                            .gender(staff.getGender())
                            .designation(staff.getDesignation())
                            .addressLine1(staff.getAddressLine1())
                            .addressLine2(staff.getAddressLine2())
                            .addressLine3(staff.getAddressLine3())
                            .addressLine4(staff.getAddressLine4())
                            .addressLine5(staff.getAddressLine5())
                            .contactNumber(staff.getContactNumber())
                            .email(staff.getEmail())
                            .role(staff.getRole())
                            .build()).toList()
            );
            fieldDTO.setEquipments(
                    field.getEquipments().stream().map(equipment -> EquipmentDTO.builder()
                            .equipmentId(equipment.getEquipmentId())
                            .name(equipment.getName())
                            .type(equipment.getType())
                            .status(equipment.getStatus())
                            .staffId(equipment.getStaff().getId())
                            .fieldCode(equipment.getField().getFieldCode())
                            .build()).toList()
            );
            fieldDTO.setLogCode(field.getLog().getLogCode());
            return fieldDTO;
        }).toList();
    }
    public List<Field> mapToFieldList(List<FieldDTO> fieldDTOList) {return fieldDTOList.stream().map(this::mapToField).toList();}

    public Staff mapToStaff(StaffDTO staffDto) {return modelMapper.map(staffDto, Staff.class);}
    public StaffDTO mapToStaffDTO(Staff staff) {return modelMapper.map(staff, StaffDTO.class);}
    public List<StaffDTO> mapToStaffDTOList(List<Staff> staffList) {
        return staffList.stream().map(staff -> {
            StaffDTO staffDTO = new StaffDTO();
            staffDTO.setId(staff.getId());
            staffDTO.setFirstName(staff.getFirstName());
            staffDTO.setLastName(staff.getLastName());
            staffDTO.setDesignation(staff.getDesignation());
            staffDTO.setGender(staff.getGender());
            staffDTO.setJoinedDate(staff.getJoinedDate());
            staffDTO.setAddressLine1(staff.getAddressLine1());
            staffDTO.setAddressLine2(staff.getAddressLine2());
            staffDTO.setAddressLine3(staff.getAddressLine3());
            staffDTO.setAddressLine4(staff.getAddressLine4());
            staffDTO.setAddressLine5(staff.getAddressLine5());
            staffDTO.setContactNumber(staff.getContactNumber());
            staffDTO.setEmail(staff.getEmail());
            staffDTO.setRole(staff.getRole());
            staffDTO.setFields(
                    staff.getFields().stream().map(field -> FieldDTO.builder()
                            .fieldCode(field.getFieldCode())
                            .fieldName(field.getFieldName())
                            .image1(field.getImage1())
                            .image2(field.getImage2())
                            .fieldLocation(field.getFieldLocation())
                            .extentSizeOfField(field.getExtentSizeOfField())
                            .crops(field.getCrops().stream().map(crop -> CropDTO.builder()
                                    .cropCode(crop.getCropCode())
                                    .cropCommonName(crop.getCropCommonName())
                                    .cropScientificName(crop.getCropScientificName())
                                    .cropImage(crop.getCropImage())
                                    .category(crop.getCategory())
                                    .cropSeason(crop.getCropSeason())
                                    .fieldCode(crop.getField().getFieldCode())
                                    .logCode(crop.getLog().getLogCode())
                                    .build()).toList())
                            .build()).toList()
            );
            staffDTO.setVehicles(
                    staff.getVehicles().stream().map(vehicle -> VehicleDTO.builder()
                            .vehicleCode(vehicle.getVehicleCode())
                            .licensePlateNumber(vehicle.getLicensePlateNumber())
                            .vehicleCategory(vehicle.getVehicleCategory())
                            .fuelType(vehicle.getFuelType())
                            .status(vehicle.getStatus())
                            .remarks(vehicle.getRemarks())
                            .staff(vehicle.getStaff().getId())
                            .build()).toList());
            staffDTO.setEquipments(mapToEquipmentDTOList(staff.getEquipments()));
            staffDTO.setLogCode(staff.getLog().getLogCode());
            return staffDTO;
        }).toList();
    }
    public List<Staff> mapToStaffList(List<StaffDTO> staffDTOList) {return staffDTOList.stream().map(this::mapToStaff).toList();}

    public Log mapToLog(LogDTO logDTO) {return modelMapper.map(logDTO, Log.class);}
    public LogDTO mapToLogDTO(Log log) {return modelMapper.map(log, LogDTO.class);}
    public List<LogDTO> mapToLogDTOList(List<Log> logList) {
        return logList.stream().map(log -> {
            LogDTO logDTO = new LogDTO();
            logDTO.setLogCode(log.getLogCode());
            logDTO.setLogDate(log.getLogDate());
            logDTO.setLogDetails(log.getLogDetails());
            logDTO.setObservedImage(log.getObservedImage());
            logDTO.setFields(
                    log.getFields().stream().map(field -> FieldDTO.builder()
                            .fieldCode(field.getFieldCode())
                            .fieldName(field.getFieldName())
                            .image1(field.getImage1())
                            .image2(field.getImage2())
                            .fieldLocation(field.getFieldLocation())
                            .extentSizeOfField(field.getExtentSizeOfField())
                            .build()).toList()
            );
            logDTO.setCrops(
                    log.getCrops().stream().map(crop -> CropDTO.builder()
                            .cropCode(crop.getCropCode())
                            .cropCommonName(crop.getCropCommonName())
                            .cropScientificName(crop.getCropScientificName())
                            .cropImage(crop.getCropImage())
                            .category(crop.getCategory())
                            .cropSeason(crop.getCropSeason())
                            .fieldCode(crop.getField().getFieldCode())
                            .logCode(crop.getLog().getLogCode())
                            .build()).toList()
            );
            logDTO.setStaff(
                    log.getStaff().stream().map(staff -> StaffDTO.builder()
                            .id(staff.getId())
                            .firstName(staff.getFirstName())
                            .lastName(staff.getLastName())
                            .designation(staff.getDesignation())
                            .gender(staff.getGender())
                            .joinedDate(staff.getJoinedDate())
                            .addressLine1(staff.getAddressLine1())
                            .addressLine2(staff.getAddressLine2())
                            .addressLine3(staff.getAddressLine3())
                            .addressLine4(staff.getAddressLine4())
                            .addressLine5(staff.getAddressLine5())
                            .contactNumber(staff.getContactNumber())
                            .email(staff.getEmail())
                            .role(staff.getRole())
                            .build()).toList());
            return logDTO;
        }).toList();
    }
    public List<Log> mapToLogList(List<LogDTO> logDTOList) {return logDTOList.stream().map(this::mapToLog).toList();}

    public Vehicle mapToVehicle(VehicleDTO vehicleDTO) {return modelMapper.map(vehicleDTO, Vehicle.class);}
    public VehicleDTO mapToVehicleDTO(Vehicle vehicle) {return modelMapper.map(vehicle, VehicleDTO.class);}
    public List<VehicleDTO> mapToVehicleDTOList(List<Vehicle> vehicleList) {return vehicleList.stream().map(this::mapToVehicleDTO).toList();}
    public List<Vehicle> mapToVehicleList(List<VehicleDTO> vehicleDTOList) {return vehicleDTOList.stream().map(this::mapToVehicle).toList();}

    public Equipment mapToEquipment(EquipmentDTO equipmentDTO) {return modelMapper.map(equipmentDTO, Equipment.class);}
    public EquipmentDTO mapToEquipmentDTO(Equipment equipment) {return modelMapper.map(equipment, EquipmentDTO.class);}
    public List<EquipmentDTO> mapToEquipmentDTOList(List<Equipment> equipmentList) {return equipmentList.stream().map(this::mapToEquipmentDTO).toList();}
    public List<Equipment> mapToEquipmentList(List<EquipmentDTO> equipmentDTOList) {return equipmentDTOList.stream().map(this::mapToEquipment).toList();}
}